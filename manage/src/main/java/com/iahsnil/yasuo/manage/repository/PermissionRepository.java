package com.iahsnil.yasuo.manage.repository;

import com.iahsnil.yasuo.manage.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zed
 * @Date: 2019/2/14 19:43
 * @Description: 权限接口
 */
public interface PermissionRepository extends JpaRepository<SysPermission,Integer> {

}
