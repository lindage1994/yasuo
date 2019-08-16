package com.iahsnil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zed
 * @Date: 2019/8/16 17:42
 * @Description: config client 启动类
 */
@SpringBootApplication
@RestController
public class ConfigClientApplication {
    @Value("${order.name}")
    String orderName;

    @RequestMapping("/test")
    public String test() {
        return "client ====>>> " + orderName;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
