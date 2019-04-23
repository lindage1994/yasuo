package com.iahsnil.yasuo.manage.repository;

import com.iahsnil.yasuo.manage.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zed
 * @Date: 2019/2/5 19:16
 * @Description: 用户操作接口
 */
public interface UserRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByUid(int id);

    UserInfo findByUsername(String userName);

}
