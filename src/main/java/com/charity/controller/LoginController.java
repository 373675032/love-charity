package com.charity.controller;

import com.charity.dto.ResponseResult;
import com.charity.entity.User;
import com.charity.utils.LogUtils;
import com.charity.utils.MailUtils;
import com.charity.utils.OssUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器
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
public class LoginController extends BaseController {

    private final Logger logger = LogUtils.getInstance(LoginController.class);

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(User user, String code, Map<String, Object> map) {

        logger.info("用户" + user.getName() + "进行注册！");
        String uCode = (String) session.getAttribute(user.getEmail() + "_code");
        if (!code.equals(uCode)) {
            logger.info("用户" + user.getName() + "注册邮箱验证码错误！");
            map.put("errorMsg", "验证码错误~");
            return "background/register";
        }
        // 查找是否存在同名用户
        User u = new User();
        u.setName(user.getName().trim());
        if (userService.listUsers(u).size() == 0) {
            // 表示不存在同名用户注册过,进行注册
            user.setGmtCreate(new Date());
            user.setImg(OssUtils.getRandomFace());
            user.setBirthday(new Date());
            if (userService.insert(user)) {
                logger.info("注册用户成功！当前注册用户" + user);
            } else {
                logger.error("用户" + user.getName() + "注册失败");
                map.put("errorMsg", "注册失败!");
                return "background/register";
            }
            session.removeAttribute(user.getEmail() + "_code");
            user.setRole(0);
            session.setAttribute("loginUser", user);
            return "redirect:/index";
        } else {
            logger.error("用户" + user.getName() + "注册失败，用户名已存在!");
            map.put("errorMsg", "用户名已存在!");
            return "background/register";
        }
    }


    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(User user, Map<String, Object> map) {

        User onlineUser = null;
        List<User> users = this.userService.listUsers(user);
        if (users.size() == 0) {
            logger.info("邮箱用户" + user.getEmail() + "登录失败!");
            // 用户不存在，提示用户是否用户名或密码错误
            map.put("errorMsg", "邮箱或者密码错误！");
            return "background/login";
        } else {
            onlineUser = users.get(0);
            if (onlineUser.getStatus() == 1) {
                logger.info("邮箱用户" + user.getEmail() + "账户已被封禁!");
                map.put("errorMsg", "您的账号已被封禁，请联系管理员！");
                return "background/login";
            } else {
                logger.info("邮箱用户" + user.getEmail() + "账户正常!");
                logger.info("登录成功！用户信息：" + onlineUser);
                session.setAttribute("loginUser", onlineUser);
            }
            return "redirect:/index";
        }
    }


    /**
     * 用户注册时发送验证码
     */
    @ResponseBody
    @RequestMapping("/sendCodeForRegister")
    public ResponseResult sendCodeForRegister(String userName, String email) {
        logger.info("尝试发送邮箱验证码给用户" + userName + "进行注册操作");
        if (userService.getUserByEmail(email) != null) {
            logger.error("用户" + userName + "注册邮箱已经存在!");
            result.setCode(500);
            return result;
        }
        logger.info("开始发送邮件.../n" + "获取的到邮件发送对象为:" + mailSender);
        mailUtils = new MailUtils(mailSender, fromEmail);
        String code = mailUtils.sendCode(email);
        session.setAttribute(email + "_code", code);
        logger.info("发送邮箱验证码给用户" + userName + "成功");
        result.setCode(200);
        return result;
    }

    /**
     * 用户信息修改发送验证码，排除同名查询
     */
    @ResponseBody
    @RequestMapping("/sendCode")
    public ResponseResult sendCode(String email) {
        logger.info("开始发送邮件.../n" + "获取的到邮件发送对象为:" + email);
        mailUtils = new MailUtils(mailSender, fromEmail);
        String code = mailUtils.sendCode(email);
        session.setAttribute("_code", code);
        if (code != null && code != "") {
            result.setCode(200);
            logger.info("发送邮箱验证码到:" + email + "成功！");
        } else {
            result.setCode(500);
            logger.error("发送邮箱验证码到:" + email + "成功！");
        }
        return result;
    }

    /**
     * 忘记密码使用邮箱进行密码的修改 后台忘记
     */
    @PostMapping("/updateUserPwd")
    @ResponseBody
    public ResponseResult updateUserPwd(String email, String code, Map<String, Object> map, String oldPwd, String newPwd) {
        // 如果验证码为空 代表是通过信息页面修改密码
        //          不为空 代表是通过忘记密码修改密码
        User u = new User();
        if (code == null) {
            logger.info("用户" + loginUser.getName() + "通过我的资料修改密码!");
            u.setId(loginUser.getId());
            u.setPassword(oldPwd);
            if (userService.listUsers(u).size() == 0) {
                logger.error("用户通过我的资料修改密码，修改失败，原始密码错误!");
                result.setCode(500);
            } else {
                u.setPassword(newPwd);
                if (userService.update(u)) {
                    logger.info("用户邮箱为" + loginUser.getEmail() + "修改密码成功！");
                    result.setCode(200);
                } else {
                    logger.error("用户邮箱为" + loginUser.getEmail() + "修改密码失败！");
                    result.setCode(500);
                }
            }
            return result;

        } else {
            logger.info("用户邮箱为" + email + "通过忘记密码修改密码!");
            u.setPassword(newPwd);
            u.setEmail(email);
            String uCode = (String) session.getAttribute("_code");
            if (!code.equals(uCode)) {
                result.setCode(500);
                result.setMessage("验证码错误!");
                logger.error("邮箱为" + email + "用户修改密码验证码错误！");
                return result;
            }
            if (userService.updatePwdByEmail(u)) {
                logger.info("邮箱为" + email + "用户修改密码成功！");
                session.removeAttribute("_code");
                result.setCode(200);
            } else {
                result.setMessage("密码找回失败");
                result.setCode(500);
            }
        }
        return result;
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        // 清楚所有会话信息
        session.invalidate();
        return "redirect:/index";
    }
}
