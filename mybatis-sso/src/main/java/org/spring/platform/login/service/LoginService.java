package org.spring.platform.login.service;

import org.spring.common.utils.Result;

public interface LoginService {
	/**
	 * 用户登录
	 * @param ip
	 * @param loginName
	 * @param password
	 * @return
	 */
	Result login(String ip, String loginName, String password);
}
