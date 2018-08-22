package com.mybatis.platform.icon.service.impl;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.icon.dao.IconMapper;
import com.mybatis.platform.icon.entity.Icon;
import com.mybatis.platform.icon.service.IconService;
import org.springframework.stereotype.Service;

/**
 * @program: mybatis-base
 * @description: 图标管理业务层接口实现
 * @author: Mr.Huang
 * @create: 2018-08-22 12:39
 **/
@Service
public class IconServiceImpl extends BaseServiceImpl<Icon, String, IconMapper> implements IconService {

}
