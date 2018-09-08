package com.mybatis.config.exceptionlog.service;

import com.mybatis.config.exceptionlog.dao.ExceptionLogMapper;
import com.mybatis.config.exceptionlog.entity.ExceptionLog;
import com.mybatis.core.orm.common.service.BaseService;

public interface ExceptionLogService extends BaseService<ExceptionLog, String, ExceptionLogMapper> {

}
