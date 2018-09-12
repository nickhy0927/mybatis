package com.mybatis.system.optlog.service.impl;

import com.mybatis.system.optlog.dao.OptLogMapper;
import com.mybatis.system.optlog.entity.OptLog;
import com.mybatis.system.optlog.service.OptLogService;
import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;

@Service
public class OptLogServiceImpl extends BaseServiceImpl<OptLog, String, OptLogMapper> implements OptLogService {

}
