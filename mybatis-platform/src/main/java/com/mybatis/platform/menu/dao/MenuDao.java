package com.mybatis.platform.menu.dao;

import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.core.orm.core.dao.CommonDao;
import com.mybatis.platform.menu.entity.MenuTemplate;

import java.util.List;
import java.util.Map;

public interface MenuDao extends CommonDao<Menu,String> {

    List<MenuTemplate> queryMenuByTree(Map<String, Object> paramsMap);
}