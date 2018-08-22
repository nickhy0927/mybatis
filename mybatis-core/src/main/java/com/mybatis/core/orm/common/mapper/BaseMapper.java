package com.mybatis.core.orm.common.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mybatis.common.utils.PagerInfo;
import com.mybatis.core.orm.entity.PageRowBounds;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午10:39:45
 */
public interface BaseMapper<E, ID extends Serializable> {

	int insert(E t);

	int update(E t);

	void updateBatch(E t);

	int delete(ID id);

	void deleteBatch(List<ID> ids);

	E get(ID id);

	E getObject(E e);
	
	List<E> queryListByObject(E e);

	List<E> queryListByMap(Map<String, Object> paramMap);

	List<E> queryPageByMap(Map<String, Object> paramMap, PageRowBounds rowBounds);
	
	PagerInfo<E> queryPage(Map<String, Object> paramMap, PageRowBounds rowBounds);
}
