package com.charity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 职业
     */
    private String career;
    /**
     * 认证ID
     */
    private String certification;
    /**
     * 头像地址
     */
    private String img;
    /**
     * 状态
     */
    private String status;
    /**
     * 注册时间
     */
    private String gmtCreate;
    /**
     * 用户角色
     */
    private String role;
}
