package com.iahsnil.yasuo.manage.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

/**
 * @Author: zed
 * @Date: 2019/8/20 14:12
 * @Description: 自定义错误页面
 */
public class MyErrorPageRegistrar implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
        ErrorPage errorPage = new ErrorPage("/500");

        registry.addErrorPages(page404, page500, errorPage);
    }
}
