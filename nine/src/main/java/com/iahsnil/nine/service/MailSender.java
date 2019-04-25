package com.iahsnil.nine.service;


import com.iahsnil.nine.common.model.Email;

/**
 * @Auther: zed
 * @Date: 2019/4/25 19:02
 * @Description:
 */
public interface MailSender {

    void send(Email mail);

    void sendHtml(Email mail) throws Exception;

    void sendFreemarker(Email mail) throws Exception;

    void sendThymeleaf(Email mail) throws Exception;

}
