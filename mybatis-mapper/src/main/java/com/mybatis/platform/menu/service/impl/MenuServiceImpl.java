/**
 * 
 */
package com.mybatis.platform.menu.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.menu.dao.MenuMapper;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.service.MenuService;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午11:07:45
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, String, MenuMapper> implements MenuService {

}
