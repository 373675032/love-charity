package com.charity.utils;

import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮箱工具
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
public class MailUtils {

    Logger logger = LogUtils.getInstance(MailUtils.class);
    //邮件发送器
    private final JavaMailSenderImpl mailSender;

    public MailUtils(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public String sendCode(String email) {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("爱慈善-邮箱验证");
            helper.setText("<h2 >爱慈善公益平台</h2>" +
                    "<h3>邮箱验证<h3/>" +
                    "您收到了来自爱慈善公益平台发送的验证码<br>" +
                    "验证码: <span style='color : red'>" + code + "</span><br>" +
                    "<h5 style='color : red'>如果并非本人操作，请忽略本邮件</h5>", true);
            helper.setFrom("373675032@qq.com");
            helper.setTo(email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("mimeMessage对象为:" + mimeMessage);
        mailSender.send(mimeMessage);
        return String.valueOf(code);
    }

}
