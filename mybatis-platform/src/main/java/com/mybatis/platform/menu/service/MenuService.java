package com.mybatis.platform.menu.service;

import com.mybatis.platform.menu.dao.MenuDao;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.core.orm.core.service.CommonService;
import com.mybatis.platform.menu.entity.MenuTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuService extends CommonService<Menu, String>{

    @Autowired
    private MenuDao menuDao;

    public List<MenuTemplate> queryMenuByTree(Map<String, Object> paramsMap) {
        return menuDao.queryMenuByTree(paramsMap);
    }
}
