/**
 * 
 */
package com.mybatis.platform.menu.service;

import org.springframework.stereotype.Repository;

import com.mybatis.orm.common.mapper.BaseMapper;
import com.mybatis.platform.menu.entity.Menu;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午11:07:11
 */
@Repository
public interface MenuService extends BaseMapper<Menu, String> {

}
