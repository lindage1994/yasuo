package com.iahsnil.nine.common.cache;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: zed
 * @Date: 2019/5/22 19:39
 * @Description: key生成器
 */
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param joinPoint joinPoint
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint joinPoint);
}
