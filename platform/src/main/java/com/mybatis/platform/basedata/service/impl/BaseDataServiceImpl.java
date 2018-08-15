package com.mybatis.platform.basedata.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.basedata.dao.BaseDataMapper;
import com.mybatis.platform.basedata.entity.BaseData;
import com.mybatis.platform.basedata.service.BaseDataService;

@Service
public class BaseDataServiceImpl extends BaseServiceImpl<BaseData, String, BaseDataMapper> implements BaseDataService{

}
