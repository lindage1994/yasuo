package com.iahsnil.yasuo.manage.config;

import com.iahsnil.yasuo.manage.error.MyErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zed
 * @Date: 2019/8/20 14:11
 * @Description: 错误页面配置类
 */
@Configuration
public class ErrorConfig {
    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new MyErrorPageRegistrar();
    }
}
