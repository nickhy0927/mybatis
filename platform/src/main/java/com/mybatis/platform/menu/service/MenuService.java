package com.mybatis.platform.menu.service;

import java.util.List;

import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTemplate;

public interface MenuService extends BaseMapper<Menu, String> {

    List<MenuTemplate> queryIndexMenu();

}
