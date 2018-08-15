package com.mybatis.core.orm.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.core.orm.entity.PageRowBounds;

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
	public E getObject(E e) {
		return null;
	}
	
	@Override
	public List<E> queryListByMap(Map<String, Object> paramMap) {
		return mapper.queryListByMap(paramMap);
	}

	@Override
	public PagerInfo<E> queryPage(Map<String, Object> paramMap, PageRowBounds rowBounds) {
		List<E> list = mapper.queryPageByMap(paramMap, rowBounds);
		PageSupport support = rowBounds.getSupport();
		support.setTotalRecord(queryListByMap(paramMap).size());
		return new PagerInfo<>(support, list);
	}

	@Override
	public void updateBatch(E t) {
		this.mapper.updateBatch(t);
	}

	@Override
	public int delete(ID id) {
		return this.mapper.delete(id);
	}

	@Override
	public void deleteBatch(List<ID> ids) {
		this.mapper.deleteBatch(ids);		
	}

	@Override
	public List<E> queryListByObject(E e) {
		return this.mapper.queryListByObject(e);
	}
	
	@Override
	public List<E> queryPageByMap(Map<String, Object> paramMap, PageRowBounds rowBounds) {
		return this.mapper.queryPageByMap(paramMap, rowBounds);
	}
	

}
