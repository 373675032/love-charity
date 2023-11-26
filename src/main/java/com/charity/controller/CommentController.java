package com.charity.controller;

import com.alibaba.fastjson.JSONObject;
import com.charity.constant.CommentType;
import com.charity.constant.RoleStatus;
import com.charity.dto.ResponseResult;
import com.charity.entity.Article;
import com.charity.entity.Comment;
import com.charity.entity.Message;
import com.charity.utils.LogUtils;
import com.charity.utils.MessageUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 评论控制器
 * <p>
 * ==========================================================================
 * 郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
 * ==========================================================================
 * B站账号：薛伟同学
 * 微信公众号：薛伟同学
 * 作者博客：http://xuewei.world
 * ==========================================================================
 * 陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
 * 不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
 * 希望各位朋友擦亮慧眼，谨防上当受骗！
 * ==========================================================================
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@RestController
public class CommentController extends BaseController {

    private final Logger logger = LogUtils.getInstance(CommentController.class);

    /**
     * 回复评论
     */
    @PostMapping("/replyComment")
    public String replyComment(@RequestParam("id") Integer id, @RequestParam("content") String content, @RequestParam("type") Integer type) {
        // 获取被回复的评论
        Comment c = commentService.getById(id);
        // 生成回复的评论对象
        Comment comment = Comment.builder()
                .type(type).isFirst(0).replyId(id).content(content).userId(loginUser.getId()).userName(loginUser.getName())
                .img(loginUser.getImg()).gmtCreate(new Date()).gmtModified(new Date()).targetId(c.getTargetId())
                .build();
        if (type == CommentType.PROJECT) {
            // 回复的是项目
            if (commentService.insert(comment)) {
                logger.info("【成功】：回复评论");
                result.setCode(200);
            } else {
                logger.info("【失败】：回复评论");
                result.setCode(500);
            }
        } else {
            // 回复的是文章/活动
            Article article = articleService.getById(c.getTargetId());
            if (commentService.insert(comment)) {
                String s = MessageUtils.relplyCommentHandle(article.getTitle(), loginUser.getName(), content, type);
                // 生成消息对象
                Message message = Message.builder()
                        .receiveUserId(c.getUserId()).sentUserId(loginUser.getId()).type(type).targetId(c.getTargetId())
                        .title(article.getTitle()).sentUserImg(loginUser.getImg()).content(s).gmtCreate(new Date())
                        .build();
                if (messageService.insert(message)) {
                    logger.info("【成功】：发送消息");
                } else {
                    logger.info("【失败】：发送消息");
                }
                logger.info("【成功】：回复评论");
                result.setCode(200);
            } else {
                result.setCode(500);
                logger.info("【失败】：回复评论");
            }
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 添加评论
     */
    @PostMapping("/addComment")
    public String addComment(@RequestParam("id") Integer id, @RequestParam("content") String content, @RequestParam("type") Integer type) {
        // 生成评论对象
        Comment comment = Comment.builder()
                .type(type).isFirst(0).targetId(id).content(content)
                .userId(loginUser.getId()).userName(loginUser.getName()).img(loginUser.getImg())
                .gmtCreate(new Date()).gmtModified(new Date())
                .build();
        if (type == CommentType.PROJECT) {
            // 评论的是项目
            if (commentService.insert(comment)) {
                logger.info("【成功】：添加评论");
                result.setCode(200);
            } else {
                logger.info("【失败】：添加评论");
                result.setCode(500);
            }
        } else {
            // 评论的是文章/活动
            Article article = articleService.getById(id);
            if (commentService.insert(comment)) {
                String s = MessageUtils.relplyCommentHandle(article.getTitle(), loginUser.getName(), content, type);
                // 生成消息对象
                Message message = Message.builder()
                        .receiveUserId(article.getUserId()).sentUserId(loginUser.getId()).type(type).targetId(article.getId())
                        .title(article.getTitle()).sentUserImg(loginUser.getImg()).content(s).gmtCreate(new Date())
                        .build();
                if (messageService.insert(message)) {
                    logger.info("【成功】：发送消息");
                } else {
                    logger.info("【失败】：发送消息");
                }
                logger.info("【成功】：添加评论");
                result.setCode(200);
            } else {
                result.setCode(500);
                logger.info("【失败】：添加评论");
            }
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除评论
     */
    @PostMapping("/delComment")
    public ResponseResult delComment(Integer id) {
        // 获取评论对象
        Comment comment = commentService.getById(id);
        // 如果此评论的类型不是文章并且当前登录用户不是管理员
        if (comment.getType() != CommentType.ARTICLE && loginUser.getRole() != RoleStatus.ADMIN) {
            logger.info("【失败】：无权限");
            result.setCode(500);
            return result;
        }
        if (commentService.deleteById(id)) {
            logger.info("【成功】：删除评论");
            result.setCode(200);
        } else {
            logger.info("【失败】：删除评论");
            result.setCode(500);
        }
        return result;
    }

    /**
     * 评论置顶/取消置顶
     */
    @PostMapping("/commentIsFirst")
    public ResponseResult findCommentIsFirst(Integer id, Integer flag, Integer type) {
        Comment target = commentService.getById(id);
        //代表置顶操作
        if (flag == 0) {
            //查找是否存在置顶
            Comment comment = Comment.builder().targetId(target.getTargetId()).isFirst(1).type(type).build();
            List<Comment> comments = commentService.listComments(comment);
            if (comments.size() == 0) {
                //设置置顶
                target.setIsFirst(1);
                if (commentService.update(target)) {
                    result.setCode(200);
                    logger.info("【成功】：评论置顶");
                } else {
                    result.setCode(500);
                    logger.info("【成功】：评论置顶");
                }
            } else {
                result.setCode(500);
                logger.info("【失败】：评论置顶，已存在置顶评论");
            }
        } else {
            //取消置顶
            target.setIsFirst(0);
            if (commentService.update(target)) {
                result.setCode(200);
                logger.info("【成功】：取消置顶");
            } else {
                result.setCode(500);
                logger.info("【失败】：取消置顶");
            }
        }
        return result;
    }
}
