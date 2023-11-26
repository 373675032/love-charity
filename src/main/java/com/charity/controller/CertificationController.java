package com.charity.controller;

import com.charity.dto.ResponseResult;
import com.charity.entity.Certification;
import com.charity.entity.Message;
import com.charity.entity.User;
import com.charity.utils.OssUtils;
import com.charity.utils.LogUtils;
import com.charity.utils.MessageUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * 认证控制器
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
public class CertificationController extends BaseController {

    private final Logger logger = LogUtils.getInstance(CertificationController.class);

    /**
     * 添加用户认证信息
     */
    @PostMapping("/insertCertification")
    @ResponseBody
    public ResponseResult insertCertification(Certification certification) {

        logger.info("进入认证方法，参数为" + certification.toString());
        certification.setGmtCreate(new Date());
        //保存用户认证上传的照片
        if (null != session.getAttribute("license")) {
            certification.setProveImg(session.getAttribute("license").toString());
        }
        if (null != session.getAttribute("idCard1")) {
            certification.setIdCardImg0(session.getAttribute("idCard1").toString());
        }
        if (null != session.getAttribute("idCard2")) {
            certification.setIdCardImg1(session.getAttribute("idCard2").toString());
        }
        certification.setUserId(loginUser.getId());
        if (certificationService.insert(certification)) {
            loginUser.setCertificationId(certification.getId());
            // 更新数据库中用户认证
            loginUser.setCertificationId(certification.getId());
            this.userService.update(loginUser);
            // 将认证中的照片信息清楚
            session.removeAttribute("license");
            session.removeAttribute("idCard1");
            session.removeAttribute("idCard2");
            logger.info("用户" + loginUser.getName() + "提交认证信息提交成功！");
            result.setCode(200);
        } else {
            logger.error("用户" + loginUser.getName() + "认证信息提交失败！");
            result.setCode(500);
        }
        return result;
    }

    /**
     * 上传证件图片
     */
    @PostMapping("/uploadLicenseImg")
    @ResponseBody
    public ResponseResult uploadLicenseImg(HttpServletRequest request, String type) throws IOException {
        logger.info("用户" + loginUser.getName() + "上传" + type + "认证图片信息");
        //转型为MultipartHttpServletRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获取文件到map容器中
        MultipartFile file = multipartRequest.getFile("file");
        if (null != file) {
            String licensePath = OssUtils.upload(file, "user");
            logger.info("用户" + loginUser.getName() + "上传证件类型为" + type + ",返回的地址为:" + licensePath);
            if (null != licensePath && licensePath != "") {
                session.setAttribute(type, licensePath);
                logger.info("用户" + loginUser.getName() + "上传" + type + "认证图片信息成功!");
                result.setCode(200);
            } else {
                logger.error("用户" + loginUser.getName() + "上传" + type + "认证图片信息失败!");
                result.setCode(500);
            }
        }
        return result;
    }

    /**
     * 管理员审核用户认证申请
     */
    @PostMapping("/checkCertification")
    @ResponseBody
    public ResponseResult checkCertification(Certification certification) {
        User byId = null;
        if (certificationService.update(certification)) {
            Certification c = certificationService.getById(certification.getId());
            User u = new User();
            u.setCertificationId(c.getId());
            u.setId(c.getUserId());
            if (userService.update(u)) {
                byId = userService.getById(c.getUserId());
                // 消息处理
                String s = MessageUtils.certificationCheckHandle(certification.getIsChecked(), certification.getInfo());
                // 消息的构建 其中3代表项目审核
                Message message = Message.builder().receiveUserId(c.getUserId()).sentUserId(loginUser.getId()).sentUserImg(loginUser.getImg())
                        .type(3).targetId(u.getCertificationId()).content(s).gmtCreate(new Date()).build();
                // 认证信息审核操作成功
                if (messageService.insert(message)) {
                    logger.info("发送认证状态消息给" + byId.getName() + "成功!");
                } else {
                    logger.error("发送认证状态消息给" + byId.getName() + "失败!");
                }
                logger.info("用户" + byId.getName() + "认证状态消息成功，更新用户状态成功!");
                result.setCode(200);
            } else {
                logger.error("用户" + byId.getName() + "认证状态消息成功，更新用户状态失败!");
                result.setCode(500);
            }
        } else {
            logger.error("用户" + byId.getName() + "消息认证操作失败!");
            result.setCode(500);
        }
        return result;
    }
}
