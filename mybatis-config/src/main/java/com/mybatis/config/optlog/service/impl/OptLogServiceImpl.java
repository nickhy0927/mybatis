package com.mybatis.config.optlog.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.config.optlog.dao.OptLogMapper;
import com.mybatis.config.optlog.entity.OptLog;
import com.mybatis.config.optlog.service.OptLogService;

@Service
public class OptLogServiceImpl extends BaseServiceImpl<OptLog, String, OptLogMapper> implements OptLogService{

}
