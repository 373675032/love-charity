package com.charity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {

    private static final long serialVersionUID = 170364374295614279L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 接收消息的用户ID
     */
    private Integer receiveUserId;

    /**
     * 发送消息的用户ID
     */
    private Integer sentUserId;

    /**
     * 类型：点赞（0）/评论（1）
     */
    private Integer type;

    /**
     * 目标ID：公益项目/文章ID
     */
    private Integer targetId;

    /**
     * 标题
     */
    private String title;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * 是否已读：未读（0），已读（1）
     */
    private Integer isRead;

    /**
     * 插入数据的时间，即发表评论的时间
     */
    private Date gmtCreate;

    /**
     * 更新的时间
     */
    private Date gmtModified;

    /**
     * 发送者用户头像
     */
    private String sentUserImg;

}