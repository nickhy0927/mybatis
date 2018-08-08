package com.mybatis.platform.rolemenu.dao.impl;

import com.mybatis.platform.rolemenu.dao.RoleMenuDao;
import com.mybatis.platform.rolemenu.entity.RoleMenu;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class RoleMenuDaoImpl extends CommonDaoImpl<RoleMenu, String> implements RoleMenuDao {

}