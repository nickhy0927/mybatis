package com.mybatis.system.exceptionlog.service.impl;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.system.exceptionlog.dao.ExceptionLogMapper;
import com.mybatis.system.exceptionlog.entity.ExceptionLog;
import com.mybatis.system.exceptionlog.service.ExceptionLogService;
import org.springframework.stereotype.Service;

@Service
public class ExceptionLogServiceImpl extends BaseServiceImpl<ExceptionLog, String, ExceptionLogMapper> implements ExceptionLogService {

}
