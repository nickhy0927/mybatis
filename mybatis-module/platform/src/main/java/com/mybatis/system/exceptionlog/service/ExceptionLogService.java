package com.mybatis.system.exceptionlog.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.system.exceptionlog.dao.ExceptionLogMapper;
import com.mybatis.system.exceptionlog.entity.ExceptionLog;

public interface ExceptionLogService extends BaseService<ExceptionLog, String, ExceptionLogMapper> {

}
