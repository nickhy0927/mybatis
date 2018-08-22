package com.mybatis.core.orm.common.service;

import java.io.Serializable;

import com.mybatis.core.orm.common.mapper.BaseMapper;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午11:00:43
 */
public interface BaseService<E, ID extends Serializable, T extends BaseMapper<E, ID>> extends BaseMapper<E, ID> {

}
