package com.iahsnil.yasuo.manage.service;

import com.iahsnil.yasuo.manage.entity.UserInfo;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * @Auther: zed
 * @Date: 2019/2/5 19:20
 * @Description: 用户service
 */
public interface UserService {

    List<UserInfo> getUserList();

    //通过@PostAuthorize注解 method(User object)的返回值在Spring表达式语言中可以通过returnObject 来使用。确保登录用户只能获取他自己的用户对象。
    @PostAuthorize("returnObject.username == authentication.username")
    UserInfo findUserById(int id);

    void save(UserInfo UserInfo);

    void edit(UserInfo UserInfo);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(int id);

    UserInfo findUserByUserName(String userName);
}
