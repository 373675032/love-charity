package com.charity.controller;

import com.alibaba.fastjson.JSONObject;
import com.charity.constant.CheckStatus;
import com.charity.constant.CommentType;
import com.charity.constant.TrashStatus;
import com.charity.constant.TypeStatus;
import com.charity.entity.Article;
import com.charity.utils.OssUtils;
import com.charity.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 活动控制器
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
@Controller
public class ActivityController extends BaseController {

    private final Logger logger = LogUtils.getInstance(ActivityController.class);

    /**
     * 发布活动
     */
    @PostMapping("/publishActivity")
    @ResponseBody
    public String publishActivity(String title, String content, String imgPath) {
        // 生成活动对象
        Article article = Article.builder()
                .title(title).content(content).userId(loginUser.getId())
                .type(TypeStatus.ACTIVITY).readCount(0).status(TrashStatus.NOT_IN).isChecked(CheckStatus.PASS)
                .gmtCreate(new Date()).gmtModified(new Date())
                .build();
        // 为活动设置封面，如果为空就生成随机封面
        article.setImg(StringUtils.isEmpty(imgPath) ? OssUtils.getRandomFace() : imgPath);
        if (articleService.insert(article)) {
            logger.info("【成功】：发布活动");
            result.setCode(200);
        } else {
            logger.info("【失败】：发布活动");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 更新活动
     */
    @PostMapping("/updateActivity")
    @ResponseBody
    public String updateActivity(Integer id, String title, String content, String imgPath) {
        // 生成活动对象
        Article article = Article.builder()
                .id(id).title(title).content(content)
                .gmtModified(new Date())
                .build();
        // 为文章设置封面，如果为空就生成随机封面
        article.setImg(StringUtils.isEmpty(imgPath) ? OssUtils.getRandomFace() : imgPath);
        if (articleService.update(article)) {
            logger.info("【成功】：更新活动");
            result.setCode(200);
        } else {
            logger.info("【失败】：更新活动");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除活动
     */
    @GetMapping("/deleteActivity")
    public String deleteActivity(@RequestParam("id") Integer id) {
        Article activity = articleService.getById(id);
        if (activity.getType() == TypeStatus.ARTICLE) {
            logger.info("【失败】：删除活动，类型错误");
            return "error/400";
        }
        if (articleService.deleteById(id)) {
            logger.info("【成功】：删除活动");
            articleService.deleteActivity(id);
            // 删除评论
            commentService.deleteByTargetIdAndType(id, CommentType.ACTIVITY);
        } else {
            logger.info("【失败】：删除活动");
        }
        return "redirect:/admin-activity";
    }
}
