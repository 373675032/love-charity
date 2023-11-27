package com.charity.mapper;

import com.charity.entity.Comment;
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
public interface CommentMapper {

    /**
     * @param comment 实例对象
     * @return 影响行数
     * 添加Comment
     */
    int insert(Comment comment);

    /**
     * @param id 主键
     * @return 影响行数
     * 删除Comment
     */
    int deleteById(Integer id);

    /**
     * @param id 主键
     * @return 实例对象
     * 查询单条数据
     */
    Comment getById(Integer id);

    /**
     * 获取目标的指定评论
     */
    Comment getFirst(Integer id);

    /**
     * @return 对象列表
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Comment> listComments();

    /**
     * @param comment 实例对象
     * @return 对象列表
     * 实体作为筛选条件查询数据
     */
    List<Comment> listComments(Comment comment);

    /**
     * @param comment
     * @return 影响行数
     * 修改Comment， 根据 comment 的主键修改数据
     */
    int update(Comment comment);

    /**
     * @param articleId 文章ID
     * @return 总数
     * 获取文章的评论数
     */
    Integer countComment(Integer articleId);

    /**
     * 　* : 通过目标id 自定义sql获得一级评论
     * 　* @Author LiMeiyue
     * 　* @date 2020/10/19 21:07
     * 　* @Param [comment]
     * 　* @Return java.util.List<com.charity.entity.Comment>
     */
    List<Comment> listOneComments(Comment comment);

    /**
     * 根据目标ID和类型删除评论
     */
    int deleteByTargetIdAndType(@Param("id") Integer id, @Param("type") Integer type);
}