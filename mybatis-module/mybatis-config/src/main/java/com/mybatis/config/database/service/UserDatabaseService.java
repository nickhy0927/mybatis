package com.mybatis.config.database.service;

import com.mybatis.config.database.dao.UserDatabaseMapper;
import com.mybatis.config.database.entity.UserDatabase;
import com.mybatis.core.orm.common.service.BaseService;

public interface UserDatabaseService extends BaseService<UserDatabase,String,UserDatabaseMapper> {

}
