package com.iahsnil.nine.service.impl;

import com.iahsnil.nine.common.model.Email;
import com.iahsnil.nine.service.MailSender;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zed
 * @Date: 2019/4/25 19:08
 * @Description:
 */
@Service
@Slf4j
public class MailSenderImpl implements MailSender {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    Configuration configuration;
    @Autowired
    TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    public String USER_NAME;//发送者
    private final static String PATH = "yasuo@qq.com";//发送者

    @Override
    public void send(Email mail) {
        log.info("发送邮件：{}",mail.getContent());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USER_NAME);
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());

        javaMailSender.send(message);
    }

    @Override
    public void sendHtml(Email mail) throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(USER_NAME,"yasuo");
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        helper.setText(
                "<html><body><img src=\"cid:springcloud\" ></body></html>",
                true);
        // 发送图片
//        File file = ResourceUtils.getFile("classpath:static"
//                + Constants.SF_FILE_SEPARATOR + "image"
//                + Constants.SF_FILE_SEPARATOR + "springcloud.png");
        FileSystemResource file = new FileSystemResource(new File("heihei.jpg"));
        helper.addInline("springcloud", file);
        // 发送附件
        helper.addAttachment("heihei", file);

        javaMailSender.send(message);
    }

    @Override
    public void sendFreemarker(Email mail) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(USER_NAME,"fizz");
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        model.put("path", PATH);
        Template template = configuration.getTemplate(mail.getTemplate());
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                template, model);
        helper.setText(text, true);
        javaMailSender.send(message);
    }

    @Override
    public void sendThymeleaf(Email mail) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(USER_NAME);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Context context = new Context();
        context.setVariable("email", mail);
        String text = templateEngine.process(mail.getTemplate(), context);
        helper.setText(text, true);
        javaMailSender.send(message);
    }

}
