package com.iss.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.iss.security.entity.SecurityUser;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 以下可以替换成用其他方式获取用户信息
		if (username.equals("admin")) {
			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
			auths.add(authority);
			SecurityUser user = new SecurityUser(username, "123456", auths);
			return user;
		} else {
			throw new UsernameNotFoundException("用户不存在");
		}
	}

}
