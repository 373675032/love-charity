package com.charity.service;

import com.charity.entity.User;

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
public interface UserService {

    /**
     * 添加User
     *
     * @param user 实例对象
     * @return 是否成功
     */
    boolean insert(User user);

    /**
     * 删除User
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User getById(Integer id);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     *
     * @return 对象列表
     */
    List<User> listUsers();

    /**
     * 实体作为筛选条件查询数据
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> listUsers(User user);

    /**
     * 修改数据，哪个属性不为空就修改哪个属性
     *
     * @param user 实例对象
     * @return 是否成功
     */
    boolean update(User user);

    /**
     * 　* : 根据邮箱查找用户
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/13 20:51
     * 　* @Param [email]
     * 　* @Return com.charity.entity.User
     */
    User getUserByEmail(String email);

    /**
     * 　* : 通过用户名查找用户
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/14 15:03
     * 　* @Param [param]
     * 　* @Return com.charity.entity.User
     */
    User getUserByUserName(String param);

    /**
     * 　* : 根据邮箱修改密码
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/16 9:20
     * 　* @Param [user]
     * 　* @Return int
     */
    boolean updatePwdByEmail(User user);

    /**
     * 删除用户的认证信息
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteCertification(Integer id);
}