package org.spring.platform.login.service.impl;

import javax.annotation.Resource;

import org.spring.common.service.LoginUser;
import org.spring.common.service.TokenManager;
import org.springframework.stereotype.Service;
import org.sso.rpc.AuthenticationRpcService;
import org.sso.rpc.RpcUser;

import com.mybatis.platform.user.service.UserService;

@Service("authenticationRpcService")
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

	@Resource
	private UserService userService;
	@Resource
	private TokenManager tokenManager;

	@Override
	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}

	@Override
	public RpcUser findAuthInfo(String token) {
		LoginUser user = tokenManager.validate(token);
		if (user != null) {
			return new RpcUser(user.getAccount());
		}
		return null;
	}
}
