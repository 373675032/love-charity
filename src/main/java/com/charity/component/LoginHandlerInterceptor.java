package com.charity.component;

import com.charity.constant.RoleStatus;
import com.charity.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 登录拦截器
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
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 在目标方式执行之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        String[] array = new String[]{
                "/publishActivity", "/updateActivity", "/deleteActivity", "/deleteBoard", "/checkCertification",
                "/deleteFeedBack", "/deleteLink", "/addLink", "/publishProject",
                "/updateProject", "/deleteProject", "/listUsers", "/editUser", "/admin-user",
                "/admin-certification", "/new-activity", "/edit-activity", "/admin-activity", "/new-project",
                "/admin-project", "/edit-project", "/admin-link", "/admin-feedback", "/admin-board",
                "/admin-project-comment", "/admin-activity-comment", "/admin-article", "/preview"
        };
        List<String> adminPath = new ArrayList<>(Arrays.asList(array));
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null) {
            //未登录,返回登录页面
            response.sendRedirect("/love-charity/login-page");
            return false;
        } else {
            // 如果是普通用户
            if (user.getRole() == RoleStatus.USER) {
                // 访问管理员路径
                if (adminPath.contains(path)) {
                    response.sendRedirect("/love-charity/error401Page");
                    return false;
                }
            }
            // 已登录，放行
            return true;
        }
    }
}
