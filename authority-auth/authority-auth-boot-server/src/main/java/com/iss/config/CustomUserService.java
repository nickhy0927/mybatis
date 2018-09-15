package com.iss.config;

import com.iss.module.platform.user.pojo.User;
import com.iss.module.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义UserDetailsService,将用户权限交给springsecurity进行管控
 *
 * @Author: 我爱大金子
 * @Description: 将用户权限交给Springsecurity进行管控
 * @Date: Create in 16:19 2017/7/5
 */

public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.login(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            for (SysPermission permission : permissions) {
//                if (permission != null && permission.getName()!=null) {
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
//                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
//                    grantedAuthorities.add(grantedAuthority);
//                }
//            }
            return new User(user.getLoginName(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}