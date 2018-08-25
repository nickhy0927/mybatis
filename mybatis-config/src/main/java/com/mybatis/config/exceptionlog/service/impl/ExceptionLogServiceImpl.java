package com.mybatis.config.exceptionlog.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.config.exceptionlog.dao.ExceptionLogMapper;
import com.mybatis.config.exceptionlog.entity.ExceptionLog;
import com.mybatis.config.exceptionlog.service.ExceptionLogService;

@Service
public class ExceptionLogServiceImpl extends BaseServiceImpl<ExceptionLog, String, ExceptionLogMapper> implements ExceptionLogService{

}
