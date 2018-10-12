package com.mybatis.core.orm.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mybatis.core.orm.core.exception.DAOException;

public interface CommonDao<T, ID extends Serializable> {

	/**
	 * 插入新数据
	 * 
	 * @param t
	 * @return
	 * @throws DAOException
	 */
	T insert(T t) throws DAOException;
	
	void delete(Map<String, Object> paramsMap) throws DAOException;
	
	void delete(ID id) throws DAOException;
	
	void deleteBatch(List<ID> ids) throws DAOException;

	/**
	 * 修改数据
	 * 
	 * @param t
	 * @return
	 * @throws DAOException
	 */
	T update(T t) throws DAOException;

	/**
	 * 查询一条数据
	 * 
	 * @param paramsMap
	 * @param paramsMap
	 * @return
	 * @throws DAOException
	 */
	T get(Map<String, Object> paramsMap) throws DAOException;

	/**
	 * 查询一条数据
	 * 
	 * @param id
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	T get(ID id) throws DAOException;

	List<T> queryListByMap(Map<String, Object> paramsMap) throws DAOException;

	List<T> queryPageByMap(Map<String, Object> paramsMap) throws DAOException;

	List<T> queryAll() throws DAOException;

}