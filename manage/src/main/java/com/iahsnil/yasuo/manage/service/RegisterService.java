package com.iahsnil.yasuo.manage.service;

/**
 * @Author: zed
 * @Date: 2019/8/20 19:54
 * @Description: 用户注册
 */
public interface RegisterService {
    boolean register(String username, String password, String email);
}
