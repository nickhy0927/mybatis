/**
 * 
 */
package com.mybatis.orm.common.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mybatis.core.orm.entity.PageRowBounds;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午10:39:45
 */
public interface BaseMapper<E, ID extends Serializable> {

	int insert(E t);

	int update(E t);

	E get(ID id);
	
	List<E> queryListByMap(Map<String, Object> paramMap);
	
	List<E> queryPageByMap(Map<String, Object> paramMap, PageRowBounds rowBounds);
}
