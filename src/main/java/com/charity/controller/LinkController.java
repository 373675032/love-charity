package com.charity.controller;

import com.alibaba.fastjson.JSONObject;
import com.charity.entity.Link;
import com.charity.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 链接控制器
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
public class LinkController extends BaseController {

    private final Logger logger = LogUtils.getInstance(LinkController.class);

    /**
     * 删除链接
     */
    @GetMapping("/deleteLink")
    public String deleteLink(@RequestParam("id") Integer id) {
        if (linkService.deleteById(id)) {
            logger.info("【成功】：删除链接");
            result.setCode(200);
        } else {
            logger.info("【失败】：删除链接");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 添加菜单/友情链接
     */
    @PostMapping("/addLink")
    public String addLink(@RequestParam("name") String name, @RequestParam("url") String url, @RequestParam("type") Integer type) {
        // 生成链接对象
        Link link = Link.builder()
                .name(name).targetUrl(url).type(type).gmtCreate(new Date())
                .build();
        if (linkService.insert(link)) {
            logger.info("【成功】：添加菜单/友情链接");
            result.setCode(200);
        } else {
            logger.info("【失败】：添加菜单/友情链接");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }
}
