/**
 * 
 */
package com.mybatis.orm.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.orm.common.mapper.BaseMapper;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午11:00:43
 */
public interface BaseService<E, ID extends Serializable, T extends BaseMapper<E, ID>> {

	int insert(E t);

	int update(E t);

	E get(ID id);
	
	List<E> queryListByMap(Map<String, Object> paramMap);
	
	List<E> queryPageByMap(Map<String, Object> paramMap, PageRowBounds rowBounds);
}
