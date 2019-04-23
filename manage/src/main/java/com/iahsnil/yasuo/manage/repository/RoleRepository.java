package com.iahsnil.yasuo.manage.repository;

import com.iahsnil.yasuo.manage.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zed
 * @Date: 2019/2/14 17:34
 * @Description: 角色操作接口
 */
public interface RoleRepository extends JpaRepository<SysRole,Integer> {
}
