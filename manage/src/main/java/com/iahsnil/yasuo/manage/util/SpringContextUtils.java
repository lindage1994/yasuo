package com.iahsnil.yasuo.manage.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: zed
 * @Date: 2019/4/16 11:22
 * @Description: 从ApplicationContext中获取bean的工具类
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     * @param id bean id
     * @return bean 对象
     */
    public static Object getBeanById(String id) {
        return applicationContext.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     * @param clazz bean class
     * @return T bean
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param clazz bean class
     * @return class Map
     */
    public static <T> Map<String, T> getBeansByClass(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

}
