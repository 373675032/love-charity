package com.charity.mapper;

import com.charity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface UserMapper {

    /**
     * 添加User
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 删除User
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 删除用户的认证信息
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteCertification(Integer id);

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
     * 修改User，根据 user 的主键修改数据
     *
     * @param user
     * @return 影响行数
     */
    int update(User user);

    /**
     * 　* : 根据邮箱查用户
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/13 20:57
     * 　* @Param [email]
     * 　* @Return com.charity.entity.User
     */
    User getUserByEmail(@Param("email") String email);

    /**
     * 　* : 根据邮箱查用户
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/13 20:57
     * 　* @Param [email]
     * 　* @Return com.charity.entity.User
     */
    User getUserByName(@Param("name") String name);

    /**
     * 　* : 根据邮箱修改密码
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/16 9:20
     * 　* @Param [user]
     * 　* @Return int
     */
    int updatePwdByEmail(User user);

}