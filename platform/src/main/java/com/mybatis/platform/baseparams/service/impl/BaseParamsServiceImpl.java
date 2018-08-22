package com.mybatis.platform.baseparams.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.baseparams.dao.BaseParamsMapper;
import com.mybatis.platform.baseparams.entity.BaseParams;
import com.mybatis.platform.baseparams.service.BaseParamsService;

@Service
public class BaseParamsServiceImpl extends BaseServiceImpl<BaseParams, String, BaseParamsMapper> implements BaseParamsService{

}
