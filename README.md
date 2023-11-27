## 前言

哈喽兄弟们，好久不见哦～

最近整理了一下之前写过的一些小项目/毕业设计。发现还是有很多存货的，虽然这些项目普遍都写的比较简单，但想一想既然放在电脑里面也吃灰，那么还不如开源分享出去，没准还可以帮助到一些小白新手。

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202310292144885.png)

本期就在其中选取了一个慈善公益系统的项目进行分享。在项目资料中，包括了项目的完整源代码、前端模板项目、文档介绍、数据库结构以及一些图片素材。

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271748993.png)

> 项目资料获取可前往微信公众号：【薛伟同学】，后台回复：【慈善公益平台】

## 项目介绍

爱慈善公益平台（love-charity）是一个基于 SpringBoot 开发的标准 Java Web 项目。整体页面非常的简约大气，项目的完整度较高，是一个偏向公益论坛的系统。非常适合刚刚接触学习 SpringBoot 的技术小白学习，也非常适合学校中的项目答辩或者毕业设计。

### 角色介绍

系统共设计了三个角色：游客、普通用户、管理员。

1. 游客：尚未进行注册和登录。具备登录注册、浏览系统、查询公益项目、查看求助文章、留言等权限。
2. 普通用户：用户角色为普通用户，具备登录、修改资料、修改密码、提交社团/企业认证、评论公益项目、评论求助文章、评论管理、发布求助文章、提交反馈、留言等权限。
3. 管理员：用户角色为管理员，具备登录、修改资料、修改密码、评论公益项目、评论求助文章、评论管理、发布求助文章、发布公益项目、发布平台活动、用户管理、用户认证审核、文章审核、链接管理、反馈管理、留言管理等权限。

### 功能介绍

#### 游客功能介绍

| 功能模块     | 功能描述                                                     |
| ------------ | ------------------------------------------------------------ |
| 登录注册方面 | 注册成为系统用户                                             |
| 系统主页     | 浏览系统主页、留言、浏览公益项目、浏览平台活动、浏览求助文章，每次浏览都会累积访问量 |

#### 普通用户功能介绍

| 功能模块     | 功能描述                                                     |
| ------------ | ------------------------------------------------------------ |
| 登录注册方面 | 填写用户信息进行账号注册（邮箱验证码）、使用邮箱密码进行登录、忘记密码（邮箱验证码并重置）、退出登录 |
| 个人资料方面 | 修改个人资料（姓名、所在地、邮箱、手机号、提交认证等）、修改登录密码、修改头像 |
| 仪表盘       | 数据统计、最新活动、最新求助文章                             |
| 求助文章     | 标题、编写求助文章（Markdown编辑器）、上传封面、关联平台活动、文章搜索、重新编辑 |
| 审核队列     | 等待审核、已审核                                             |
| 文章回收站   | 删除文章后自动加入回收站，可再次删除、移出回收站             |
| 评论管理     | 置顶评论、删除评论、查询详情                                 |
| 通知消息     | 查看消息、全部已读                                           |

#### 管理员功能介绍

| 功能模块     | 功能描述                                                     |
| ------------ | ------------------------------------------------------------ |
| 登录注册方面 | 填写用户信息进行账号注册（邮箱验证码）、使用邮箱密码进行登录、忘记密码（邮箱验证码并重置）、退出登录 |
| 个人资料方面 | 修改个人资料（姓名、所在地、邮箱、手机号、认证等）、修改登录密码、修改头像 |
| 用户管理     | 修改用户资料、删除用户、多条件查询                           |
| 用户认证审核 | 查看、审核用户认证资料                                       |
| 平台活动     | 查询、搜索、发表平台活动（标题、内容（Markdown）、封面）、编辑、删除 |
| 公益项目     | 查询、搜索、发表公益项目（标题、内容（Markdown）、封面、背景、主题、目的、赞助）、编辑、删除 |
| 评论管理     | 平台活动、公益项目评论管理、指定、详情、删除                 |
| 求助文章审核 | 审核由普通用户提交的求助文章                                 |
| 链接管理     | 添加主页置顶链接、友情链接                                   |
| 留言管理     | 对留言进行管理、包含搜索、查看、删除                         |
| 反馈管理     | 对反馈进行管理、包含搜索、查看、删除                         |

## 技术介绍

### 前端

| 名称               | 描述                                                         |
| :----------------- | ------------------------------------------------------------ |
| HTML、CSS          | 用于设计网页的内容和样式                                     |
| JavaScript、JQuery | 作为开发 Web 页面的脚本语言，为网页添加各式各样的动态功能，为用户提供更流畅美观的浏览效果 |
| Bootstrap          | 基于 HTML、CSS、JavaScript 开发的简洁、直观、强悍的前端开发框架，使得 Web 开发更加快捷 |
| 前端模板           | 前台主页模板和后台管理模板，均基于 Bootstrap 开发，都分享在了项目目录下 |
| EditMd             | Markdown 编辑器组件                                          |

### 后端

| 名称       | 描述                                                         |
| ---------- | ------------------------------------------------------------ |
| SpringBoot | SpringBoot 是由 Pivotal 团队提供的全新框架，其设计目的是用来简化新 Spring 应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，SpringBoot 致力于在蓬勃发展的快速应用开发领域成为领导者。 |
| SpringMVC  | Spring MVC 属于 SpringFrameWork 的后续产品，已经融合在 Spring Web Flow 里面。Spring 框架提供了构建 Web 应用程序的全功能 MVC 模块。 |
| MyBatis    | MyBatis 是一个 Java 持久化框架，它可以帮助开发者更轻松地管理数据库访问和SQL映射。它提供了一种简单且灵活的方式来进行数据库操作，同时还支持动态 SQL、缓存和批量操作等功能。 |
| Thymeleaf  | Thymeleaf 是一个流行的模板引擎，该模板引擎采用 Java 语言开发的，用于渲染 XML/XHTML/HTML5 内容的模板引擎。类似 JSP、Velocity、FreeMaker 等，它也可以轻易的与 Spring MVC 等 Web 框架进行集成作为 Web 应用的模板引擎。 |
| Druid      | Druid 是一个高效的数据查询系统，主要解决的是对于大量的基于时序的数据进行聚合查询。数据可以实时摄入，进入到 Druid 后立即可查，同时数据是几乎是不可变。通常是基于时序的事实事件，事实发生后进入 Druid，外部系统就可以对该事实进行查询。 |

### 开发工具与环境

- **开发工具**

    - IntelliJ IDEA 2019.2.2 主要进行系统的开发、系统调试等
        - 使用到的插件
            - Lombok：使用注解代替在实体类中添加的 get、set方法、toString 方法等
    - WebStorm 2019.2.1 主要进行前端页面的开发。
    - Navcat 主要进行数据库的连接、建库建表、系统调试等。

- **开发环境**

    - JDK 1.8
    - Maven 3.6
    - MySQL 5.7

- **阿里云 OSS 对象存储**

  阿里云对象存储 OSS（Object Storage Service）是一款海量、安全、低成本、高可靠的云存储服务，对于我们这个项目而言，所有的二进制文件，包括头像、用户上传的文件都是存储到了 OSS 里面（调用相关的工具类），在数据库中只是保存了文件的 URL 地址。通过这个 URL 地址就可以获取、下载指定文件。

  https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg

## 项目结构

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271826468.png)

- src：源码目录
- src/main/java：源码主体目录
- src/main/java/xxx/component：java常用组件存放地址，比如登录拦截器【拦截器】
- src/main/java/xxx/config：java配置类存放地址，MVC配置【配置包】
- src/main/java/xxx/constant：java常量类存放地址，MVC配置【常量包】
- src/main/java/xxx/controller：java控制器类存放地址【控制层】
- src/main/java/xxx/dto：数据传输层存放地址，【接口响应封装】
- src/main/java/xxx/entity：java实体类存放地址【模型】
- src/main/java/xxx/mapper：MyBatis的映射接口存放地址，【数据访问层】
- src/main/java/xxx/service：java业务层接口存放地址【业务逻辑抽象层】
- src/main/java/xxx/utils：java工具类存放地址【工具类】
- src/main/java/xxx/vo：视图对象类存放地址【视图实体】
- src/resources：资源文件存放目录
- src/main/resources/mybatis：MyBatis配置文件位置
- src/main/resources/mybatis/mybatis-config.xml：MyBatis全局配置文件
- src/main/resources/mybatis/mapper：MyBatis SQL语句配置文件目录
- src/main/resources/love_charity.sql：数据库脚本
- src/main/resources/static：前端静态资源存放地址
- src/main/resources/templates：前端页面存放地址
- src/main/resources/application.yml：SpringBoot配置文件
- pom.xml：Maven项目依赖管理文件

> 注意！如果想要部署本项目，你需要修改两个文件：
>
> 1. SpringBoot配置文件数据库配置：src/main/resources/application.yml
> 2. SpringBoot配置文件邮件服务配置：src/main/resources/application.yml
> 3. 阿里云对象存储配置：src/main/java/com/charity/utils/OssUtils.java
>
> 注意 MySQL 的版本是 5.7，使用其它版本可能会出现其它未知问题。

## 系统截图

### 系统主页

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271837819.png)

### 系统注册

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271837698.png)

### 找回密码

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271838228.png)

### 公益项目

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271838340.png)

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271841550.png)

### 平台活动

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271840599.png)

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271841951.png)

### 留言

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271842229.png)

### 普通用户仪表盘

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271842779.png)

### 普通用户发布文章

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271843508.png)

### 管理员用户管理

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271843399.png)

### 管理员公益项目发布

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271845982.png)

### 管理员认证审核

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271843910.png)

### 管理员链接管理

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271844490.png)

### 管理员评论管理

![](https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202311271844452.png)

## 开源声明

```
==========================================================================
郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
==========================================================================
B站账号：薛伟同学
微信公众号：薛伟同学
作者博客：http://xuewei.world
==========================================================================
陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
希望各位朋友擦亮慧眼，谨防上当受骗！
==========================================================================
```

> 本项目已在 Github 开源，喜欢的话记得帮忙点亮 Star，不求打赏，免费分享，只求你一个免费的👍，你的支持是我做下去的动力。
>
> https://github.com/373675032/love-charity

> 微信公众号：【薛伟同学】，后台回复：【慈善公益平台】获取项目源码以及资料文档

