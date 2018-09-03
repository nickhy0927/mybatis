package org.spring.platform.login.service.impl;

import java.util.Date;

import org.spring.common.enums.TrueFalseEnum;
import org.spring.common.utils.Result;
import org.spring.common.utils.ResultCode;
import org.spring.platform.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.common.utils.MD5;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;

	private Result userLogin(String ip, String account, String password) {
		Result result = Result.createSuccessResult();
		User user = userMapper.login(account);
		password = MD5.MD5Encode(password).toUpperCase();
		if (user == null) {
			result.setCode(ResultCode.ERROR).setMessage("登录名不存在");
		} else if (!user.getPassword().equals(password)) {
			result.setCode(ResultCode.ERROR).setMessage("密码不正确");
		} else if (TrueFalseEnum.FALSE.getValue().equals(user.getLocked())) {
			result.setCode(ResultCode.ERROR).setMessage("已被用户禁用");
		} else {
			user.setLastLoginIp(ip);
			user.setLoginCount(user.getLoginCount() + 1);
			user.setLastLoginTime(new Date());
			userMapper.update(user);
			result.setData(user);
		}
		return result;
	}

	@Override
	public Result login(String ip, String loginName, String password) {
		return userLogin(ip, loginName, password);
	}
}
