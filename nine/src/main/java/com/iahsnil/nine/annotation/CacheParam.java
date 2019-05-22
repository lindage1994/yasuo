package com.iahsnil.nine.annotation;

import java.lang.annotation.*;

/**
 * @Author: zed
 * @Date: 2019/5/22 19:35
 * @Description: 锁的参数
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     * @return String
     */
    String name() default "";
}
