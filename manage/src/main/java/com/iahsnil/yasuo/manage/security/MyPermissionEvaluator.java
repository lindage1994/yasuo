package com.iahsnil.yasuo.manage.security;

import com.iahsnil.yasuo.manage.entity.SysPermission;
import com.iahsnil.yasuo.manage.entity.SysRole;
import com.iahsnil.yasuo.manage.entity.UserInfo;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: zed
 * @Date: 2019/8/21 20:50
 * @Description: 自定义权限认证
 */
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        //获取userInfo
        UserInfo user = (UserInfo) authentication.getPrincipal();
        //获取用户角色
        List<SysRole> roles = user.getRoleList();
        //遍历角色，匹配权限
        for (SysRole role:roles) {
            List<SysPermission> sysPermissions = role.getPermissions();

            for (SysPermission permission:sysPermissions) {
                if (targetUrl.equals(permission.getUrl()) && permission.getPermission().equals(targetPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
