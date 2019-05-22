package com.iahsnil.nine.controller;

import com.iahsnil.nine.annotation.CacheLock;
import com.iahsnil.nine.common.model.Email;
import com.iahsnil.nine.service.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zed
 * @Date: 2019/4/25 19:53
 * @Description:
 */
@RestController
@Slf4j
public class MailController {

    @Autowired
    MailSender mailSender;

    @RequestMapping("/sendMail")
    public String sendMail() {
        Email email = new Email();
        email.setEmail(new String[]{"lindage1994@126.com"});
        email.setContent("hello sb");
        email.setSubject("测试邮件");
        mailSender.send(email);
        return "success";
    }

    @CacheLock(prefix = "test")
    @RequestMapping("/testLock")
    public String testLock() {
        return "success";
    }
}
