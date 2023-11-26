package com.charity.service.impl;

import com.charity.entity.Article;
import com.charity.entity.ArticleActivity;
import com.charity.service.ArticleService;
import com.charity.service.BaseService;
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
@Service("articleService")
public class ArticleServiceImpl extends BaseService implements ArticleService {

    /**
     * @param article 实例对象
     * @return 是否成功
     * 添加Article
     */
    @Override
    public boolean insert(Article article) {
        return articleMapper.insert(article) == 1;
    }

    /**
     * 参加活动
     *
     * @param articleId  文章ID
     * @param activityId 活动ID
     * @return 是否成功
     */
    @Override
    public boolean insertArticleActivity(Integer articleId, Integer activityId) {
        ArticleActivity articleActivity = ArticleActivity.builder().articleId(articleId).activityArticleId(activityId).build();
        return articleActivityMapper.insert(articleActivity) == 1;
    }

    /**
     * 删除参加的活动
     */
    @Override
    public boolean deleteArticleActivity(Integer articleId) {
        return articleActivityMapper.deleteByArticleId(articleId) > 0;
    }

    /**
     * 删除活动
     */
    @Override
    public boolean deleteActivity(Integer activityId) {
        return articleActivityMapper.deleteByActivityId(activityId) > 0;
    }

    /**
     * @param id 主键
     * @return 是否成功
     * 删除Article
     */
    @Override
    public boolean deleteById(Integer id) {
        return articleMapper.deleteById(id) == 1;
    }

    /**
     * @param id 主键
     * @return 实例对象
     * 查询单条数据
     */
    @Override
    public Article getById(Integer id) {
        return articleMapper.getById(id);
    }

    /**
     * @return 对象列表
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    @Override
    public List<Article> listArticles() {
        return articleMapper.listArticles();
    }

    /**
     * @param article 实例对象
     * @return 对象列表
     * 实体作为筛选条件查询数据
     */
    @Override
    public List<Article> listArticles(Article article) {
        return articleMapper.listArticles(article);
    }

    /**
     * @param article 实例对象
     * @return 是否成功
     * 修改数据，哪个属性不为空就修改哪个属性
     */
    @Override
    public boolean update(Article article) {
        return articleMapper.update(article) == 1;
    }

    /**
     * @param id 文章ID
     * @return 对象列表
     * 根据文章ID获取参加的活动
     */
    @Override
    public Article getActivityByArticleId(Integer id) {
        return articleMapper.getActivityByArticleId(id);
    }

    /**
     * @return 对象列表
     * 获取全部的文章
     */
    @Override
    public List<Article> listAllArticles() {
        Article article = Article.builder().type(1).build();
        return articleMapper.listArticles(article);
    }

    /**
     * @return 对象列表
     * 获取全部的活动
     */
    @Override
    public List<Article> listAllActivities() {
        Article article = Article.builder().type(2).build();
        return articleMapper.listArticles(article);
    }

    /**
     * 获取文章的数量
     */
    @Override
    public Integer countArticles() {
        return articleMapper.countArticles(Article.builder().type(1).build());
    }

    /**
     * 获取文章的数量
     */
    @Override
    public Integer countActivities() {
        return articleMapper.countArticles(Article.builder().type(2).build());
    }

    @Override
    public Article listArticleByTitle(String title, Integer id) {
        return articleMapper.listArticleByTitle(title, id);
    }

    @Override
    public int addReadCount(Integer id) {
        return articleMapper.addReadCount(id);
    }

}