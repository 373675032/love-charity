package com.charity.controller;

import cn.hutool.core.util.ObjectUtil;
import com.charity.constant.CheckStatus;
import com.charity.constant.RoleStatus;
import com.charity.constant.TrashStatus;
import com.charity.constant.TypeStatus;
import com.charity.dto.ResponseResult;
import com.charity.entity.Article;
import com.charity.entity.Certification;
import com.charity.entity.Message;
import com.charity.entity.User;
import com.charity.service.*;
import com.charity.service.impl.UserServiceImpl;
import com.charity.utils.MailUtils;
import com.charity.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础控制器
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
public class BaseController {


    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected CertificationService certificationService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected FeedbackService feedbackService;

    @Autowired
    protected LinkService linkService;

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected UserServiceImpl userService;

    @Autowired
    protected MessageBoardService boardService;

    @Resource()
    protected JavaMailSenderImpl mailSender;
    protected MailUtils mailUtils;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;

    protected ResponseResult result;

    /**
     * 在每个子类方法调用之前先调用，设置 Request、Response、Session 这三个对象
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
        if (!ObjectUtil.isEmpty(loginUser)) {
            if (loginUser.getRole() == RoleStatus.USER) {
                // 获取最新消息
                List<Message> messages = messageService.listMessages(Message.builder().receiveUserId(loginUser.getId()).build());
                List<MessageVo> result = new ArrayList<>();
                for (Message message : messages) {
                    MessageVo messageVo = MessageVo.builder().message(message).userName(userService.getById(message.getSentUserId()).getName()).build();
                    result.add(messageVo);
                }
                // 获取未通过文章数
                List<Article> articles3 = articleService.listArticles(Article.builder().userId(loginUser.getId()).status(TrashStatus.NOT_IN).type(TypeStatus.ARTICLE).isChecked(CheckStatus.NOT_PASS).build());
                session.setAttribute("noPassCount", articles3.size());
                session.setAttribute("messages", result);
            } else {
                // 获取待审核文章数
                List<Article> articles1 = articleService.listArticles(Article.builder().status(TrashStatus.NOT_IN).type(TypeStatus.ARTICLE).isChecked(CheckStatus.WAIT).build());
                session.setAttribute("waitCount", articles1.size());
                // 获取待审核认证数
                List<Certification> certifications = certificationService.listCertifications(Certification.builder().isChecked(CheckStatus.WAIT).build());
                session.setAttribute("waitUser", certifications.size());
            }
        }
        result = new ResponseResult();
    }
}
