package com.charity.controller;

import com.charity.dto.ResponseResult;
import com.charity.entity.Feedback;
import com.charity.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 反馈控制器
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
public class FeedBackController extends BaseController {

    private final Logger logger = LogUtils.getInstance(FeedBackController.class);

    /**
     * 已登录用户发表反馈
     */
    @PostMapping("/publishFeedback")
    public ResponseResult publishFeedback(@RequestParam("content") String content) {
        Feedback feedback = Feedback.builder().userName(loginUser.getName())
                .img(loginUser.getImg()).content(content).gmtCreate(new Date()).userId(loginUser.getId()).build();
        boolean insert = feedbackService.insert(feedback);
        if (insert) {
            result.setCode(200);
        } else {
            result.setCode(301);
        }
        return result;
    }

    /**
     * 根据id删除反馈
     */
    @GetMapping("/deleteFeedBack")
    public ResponseResult deleteFeedBack(Integer id) {
        boolean b = feedbackService.deleteById(id);
        if (b) {
            result.setCode(200);
        } else {
            result.setCode(500);
        }
        return result;
    }
}
