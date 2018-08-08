package com.mybatis.platform.menu.dao.impl;

import com.mybatis.platform.menu.dao.MenuDao;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDaoImpl extends CommonDaoImpl<Menu, String> implements MenuDao {

}