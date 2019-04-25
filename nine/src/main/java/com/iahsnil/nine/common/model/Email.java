package com.iahsnil.nine.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Auther: zed
 * @Date: 2019/4/25 19:06
 * @Description:
 */
@Data
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    //必填参数
    private String[] email;//接收方邮件
    private String subject;//主题
    private String content;//邮件内容
    //选填
    private String template;//模板
    private HashMap<String, String> kvMap;// 自定义参数
}
