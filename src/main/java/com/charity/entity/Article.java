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
public class Article implements Serializable {

    private static final long serialVersionUID = 591577028509822181L;

    /**
     * 文章ID
     */
    private Integer id;

    /**
     * 作者用户ID
     */
    private Integer userId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 文章状态：发布（1），回收站（2）
     */
    private Integer status;

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 封面图片
     */
    private String img;

    /**
     * 类型：文章（1），活动（2）
     */
    private Integer type;

    /**
     * 文章审核，0：未审核，1：审核通过，2：审核不通过
     */
    private Integer isChecked;

    /**
     * 审核未通过返回的消息
     */
    private String info;

    /**
     * 数据插入时间，即发布时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

}