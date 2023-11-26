package com.charity.service;

import com.charity.mapper.*;

import javax.annotation.Resource;

/**
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
public class BaseService {

    @Resource
    protected ArticleActivityMapper articleActivityMapper;

    @Resource
    protected ArticleMapper articleMapper;

    @Resource
    protected CertificationMapper certificationMapper;

    @Resource
    protected CommentMapper commentMapper;

    @Resource
    protected FeedbackMapper feedbackMapper;

    @Resource
    protected LinkMapper linkMapper;

    @Resource
    protected MessageMapper messageMapper;

    @Resource
    protected ProjectMapper projectMapper;

    @Resource
    protected UserMapper userMapper;

    @Resource
    protected MessageBoardMapper messageBoardMapper;

}
