package com.charity.controller;

import com.alibaba.fastjson.JSONObject;
import com.charity.entity.MessageBoard;
import com.charity.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 留言板控制器
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
public class BoardController extends BaseController {

    private final Logger logger = LogUtils.getInstance(BoardController.class);

    /**
     * 发表留言
     */
    @PostMapping("/publishMessage")
    public String publishMessage(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("content") String content) {
        // 构建留言对象
        MessageBoard message = MessageBoard.builder()
                .name(name).email(email).content(content).gmtCreate(new Date())
                .build();
        if (boardService.insert(message)) {
            logger.info("【成功】，发表留言");
            result.setCode(200);
        } else {
            logger.info("【失败】，发表留言");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除留言
     */
    @GetMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("id") Integer id) {
        if (boardService.deleteById(id)) {
            logger.info("【成功】，删除留言");
            result.setCode(200);
        } else {
            logger.info("【失败】，删除留言");
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }
}
