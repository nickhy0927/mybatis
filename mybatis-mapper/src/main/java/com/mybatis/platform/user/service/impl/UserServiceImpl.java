/**
 * 
 */
package com.mybatis.platform.user.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 下午1:38:58
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserMapper> implements UserService {


}
