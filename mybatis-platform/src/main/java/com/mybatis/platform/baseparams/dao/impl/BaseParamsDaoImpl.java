package com.mybatis.platform.baseparams.dao.impl;

import com.mybatis.platform.baseparams.dao.BaseParamsDao;
import com.mybatis.platform.baseparams.entity.BaseParams;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class BaseParamsDaoImpl extends CommonDaoImpl<BaseParams, String> implements BaseParamsDao {

}