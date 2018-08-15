package com.mybatis.orm.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.orm.common.mapper.BaseMapper;
import com.mybatis.orm.common.service.BaseService;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午11:02:06
 */
@Component
public abstract class BaseServiceImpl<E, ID extends Serializable, T extends BaseMapper<E, ID>>
		implements BaseService<E, ID, T> {
	
	@Autowired
	private T mapper;

	@Override
	public int insert(E t) {
		return mapper.insert(t);
	}

	@Override
	public int update(E t) {
		return mapper.update(t);
	}

	@Override
	public E get(ID id) {
		return mapper.get(id);
	}
	
	@Override
	public List<E> queryListByMap(Map<String, Object> paramMap) {
		return mapper.queryListByMap(paramMap);
	}

	@Override
	public List<E> queryPageByMap(Map<String, Object> paramMap, PageRowBounds rowBounds) {
		return mapper.queryPageByMap(paramMap, rowBounds);
	}

}
