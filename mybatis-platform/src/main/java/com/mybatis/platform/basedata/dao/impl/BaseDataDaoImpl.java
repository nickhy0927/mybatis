package com.mybatis.platform.basedata.dao.impl;

import com.mybatis.platform.basedata.dao.BaseDataDao;
import com.mybatis.platform.basedata.entity.BaseData;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDataDaoImpl extends CommonDaoImpl<BaseData, String> implements BaseDataDao {

}