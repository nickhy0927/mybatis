package com.mybatis.platform.menu.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.menu.dao.MenuMapper;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTemplate;

import java.util.List;

public interface MenuService extends BaseService<Menu, String, MenuMapper> {

    List<MenuTemplate> queryIndexMenu();

}
