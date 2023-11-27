package com.charity.config;

import com.charity.component.LoginHandlerInterceptor;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC 配置
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
@Configuration
public class MvcConfig implements WebMvcConfigurer, ErrorPageRegistrar {

    /**
     * 注册视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 注册登录，忘记密码页面
        registry.addViewController("/login-page").setViewName("background/login");
        registry.addViewController("/register-page").setViewName("background/register");
        registry.addViewController("/forget-password").setViewName("background/forget-pwd");
        registry.addViewController("/password").setViewName("background/password");
        // 个人信息页面跳转
        registry.addViewController("/feedback").setViewName("front/feedback");
        // 注册错误页面
        registry.addViewController("/error400Page").setViewName("error/400");
        registry.addViewController("/error401Page").setViewName("error/401");
        registry.addViewController("/error404Page").setViewName("error/404");
        registry.addViewController("/error500Page").setViewName("error/500");
    }

    /**
     * 配置错误页面
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error400Page");
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error401Page");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error404Page");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error500Page");
        registry.addErrorPages(error400Page, error401Page, error404Page, error500Page);
    }

    /**
     * 注册登录拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                // 拦截的请求
                .addPathPatterns("/**")
                // 不拦截的请求（放行）
                .excludePathPatterns(
                        "/", "/index", "/login-page", "/register-page",
                        "/login", "/register", "/sendCodeForRegister", "/updateUserPwd", "/sendCode",
                        "/articles", "/article", "/project", "/projects",
                        "/activity", "/activities", "/publishMessage", "/board", "/forget-password",
                        "/error400Page", "/error401Page", "/error404Page", "/error500Page",
                        "/**/front/**", "/asserts/**", "/**/*.css", "/**/*.js", "/**/*.png ",
                        "/**/*.jpg", "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
    }
}
