package com.charity.controller;

import com.alibaba.fastjson.JSONObject;
import com.charity.constant.CommentType;
import com.charity.entity.Project;
import com.charity.utils.OssUtils;
import com.charity.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 公益项目控制器
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
@RestController
public class ProjectController extends BaseController {

    private final Logger logger = LogUtils.getInstance(ProjectController.class);

    /**
     * 发布公益项目
     */
    @PostMapping("/publishProject")
    public String publishProject(Project project, String imgPath) {
        // 为项目设置封面，如果为空就生成随机封面
        project.setImg(StringUtils.isEmpty(imgPath) ? OssUtils.getRandomFace() : imgPath);
        project.setGmtCreate(new Date());
        project.setGmtModified(new Date());
        if (projectService.insert(project)) {
            logger.info("【成功】：发布公益项目");
            result.setCode(200);
        } else {
            logger.info("【失败】：发布公益项目");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 修改公益项目
     */
    @PostMapping("/updateProject")
    public String updateProject(Project project, String imgPath) {
        // 为活动设置封面，如果为空就生成随机封面
        project.setImg(StringUtils.isEmpty(imgPath) ? OssUtils.getRandomFace() : imgPath);
        project.setGmtCreate(new Date());
        project.setGmtModified(new Date());
        if (projectService.update(project)) {
            logger.info("【成功】：修改公益项目");
            result.setCode(200);
        } else {
            logger.info("【失败】：修改公益项目");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除公益项目
     */
    @GetMapping("/deleteProject")
    public String deleteProject(@RequestParam("id") Integer id) {
        if (projectService.deleteById(id)) {
            result.setCode(200);
            logger.info("【成功】：删除公益项目");
            // 删除评论
            commentService.deleteByTargetIdAndType(id, CommentType.PROJECT);
        } else {
            result.setCode(500);
            logger.info("【失败】：删除公益项目");
        }
        return JSONObject.toJSONString(result);
    }
}
