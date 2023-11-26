package com.charity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class Comment implements Serializable {

    private static final long serialVersionUID = -63892952404673226L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 发表评论用户的ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像地址
     */
    private String img;

    /**
     * 目标ID：可以是项目ID 、文章ID
     */
    private Integer targetId;

    /**
     * 类型：项目（1），文章（2）
     */
    private Integer type;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复评论的ID
     */
    private Integer replyId;

    /**
     * 是否置顶：置顶（1），不置顶（0）
     */
    private Integer isFirst;

    /**
     * 插入数据的时间，即发表评论的时间
     */
    private Date gmtCreate;

    /**
     * 更新的时间
     */
    private Date gmtModified;

    /**
     * 所有回复的评论
     */
    private List<Comment> replyComments;

}