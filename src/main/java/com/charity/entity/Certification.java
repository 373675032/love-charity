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
public class Certification implements Serializable {

    private static final long serialVersionUID = 317068900090604466L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 领导者姓名
     */
    private String leaderName;

    /**
     * 领导者身份证号码
     */
    private String idCard;

    /**
     * 机构类型
     */
    private String type;

    /**
     * 认证称号（XXX官方账户）
     */
    private String showName;

    /**
     * 单位证明/营业执照图片地址
     */
    private String proveImg;

    /**
     * 领导者身份证照片（反面）
     */
    private String idCardImg0;

    /**
     * 领导者身份证照片（正面）
     */
    private String idCardImg1;

    /**
     * 审核（0），成功（1），失败（2）
     */
    private Integer isChecked;

    /**
     * 认证未通过返回的消息
     */
    private String info;

    /**
     * 插入数据的时间
     */
    private Date gmtCreate;

    /**
     * 更新的时间
     */
    private Date gmtModified;

}