package com.charity.service;

import com.charity.entity.Message;

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
public interface MessageService {

    /**
     * 添加Message
     *
     * @param message 实例对象
     * @return 是否成功
     */
    boolean insert(Message message);

    /**
     * 删除Message
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
    Message getById(Integer id);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     *
     * @return 对象列表
     */
    List<Message> listMessages();

    /**
     * 实体作为筛选条件查询数据
     *
     * @param message 实例对象
     * @return 对象列表
     */
    List<Message> listMessages(Message message);

    /**
     * 修改数据，哪个属性不为空就修改哪个属性
     *
     * @param message 实例对象
     * @return 是否成功
     */
    boolean update(Message message);

    /**
     * 　* : 删除用户id的所有消息记录
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/19 21:38
     * 　* @Param [id]
     * 　* @Return boolean
     */
    boolean deleteAllByUserId(Integer id);
}