package com.charity.controller;

import cn.hutool.core.util.ObjectUtil;
import com.charity.constant.*;
import com.charity.entity.*;
import com.charity.utils.LogUtils;
import com.charity.vo.ArticleVo;
import com.charity.vo.CertificationVo;
import com.charity.vo.FeedbackVo;
import com.charity.vo.ProjectVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统控制器
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
@Controller
public class SystemController extends BaseController {

    private final Logger logger = LogUtils.getInstance(SystemController.class);

    /**
     * 前往 前台主页
     */
    @GetMapping({"/index", "/"})
    public String index(Map<String, Object> map) {
        logger.info("请求【前台主页】页面");
        // 获取全部链接
        List<Link> links = linkService.listLinks();
        map.put("links", links);
        return "index";
    }

    /**
     * 前往 前台公益项目页面
     */
    @GetMapping("/projects")
    public String projects(Map<String, Object> map, Integer page) {
        logger.info("请求【前台公益项目】页面");
        // 处理page
        page = ObjectUtils.isEmpty(page) ? 1 : page;
        PageHelper.startPage(page, 6);
        // 获取全部的公益项目
        List<Project> projects = projectService.listProjects();
        PageInfo pageInfo = new PageInfo<Project>(projects);
        // 获取全部链接
        List<Link> links = linkService.listLinks();
        map.put("links", links);
        map.put("projects", projects);
        map.put("totalPage", pageInfo.getPages());
        map.put("page", page);
        return "front/projects";
    }

    /**
     * 前往 阅读公益项目页面
     */
    @GetMapping("/project")
    public String project(Map<String, Object> map, @RequestParam("id") Integer id) {
        logger.info("请求【阅读公益项目】页面，ID：" + id);
        Project project = projectService.getById(id);
        // 获取此活动的全部评论
        Comment comment = Comment.builder()
                .targetId(id).type(CommentType.PROJECT)
                .build();
        // 获取最新的6个公益项目
        PageHelper.startPage(0, 6);
        List<Project> projects = projectService.listProjects();
        List<Comment> comments = commentService.listOneComments(comment);
        // 获取全部链接
        List<Link> links = linkService.listLinks();
        // 获得置顶评论
        Comment first = commentService.getFirst(id);
        map.put("first", dealComment(first, CommentType.PROJECT));
        map.put("links", links);
        map.put("projects", projects);
        map.put("comments", dealComments(comments, CommentType.PROJECT));
        map.put("type", CommentType.PROJECT);
        map.put("project", project);
        return "front/project";
    }

    /**
     * 前往 前台留言板页面
     */
    @GetMapping("/board")
    public String board(Map<String, Object> map) {
        logger.info("请求【前台留言板】页面");
        // 获取全部的留言
        List<MessageBoard> messages = boardService.listMessageBoards();
        // 获取全部的链接
        List<Link> links = linkService.listLinks();
        map.put("messages", messages);
        map.put("links", links);
        return "front/board";
    }

    /**
     * 前往 阅读文章页面
     */
    @GetMapping("/article")
    public String article(Map<String, Object> map, @RequestParam("id") Integer id) {
        logger.info("请求【阅读文章】页面，ID：" + id);
        // 获取需要显示的文章
        Article article = articleService.getById(id);
        if (article.getType() == TypeStatus.ACTIVITY) {
            return "error/400";
        }
        // 增加阅读量
        articleService.addReadCount(id);
        article.setReadCount(article.getReadCount() + 1);
        // 获取文章参加的活动
        Article activity = articleService.getActivityByArticleId(id);
        // 获取此文章的全部评论
        Comment comment = Comment.builder()
                .targetId(id).type(CommentType.ARTICLE)
                .build();
        // 获得置顶评论
        Comment first = commentService.getFirst(id);
        map.put("first", dealComment(first, CommentType.ARTICLE));
        // 获取全部链接
        List<Link> links = linkService.listLinks();
        map.put("links", links);
        List<Comment> comments = commentService.listOneComments(comment);
        map.put("article", article);
        map.put("userName", userService.getById(article.getUserId()).getName());
        map.put("activity", activity);
        map.put("comments", dealComments(comments, CommentType.ARTICLE));
        map.put("type", CommentType.ARTICLE);
        return "front/article";
    }

    /**
     * 前往 前台求助广场页面
     */
    @GetMapping("/articles")
    public String articles(Map<String, Object> map, Integer page) {
        logger.info("请求【前台求助广场】页面");
        // 处理page
        page = ObjectUtils.isEmpty(page) ? 1 : page;
        // 获取全部文章
        PageHelper.startPage(page, 6);
        List<Article> articles = articleService.listArticles(Article.builder().type(TypeStatus.ARTICLE).status(TrashStatus.NOT_IN).isChecked(CheckStatus.PASS).build());
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        // 获取全部链接
        List<Link> links = linkService.listLinks();
        map.put("links", links);
        map.put("articles", dealArticleList(articles));
        map.put("page", page);
        map.put("totalPage", pageInfo.getPages());
        return "front/articles";
    }

    /**
     * 前往 平台活动页面
     */
    @GetMapping("/activities")
    public String activities(Map<String, Object> map, Integer page) {
        logger.info("请求【平台活动】页面");
        // 处理page
        page = ObjectUtils.isEmpty(page) ? 1 : page;
        // 获取全部文章
        PageHelper.startPage(page, 3);
        List<Article> articles = articleService.listAllActivities();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        List<Link> links = linkService.listLinks();
        map.put("links", links);
        map.put("articles", articles);
        map.put("page", page);
        map.put("totalPage", pageInfo.getPages());
        return "front/activities";
    }

    /**
     * 前往 阅读活动页面
     */
    @GetMapping("/activity")
    public String activity(Map<String, Object> map, @RequestParam("id") Integer id) {
        logger.info("请求【阅读活动页面】页面，ID：" + id);
        // 获取需要显示的活动
        Article article = articleService.getById(id);
        if (article.getType() == TypeStatus.ARTICLE) {
            return "error/400";
        }
        // 增加阅读量
        articleService.addReadCount(id);
        article.setReadCount(article.getReadCount() + 1);
        // 获取此活动的全部评论
        Comment comment = Comment.builder()
                .targetId(id).type(CommentType.ACTIVITY)
                .build();
        List<Comment> comments = commentService.listOneComments(comment);
        // 获取全部链接
        List<Link> links = linkService.listLinks();
        // 获得置顶评论
        Comment first = commentService.getFirst(id);
        map.put("first", dealComment(first, CommentType.ACTIVITY));
        map.put("links", links);
        map.put("article", article);
        map.put("comments", dealComments(comments, CommentType.ACTIVITY));
        map.put("type", CommentType.ACTIVITY);
        return "front/article";
    }

    // --------------------------------------------------------------------------------------------------

    /**
     * 前往 管理员主页
     */
    @GetMapping("/admin")
    public String admin() {
        User user = userService.getById(3);
        session.setAttribute("loginUser", user);
        return "redirect:/admin-user";
    }

    /**
     * 前往 用户管理页面
     */
    @GetMapping("/admin-user")
    public String adminUser(Map<String, Object> map) {
        logger.info("请求【用户管理】页面");
        return "background/admin-user";
    }

    /**
     * 前往 认证管理页面
     */
    @GetMapping("/admin-certification")
    public String certification(Map<String, Object> map) {
        logger.info("请求【认证管理】页面");
        List<Certification> certifications = certificationService.listCertifications();
        map.put("certifications", dealCertificationList(certifications));
        return "background/check_certification";
    }

    /**
     * 前往 发布活动页面
     */
    @GetMapping("/new-activity")
    public String newActivity() {
        logger.info("请求【发布活动】页面");
        return "background/new-activity";
    }

    /**
     * 前往 编辑活动页面
     */
    @GetMapping("/edit-activity")
    public String editActivity(@RequestParam("id") Integer id, Map<String, Object> map) {
        logger.info("请求【编辑活动】页面，ID：" + id);
        // 获取活动
        Article article = articleService.getById(id);
        map.put("article", article);
        return "background/edit-activity";
    }

    /**
     * 前往 活动管理页面
     */
    @GetMapping("/admin-activity")
    public String adminActivity(Map<String, Object> map) {
        logger.info("请求【活动管理】页面");
        // 获取全部的活动
        List<Article> activities = articleService.listAllActivities();
        map.put("activities", dealActivityList(activities));
        return "background/my-activity";
    }

    /**
     * 前往 发布公益项目页面
     */
    @GetMapping("/new-project")
    public String newProject() {
        logger.info("请求【发布公益项目】页面");
        return "background/new-project";
    }

    /**
     * 前往 管理公益项目页面
     */
    @GetMapping("/admin-project")
    public String adminProject(Map<String, Object> map) {
        logger.info("请求【管理公益项目】页面");
        // 获取全部的公益项目
        List<Project> projects = projectService.listProjects();
        map.put("projectVos", dealProject(projects));
        return "background/my-projects";
    }

    /**
     * 前往 编辑公益项目页面
     */
    @GetMapping("/edit-project")
    public String editProject(@RequestParam("id") Integer id, Map<String, Object> map) {
        logger.info("请求【编辑公益项目】页面，ID：" + id);
        // 获取需要编辑的公益项目
        Project project = projectService.getById(id);
        map.put("project", project);
        return "background/edit-project";
    }

    /**
     * 前往 管理链接页面
     */
    @GetMapping("/admin-link")
    public String settings(Map<String, Object> map) {
        logger.info("请求【链接管理】页面");
        // 获取全部的链接
        List<Link> links = linkService.listLinks();
        map.put("links", links);
        return "background/links";
    }

    /**
     * 前往 管理反馈页面
     */
    @GetMapping("/admin-feedback")
    public String adminFeedback(Map<String, Object> map) {
        logger.info("请求【反馈管理】页面");
        // 获取全部的反馈
        List<Feedback> feedbacks = feedbackService.listFeedbacks();
        map.put("feedbackVos", dealFeedback(feedbacks));
        return "background/feedback";
    }

    /**
     * 前往 留言管理页面
     */
    @GetMapping("/admin-board")
    public String adminBoard(Map<String, Object> map) {
        logger.info("请求【留言管理】页面");
        // 获取全部的留言
        List<MessageBoard> messages = boardService.listMessageBoards();
        map.put("messages", messages);
        return "background/board";
    }

    /**
     * 前往 项目评论管理页面
     */
    @GetMapping("/admin-project-comment")
    public String projactComments(Map<String, Object> map, String name) {
        logger.info("请求【评论管理】页面");
        Project project = new Project();
        Comment comment = Comment.builder().type(1).build();
        if (name != null) {
            project.setName(name);
            Project p = projectService.listProjects(project).get(0);
            comment.setTargetId(p.getId());
            map.put("name", p.getName());
        } else {
            map.put("name", "全部评论");
        }
        //查询全部项目
        List<Project> projects = projectService.listProjects();
        List<Comment> comments = commentService.listComments(comment);
        map.put("comments", comments);
        map.put("projects", projects);
        return "background/project_comment";
    }

    /**
     * 前往 活动管理页面
     */
    @GetMapping("/admin-activity-comment")
    public String activityComments(Map<String, Object> map, String title) {
        logger.info("请求【活动管理】页面");
        //查询所有的活动
        List<Article> articles = articleService.listAllActivities();
        // 评论筛选
        Comment comment = Comment.builder().build();
        // 文章筛选条件构造
        Article article1 = new Article();
        //储存最后显示的评论
        List<Comment> comments = new ArrayList<>();
        if (title != null) {
            article1.setTitle(title);
            Article article = articleService.listArticles(article1).get(0);
            comment.setTargetId(article.getId());
            comments = commentService.listComments(comment);
            map.put("name", article.getTitle());
        } else {
            for (Article article : articles) {
                comment.setTargetId(article.getId());
                List<Comment> c = commentService.listComments(comment);
                comments.addAll(c);
            }
            map.put("name", "全部评论");
        }
        map.put("comments", comments);
        map.put("articles", articles);
        return "background/activity-comment";
    }

    /**
     * 前往 文章审核页面
     */
    @GetMapping("/admin-article")
    public String check_article(Map<String, Object> map, Integer checked) {
        logger.info("请求【文章审核】页面");
        // 检查check的值
        checked = ObjectUtil.isEmpty(checked) || checked < CheckStatus.WAIT || checked > CheckStatus.NOT_PASS ? CheckStatus.PASS : checked;
        // 构建搜索条件
        Article article = Article.builder()
                .type(TypeStatus.ARTICLE).isChecked(checked).status(TrashStatus.NOT_IN)
                .build();
        List<Article> articles = this.articleService.listArticles(article);
        map.put("allArticles", dealArticleList(articles));
        map.put("checked", checked);
        return "background/check_article";
    }

    /**
     * 前往 预览文章页面
     */
    @GetMapping("/preview")
    public String preview(Map<String, Object> map, @RequestParam("id") Integer id) {
        logger.info("请求【预览文章】页面");
        // 获取需要显示的文章
        Article article = articleService.getById(id);
        if (article.getType() == TypeStatus.ACTIVITY) {
            return "error/400";
        }
        map.put("article", article);
        map.put("userName", userService.getById(article.getUserId()).getName());
        map.put("type", CommentType.ARTICLE);
        return "front/preview";
    }

    // --------------------------------------------------------------------------------------------------

    /**
     * 前往仪表盘页面
     */
    @GetMapping("/dashboard")
    public String dashboard(Map<String, Object> map) {
        logger.info("请求【仪表盘】页面");
        if (loginUser.getRole() == RoleStatus.ADMIN) {
            return "redirect:/admin-user";
        }
        //查询文章的数量
        PageHelper.startPage(0, 3);
        List<Article> articles = articleService.listArticles(Article.builder().userId(loginUser.getId()).isChecked(CheckStatus.PASS).build());
        // 获取分页对象
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        //获得评论的数量
        List<Comment> comments = commentService.listComments(Comment.builder().userId(loginUser.getId()).build());
        //获得所有活动
        PageHelper.startPage(0, 3);
        List<Article> activities = articleService.listAllActivities();
        //查询认证信息
        if (loginUser.getCertificationId() != null) {
            Certification certification = certificationService.getById(loginUser.getCertificationId());
            map.put("certification", certification);
        }
        map.put("articlesSize", pageInfo.getTotal());
        map.put("commentsSize", comments.size());
        map.put("activities", activities);
        map.put("articles", articles);
        return "background/dashboard";
    }

    /**
     * 前往 我的资料页面
     */
    @GetMapping("/profile")
    public String userInfo(Map<String, Object> map) {
        logger.info("请求【我的资料】页面");
        if (ObjectUtil.isNotEmpty(loginUser.getAddress()) && !"-".equals(loginUser.getAddress())) {
            String[] split = loginUser.getAddress().split("-");
            map.put("province", split[0]);
            map.put("city", split[1]);
            map.put("area", split[2]);
        }
        if (null != loginUser.getCertificationId()) {
            Certification byId = certificationService.getById(loginUser.getCertificationId());
            map.put("certification", byId);
        }
        return "background/profile";
    }

    /**
     * 前往 用户认证页面
     */
    @GetMapping("/certification")
    public String certification() {
        logger.info("请求【用户认证】页面");
        // 如果认证前要删除之前的认证
        if (loginUser.getCertificationId() != null) {
            certificationService.deleteById(loginUser.getCertificationId());
        }
        return "background/certification";
    }

    /**
     * 前往 发布文章页面
     */
    @GetMapping("/new-article")
    public String newArticle(Map<String, Object> map) {
        logger.info("请求【发布文章】页面");
        // 获取全部的活动
        List<Article> activities = articleService.listAllActivities();
        map.put("activities", activities);
        return "background/new-article";
    }

    /**
     * 前往 编辑文章页面
     */
    @GetMapping("/edit-article")
    public String editArticle(@RequestParam("id") Integer id, Map<String, Object> map) {
        logger.info("请求【编辑文章】页面，ID：" + id);
        // 获取文章
        Article article = articleService.getById(id);
        // 获取全部的活动
        List<Article> activities = articleService.listAllActivities();
        // 获取文章参与的活动
        Article activity = articleService.getActivityByArticleId(id);
        map.put("activity", activity);
        map.put("article", article);
        map.put("activities", activities);
        return "background/edit-article";
    }

    /**
     * 前往 我的文章页面
     */
    @GetMapping("/my-articles")
    public String myArticles(Map<String, Object> map, Integer checked) {
        logger.info("请求【我的文章】页面，checked：" + checked);
        // 判断是否传入check
        checked = ObjectUtil.isEmpty(checked) ? CheckStatus.PASS : checked;
        // 获取指定审核状态下的文章
        Article article = Article.builder()
                .type(TypeStatus.ARTICLE).userId(loginUser.getId()).status(TrashStatus.NOT_IN).isChecked(checked)
                .build();
        List<Article> articles = articleService.listArticles(article);
        map.put("articles", dealArticleList(articles));
        map.put("checked", checked);
        return "background/my-articles";
    }

    /**
     * 前往 文章回收站页面
     */
    @GetMapping("/trash-article")
    public String trashArticle(Map<String, Object> map) {
        logger.info("请求【文章回收站】页面");
        // 获取指定审核状态下的文章
        Article article = Article.builder()
                .type(TypeStatus.ARTICLE).userId(loginUser.getId()).status(TrashStatus.IS_IN)
                .build();
        List<Article> articles = articleService.listArticles(article);
        map.put("articles", dealArticleList(articles));
        return "background/trash-article";
    }

    /**
     * 前往 文章评论管理页面
     */
    @GetMapping("/my-comments")
    public String listsComments(Map<String, Object> map, String title) {
        logger.info("请求【文章评论】页面");
        Comment comment = Comment.builder().userId(loginUser.getId()).build();
        Article article1 = null;
        if (title != null) {
            article1 = articleService.listArticleByTitle(title, loginUser.getId());
            if (article1 != null) {
                comment.setTargetId(article1.getId());
            }
            map.put("name", article1.getTitle());
        } else {
            map.put("name", "全部评论");
        }
        Article article = Article.builder().userId(loginUser.getId()).build();
        List<Article> articles = articleService.listArticles(article);
        List<Comment> comments = commentService.listComments(comment);
        map.put("comments", comments);
        map.put("articles", articles);
        return "background/comment";
    }

    // --------------------------------------------------------------------------------------------------

    /**
     * 处理评论获取嵌套评论
     */
    public List<Comment> dealComments(List<Comment> list, Integer type) {
        list.forEach(comment -> {
            // 获取此评论的全部回复
            Comment temp = Comment.builder()
                    .replyId(comment.getId()).type(type)
                    .build();
            List<Comment> replys = commentService.listComments(temp);
            comment.setReplyComments(replys);
        });
        return list;
    }

    /**
     * 处理评论获取嵌套评论
     */
    public Comment dealComment(Comment comment, Integer type) {
        if (ObjectUtil.isEmpty(comment)) {
            return null;
        }
        // 获取此评论的全部回复
        Comment temp = Comment.builder()
                .replyId(comment.getId()).type(type)
                .build();
        List<Comment> replys = commentService.listComments(temp);
        comment.setReplyComments(replys);
        return comment;
    }

    /**
     * 处理文章集合获取显示层文章对象
     */
    private List<ArticleVo> dealArticleList(List<Article> list) {
        List<ArticleVo> resultList = new ArrayList<>();
        for (Article article : list) {
            // 获取文章参与活动的名称
            Article activity = articleService.getActivityByArticleId(article.getId());
            String activityStr = "未参加任何活动";
            if (!ObjectUtil.isEmpty(activity)) {
                activityStr = "【 " + activity.getTitle() + " 】";
            }
            // 获取文章的评论数
            Integer comment = commentService.countComment(article.getId());
            // 获取文章的作者
            User user = userService.getById(article.getUserId());
            String userName = user.getName();
            ArticleVo articleVo = ArticleVo.builder()
                    .article(article).userName(userName).activity(activityStr).comment(comment)
                    .build();
            resultList.add(articleVo);
        }
        return resultList;
    }

    /**
     * 处理反馈
     */
    private List<FeedbackVo> dealFeedback(List<Feedback> list) {
        List<FeedbackVo> resultVo = new ArrayList<>();
        for (Feedback feedback : list) {
            User user = userService.getById(feedback.getUserId());
            FeedbackVo feedbackVo = FeedbackVo.builder().feedback(feedback).user(user).build();
            resultVo.add(feedbackVo);
        }
        return resultVo;
    }

    /**
     * 处理活动集合获取显示层活动对象
     */
    private List<ArticleVo> dealActivityList(List<Article> list) {
        List<ArticleVo> resultList = new ArrayList<>();
        for (Article article : list) {
            // 获取活动的评论数
            Integer comment = commentService.countComment(article.getId());
            // 获取活动的作者
            User user = userService.getById(article.getUserId());
            String userName = user.getName();
            ArticleVo articleVo = ArticleVo.builder()
                    .article(article).userName(userName).comment(comment)
                    .build();
            resultList.add(articleVo);
        }
        return resultList;
    }

    /**
     * 处理认证集合获取显示层认证对象
     */
    private List<CertificationVo> dealCertificationList(List<Certification> list) {
        List<CertificationVo> certificationVos = new ArrayList<>();
        for (Certification certification : list) {
            // 生成认证显示层对象设置认证和用户
            CertificationVo certificationVo = CertificationVo.builder()
                    .certification(certification).user(userService.getById(certification.getUserId()))
                    .build();
            certificationVos.add(certificationVo);
        }
        return certificationVos;
    }

    private List<ProjectVo> dealProject(List<Project> list) {
        List<ProjectVo> resultList = new ArrayList<>();
        for (Project project : list) {
            Comment build = Comment.builder().targetId(project.getId()).type(CommentType.PROJECT).build();
            List<Comment> comments = commentService.listComments(build);
            ProjectVo projectVo = ProjectVo.builder().project(project).comments(comments.size()).build();
            resultList.add(projectVo);
        }
        return resultList;
    }
}
