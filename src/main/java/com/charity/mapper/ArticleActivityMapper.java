package com.charity.mapper;

import com.charity.entity.ArticleActivity;
import org.apache.ibatis.annotations.Mapper;

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
public interface ArticleActivityMapper {

    /**
     * @param articleActivity 实例对象
     * @return 影响行数
     * 添加ArticleActivity
     */
    int insert(ArticleActivity articleActivity);

    /**
     * @param id 主键
     * @return 影响行数
     * 删除ArticleActivity
     */
    int deleteById(Integer id);

    /**
     * @param articleId 文章ID
     * @return 影响行数
     * 删除ArticleActivity
     */
    int deleteByArticleId(Integer articleId);

    /**
     * @param activityId 活动ID
     * @return 影响行数
     * 删除ArticleActivity
     */
    int deleteByActivityId(Integer activityId);

    /**
     * @param id 主键
     * @return 实例对象
     * 查询单条数据
     */
    ArticleActivity getById(Integer id);

    /**
     * @return 对象列表
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<ArticleActivity> listArticleActivitys();

    /**
     * @param articleActivity 实例对象
     * @return 对象列表
     * 实体作为筛选条件查询数据
     */
    List<ArticleActivity> listArticleActivitys(ArticleActivity articleActivity);

    /**
     * @param activityId 活动文章的ID
     * @return 文章数
     * 获取参与活动的文章数量（已发布状态）
     */
    Integer countArticle(Integer activityId);

    /**
     * @param articleActivity
     * @return 影响行数
     * 修改ArticleActivity， 根据 articleActivity 的主键修改数据
     */
    int update(ArticleActivity articleActivity);


}