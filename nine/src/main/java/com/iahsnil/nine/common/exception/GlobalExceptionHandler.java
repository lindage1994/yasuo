package com.iahsnil.nine.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: zed
 * @Date: 2019/5/22 20:47
 * @Description: 异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e){
        log.error(e.getMessage(), e);
        return e.getMessage();
    }
}
