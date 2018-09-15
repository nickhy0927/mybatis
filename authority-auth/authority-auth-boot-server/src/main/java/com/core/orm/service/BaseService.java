package com.core.orm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.core.orm.mapper.BaseMapper;
import com.core.utils.PageInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class BaseService<T, ID extends Serializable> {

	@Autowired
	private BaseMapper<T> baseMapper;

	public int insert(T t) {
		int i = baseMapper.insert(t);
		return i;
	}

	public int delete(T t) {
		int i = baseMapper.delete(t);
		return i;
	}

	public int updateByPrimaryKey(T t) {
		int i = baseMapper.updateByPrimaryKey(t);
		return i;
	}

	public int selectCount(T t) {
		int i = baseMapper.selectCount(t);
		return i;
	}

	public T selectOneByExample(T t) {
		t = baseMapper.selectOne(t);
		return t;
	}

	public T selectOneByExample(ID id) {
		T t = baseMapper.selectOneByExample(id);
		return t;
	}

	public int deleteByPrimaryKey(ID id) {
		int i = baseMapper.deleteByPrimaryKey(id);
		return i;
	}

	public int deleteByMap(Map<String, Object> paramMap) {
		int i = baseMapper.deleteByExample(paramMap);
		return i;
	}

	public List<T> selectByMap(Map<String, Object> paramMap) {
		List<T> list = baseMapper.selectByExample(paramMap);
		return list;
	}

	public List<T> selectPage(Map<String, Object> paramMap, PageInfo pageInfo) {
		Page<T> page = PageHelper.startPage(pageInfo.getCurr(), pageInfo.getSize());
		System.out.println(page.getResult());
		List<T> list = baseMapper.selectByExample(paramMap);
		return list;
	}
}
