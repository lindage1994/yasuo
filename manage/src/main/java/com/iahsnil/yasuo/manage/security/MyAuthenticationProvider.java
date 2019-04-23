package com.iahsnil.yasuo.manage.security;

import com.iahsnil.yasuo.manage.Service.UserService;
import com.iahsnil.yasuo.manage.entity.SysRole;
import com.iahsnil.yasuo.manage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Auther: zed
 * @Date: 2019/4/23 16:41
 * @Description: 授权接口
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();// 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials();// 这个是表单中输入的密码
//         这里构建来判断用户是否存在和密码是否正确
        UserInfo user = userService.findUserByUserName(userName); // 这里调用我们的自己写的获取用户的方法；
        if (user == null) {
            throw new BadCredentialsException("用户名不存在");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        if (user.getState() == 2) {                //用户状态
            throw new BadCredentialsException("用户状态异常");
        }

        StringBuilder roleString = new StringBuilder();   //用户角色

        for (SysRole role:user.getRoleList()) {
            roleString.append(role.getRole()).append(",");
        }

        if (roleString.length() > 0) {
            roleString.deleteCharAt(roleString.length() - 1 );
        }

        //用户角色
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleString.toString());
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
