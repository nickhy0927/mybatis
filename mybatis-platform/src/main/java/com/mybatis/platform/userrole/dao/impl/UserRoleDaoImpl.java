package com.mybatis.platform.userrole.dao.impl;

import com.mybatis.platform.userrole.dao.UserRoleDao;
import com.mybatis.platform.userrole.entity.UserRole;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends CommonDaoImpl<UserRole, String> implements UserRoleDao {

}