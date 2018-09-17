package com.authority.auth.password;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mybatis.common.utils.MD5;

public class PasswordEncoderBean implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return MD5.MD5Encode(rawPassword.toString().toUpperCase());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (StringUtils.equals(rawPassword, encodedPassword)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.MD5Encode("123456").toUpperCase());
	}

}
