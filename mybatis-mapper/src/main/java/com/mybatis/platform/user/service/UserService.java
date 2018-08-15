/**
 * 
 */
package com.mybatis.platform.user.service;

import com.mybatis.orm.common.service.BaseService;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 下午1:36:26
 */
public interface UserService extends BaseService<User, String, UserMapper>{

}
