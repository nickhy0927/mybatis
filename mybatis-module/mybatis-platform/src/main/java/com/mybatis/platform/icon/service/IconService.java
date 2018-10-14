package com.mybatis.platform.icon.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.icon.dao.IconMapper;
import com.mybatis.platform.icon.entity.Icon;

/**
 * @program: mybatis-base
 * @description: 图标管理业务层接口
 * @author: Mr.Huang
 * @create: 2018-08-22 12:38
 **/
public interface IconService extends BaseService<Icon, String, IconMapper> {
}
