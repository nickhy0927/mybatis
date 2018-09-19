package com.authority.auth.service;

import com.authority.security.SecurityUser;
import com.iss.platform.user.entity.UserInfo;
import com.iss.platform.user.service.UserInfoService;
import com.iss.utils.ResponesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponesEntity<UserInfo> responesEntity = userInfoService.findUserInfoByLoginname(username);
        if (responesEntity.getCode() == ResponesEntity.ResultCode.SUCCESS) {
            UserInfo userInfo = responesEntity.getData();
            Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            auths.add(authority);
            SecurityUser user = new SecurityUser(username, userInfo.getPassword(), auths);
            return user;
        } else {
            logger.info(username + "用户不存在");
            throw new UsernameNotFoundException(username + "用户不存在");
        }
    }
}