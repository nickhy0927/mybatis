package com.mybatis.platform.menu.service;

import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTemplate;

import java.util.List;
import java.util.Map;

public interface MenuService extends BaseMapper<Menu, String> {

    List<MenuTemplate> queryIndexMenu();

}
