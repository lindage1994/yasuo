package com.iahsnil.yasuo.manage.Service.impl;


import com.iahsnil.yasuo.manage.Service.UserService;
import com.iahsnil.yasuo.manage.entity.UserInfo;
import com.iahsnil.yasuo.manage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zed
 * @Date: 2019/2/5 19:22
 * @Description: 用户service实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserInfo> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public UserInfo findUserById(int id) {
        return userRepository.findByUid(id);
    }

    @Override
    public void save(UserInfo user) {
        userRepository.save(user);
    }

    @Override
    public void edit(UserInfo user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserInfo findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

}
