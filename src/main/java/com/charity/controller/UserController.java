package com.charity.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.charity.dto.ResponseResult;
import com.charity.entity.Article;
import com.charity.entity.Certification;
import com.charity.entity.User;
import com.charity.utils.LogUtils;
import com.charity.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
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
public class UserController extends BaseController {

    private final Logger logger = LogUtils.getInstance(UserController.class);

    /**
     * 修改用户的信息
     */
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public ResponseResult updateUserInfo(User user, String code, String input_province, String bornDate) throws ParseException {

        logger.info("用户" + loginUser.getName() + "修改资料");
        String email = user.getEmail();
        user.setEmail(null);
        // 如果有验证码 说明尝试修改邮箱
        if (null != code && code != "") {
            logger.info("用户" + loginUser.getName() + "尝试修改邮箱！");
            String uCode = (String) session.getAttribute("_code");
            if (!code.equals(uCode)) {
                logger.error("用户" + loginUser.getName() + "修改邮箱验证码错误！");
                result.setCode(500);
                return result;
            } else {
                logger.info("用户" + loginUser.getName() + "修改邮箱验证码正确！");
                session.removeAttribute(user.getEmail() + "_code");
                user.setEmail(email);
            }
        }
        if (null != input_province && input_province != "") {
            user.setAddress(input_province);
        }
        if (null != bornDate && bornDate != "") {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(bornDate));
        }
        user.setId(loginUser.getId());
        if (userService.update(user)) {
            User u = userService.getById(loginUser.getId());
            //对session中数据进行更新
            session.setAttribute("loginUser", u);
            //取出位置信息用于前台显示
            if (null != u.getAddress() && !"-".equals(u.getAddress())) {
                String[] split = u.getAddress().split("-");
                session.setAttribute("province", split[0]);
                session.setAttribute("city", split[1]);
                session.setAttribute("area", split[2]);
            }
            logger.info("用户" + loginUser.getName() + "修改资料成功！");
            result.setCode(200);
        } else {
            logger.error("用户" + loginUser.getName() + "修改信息失败！");
            result.setCode(500);
        }
        return result;
    }

    /**
     * 通过用户属性查找用户
     */
    @PostMapping("/getUserByEmailOrName")
    @ResponseBody
    public ResponseResult getUserByEmailOrName(String param) {

        // 1.先进行参数判断，如果包含'@'符号，则表示是邮箱
        String isEmail = "@";
        User user = null;
        if (param.contains(isEmail)) {
            logger.info("通过邮箱查找用户!");
            // 是邮箱
            user = userService.getUserByEmail(param);
        } else {
            logger.info("通过用户名查找用户!");
            // 是用户名
            user = userService.getUserByUserName(param);
        }
        if (user == null) {
            result.setCode(500);
        } else {
            result.setCode(200);
        }
        return result;
    }

    /**
     * 查询所有用户
     */
    @GetMapping("/listUsers")
    @ResponseBody
    public String listUsers(Integer page, Integer rows, String searchField, String searchString, String searchOper) {
        System.out.println("page = " + page + ", rows = " + rows + ", searchField = " + searchField + ", searchString = " + searchString + ", searchOper = " + searchOper);
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(searchString)) {
            List<User> users = new ArrayList<>();
            if ("id".equals(searchField)) {
                User user = userService.getById(Integer.parseInt(searchString));
                if (!ObjectUtil.isEmpty(user)) {
                    users.add(user);
                }
            } else if ("name".equals(searchField)) {
                users = userService.listUsers(User.builder().name(searchString).build());
            } else if ("email".equals(searchField)) {
                users = userService.listUsers(User.builder().email(searchString).build());
            } else if ("phone".equals(searchField)) {
                users = userService.listUsers(User.builder().phone(searchString).build());
            }
            PageInfo<User> pageInfo = new PageInfo<>(users);
            // 将查询结果放入map
            map.put("rows", dealUserList(users));
            // 总页数
            map.put("total", pageInfo.getPages());
            // 总条数
            map.put("records", pageInfo.getTotal());
            // 返回结果
            return JSONObject.toJSONString(map);
        } else {
            PageHelper.startPage(page, rows);
            // 查询所有用户
            List<User> users = userService.listUsers();
            PageInfo<User> pageInfo = new PageInfo<>(users);
            // 将查询结果放入map
            map.put("rows", dealUserList(users));
            // 总页数
            map.put("total", pageInfo.getPages());
            // 总条数
            map.put("records", pageInfo.getTotal());
            // 返回结果
            return JSONObject.toJSONString(map);
        }
    }

    /**
     * 编辑用户
     */
    @PostMapping("/editUser")
    public void editUser(UserVo user, String oper) {
        System.err.println("oper = " + oper);
        // 编辑操作
        if ("edit".equals(oper)) {
            User target = userService.getById(user.getId());
            target.setName(user.getName());
            target.setPassword(user.getPassword());
            target.setEmail(user.getEmail());
            target.setPhone(user.getPhone());
            target.setStatus(Integer.parseInt(user.getStatus()));
            target.setRole(Integer.parseInt(user.getRole()));
            userService.update(target);
        }
        // 删除操作
        if ("del".equals(oper)) {
            if (userService.deleteById(user.getId())) {
                // 获取此用户发表的所有文章
                List<Article> articles = articleService.listArticles(Article.builder().userId(user.getId()).build());
                // 删除文章和活动记录
                articles.forEach(article -> {
                    if (articleService.deleteById(article.getId())) {
                        articleService.deleteActivity(article.getId());
                    }
                });
            }
        }
    }

    /**
     * 将User集合转为UserVo集合
     */
    List<UserVo> dealUserList(List<User> users) {
        List<UserVo> list = new ArrayList<>();
        for (User u : users) {
            String certificationStr = "-";
            // 获取用户的认证信息
            if (u.getCertificationId() != null) {
                Certification certification = certificationService.getById(u.getCertificationId());
                if (certification.getIsChecked() == 1) {
                    certificationStr = certification.getShowName();
                }
            }
            // 日期格式化对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            // 构建对象
            UserVo userVo = UserVo.builder()
                    .id(u.getId()).name(u.getName()).password(u.getPassword()).email(u.getEmail()).phone(u.getPhone()).address(u.getAddress())
                    .sex(u.getSex() == 0 ? "女" : "男").career(u.getCareer()).birthday(sdf.format(u.getBirthday())).img(u.getImg()).certification(certificationStr)
                    .gmtCreate(sdf.format(u.getGmtCreate())).role(u.getRole() == 0 ? "普通用户" : "管理员").status(u.getStatus() == 0 ? "正常" : "封禁")
                    .build();
            // 添加结果
            list.add(userVo);
        }
        return list;
    }
}
