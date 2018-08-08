package com.mybatis.platform.role.dao.impl;

import com.mybatis.platform.role.dao.RoleDao;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends CommonDaoImpl<Role, String> implements RoleDao {

}