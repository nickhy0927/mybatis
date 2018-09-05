package com.mybatis.config.database.service.impl;

import com.mybatis.config.database.dao.UserDatabaseMapper;
import com.mybatis.config.database.entity.UserDatabase;
import com.mybatis.config.database.service.UserDatabaseService;
import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserDatabaseServiceImpl extends BaseServiceImpl<UserDatabase,String,UserDatabaseMapper> implements UserDatabaseService {
}
