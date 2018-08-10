package com.mybatis.platform.menu.dao.impl;

import com.mybatis.platform.menu.dao.MenuDao;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import com.mybatis.platform.menu.entity.MenuTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MenuDaoImpl extends CommonDaoImpl<Menu, String> implements MenuDao {

    @Override
    public List<MenuTemplate> queryMenuByTree(Map<String, Object> paramsMap) {
        return this.getSqlSession().selectList("com.mybatis.platform.menu.entity.Menu.queryMenuByTree", paramsMap);
    }
}