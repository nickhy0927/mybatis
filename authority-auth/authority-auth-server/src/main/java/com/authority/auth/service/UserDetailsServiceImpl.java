package com.authority.auth.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 以下可以替换成用其他方式获取用户信息
		if (username.equals("admin")) {
			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
			auths.add(authority);
			User user = new User(username, "123456", auths);
			return user;
		} else {
			throw new UsernameNotFoundException("用户不存在");
		}
	}
}