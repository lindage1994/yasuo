package com.iahsnil.yasuo.manage.service.impl;

import com.iahsnil.yasuo.manage.entity.SysRole;
import com.iahsnil.yasuo.manage.entity.UserInfo;
import com.iahsnil.yasuo.manage.repository.RoleRepository;
import com.iahsnil.yasuo.manage.repository.UserRepository;
import com.iahsnil.yasuo.manage.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zed
 * @Date: 2019/8/20 19:56
 * @Description: 一般用户注册实现
 */
@Service
public class NormalRegisterServiceImpl implements RegisterService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public boolean register(String username, String password, String email) {
        UserInfo newUser = new UserInfo();
        newUser.setUsername(username);
        newUser.setName("用户");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setSalt("");
        List<SysRole> normalRole = new ArrayList<>();
        normalRole.add(roleRepository.getOne(2));
        newUser.setRoleList(normalRole);
        newUser.setState((byte)1);
        userRepository.save(newUser);
        return true;
    }

}
