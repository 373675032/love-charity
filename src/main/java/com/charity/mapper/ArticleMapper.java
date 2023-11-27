package com.charity.mapper;

import com.charity.entity.Article;
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
public interface ArticleMapper {

    /**
     * @param article 实例对象
     * @return 影响行数
     * 添加Article
     */
    int insert(Article article);

    /**
     * @param id 主键
     * @return 影响行数
     * 删除Article
     */
    int deleteById(Integer id);

    /**
     * @param uid 用户主键
     * @return 影响行数
     * 删除Article
     */
    int deleteByUserId(Integer uid);

    /**
     * @param id 主键
     * @return 实例对象
     * 查询单条数据
     */
    Article getById(Integer id);

    /**
     * @return 对象列表
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Article> listArticles();

    /**
     * @param article 实例对象
     * @return 对象列表
     * 实体作为筛选条件查询数据
     */
    List<Article> listArticles(Article article);

    /**
     * @param article
     * @return 影响行数
     * 修改Article， 根据 article 的主键修改数据
     */
    int update(Article article);

    /**
     * 获取文章的数量
     */
    Integer countArticles(Article article);

    /**
     * 根据文章ID获取参加的活动
     */
    Article getActivityByArticleId(Integer id);

    /**
     * 根据文章标题，用户id获得文章
     */
    Article listArticleByTitle(@Param("title") String title, @Param("id") Integer id);

    /**
     * 增加阅读量
     */
    int addReadCount(Integer id);
}