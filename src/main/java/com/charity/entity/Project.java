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
public class Project implements Serializable {

    private static final long serialVersionUID = 407192645049637331L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 背景
     */
    private String background;

    /**
     * 目的
     */
    private String target;

    /**
     * 主题
     */
    private String theme;

    /**
     * 赞助
     */
    private String sponsor;

    /**
     * 内容
     */
    private String content;

    /**
     * 公益项目的封面
     */
    private String img;

    /**
     * 插入数据的时间
     */
    private Date gmtCreate;

    /**
     * 更新的时间
     */
    private Date gmtModified;

}