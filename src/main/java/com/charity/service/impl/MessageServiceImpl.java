package com.charity.service.impl;

import com.charity.entity.Message;
import com.charity.service.BaseService;
import com.charity.service.MessageService;
import org.springframework.stereotype.Service;

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
@Service("messageService")
public class MessageServiceImpl extends BaseService implements MessageService {

    /**
     * 添加Message
     *
     * @param message 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Message message) {
        return messageMapper.insert(message) == 1;
    }

    /**
     * 删除Message
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return messageMapper.deleteById(id) == 1;
    }

    /**
     * 查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Message getById(Integer id) {
        return messageMapper.getById(id);
    }

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     *
     * @return 对象列表
     */
    @Override
    public List<Message> listMessages() {
        return messageMapper.listMessages();
    }

    /**
     * 实体作为筛选条件查询数据
     *
     * @param message 实例对象
     * @return 对象列表
     */
    @Override
    public List<Message> listMessages(Message message) {
        return messageMapper.listMessages(message);
    }

    /**
     * 修改数据，哪个属性不为空就修改哪个属性
     *
     * @param message 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Message message) {
        return messageMapper.update(message) == 1;
    }

    @Override
    public boolean deleteAllByUserId(Integer id) {
        return messageMapper.deleteAllByUserId(id);
    }

}