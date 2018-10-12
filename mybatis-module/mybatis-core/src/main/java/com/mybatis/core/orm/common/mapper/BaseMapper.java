package com.mybatis.core.orm.common.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.mybatis.core.orm.core.exception.DAOException;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午10:39:45
 */
@Repository
public interface BaseMapper<E, ID extends Serializable> {

	/**
	 * 新增
	 * @param t
	 * @return
	 * @throws DAOException
	 */
	int insert(E t) throws DAOException;

	/**
	 * 批量新增
	 * @param t
	 * @throws DAOException
	 */
	void insertBatch(List<E> t) throws DAOException;
	/**
	 * 修改
	 * @param t
	 * @return
	 * @throws DAOException
	 */
	int update(E t) throws DAOException;

	/**
	 * 批量修改
	 * @param t
	 * @throws DAOException
	 */
	void updateBatch(E t) throws DAOException;

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	int delete(ID id) throws DAOException;

	/**
	 * 批量删除
	 * @param ids
	 * @throws DAOException
	 */
	void deleteBatch(List<ID> ids) throws DAOException;

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	E get(ID id) throws DAOException;

	/**
	 * 根据对象查询
	 * @param e
	 * @return
	 * @throws DAOException
	 */
	E getObject(E e) throws DAOException;
	
	/**
	 * 根据对象查询集合
	 * @param e
	 * @return
	 * @throws DAOException
	 */
	List<E> queryListByObject(E e) throws DAOException;

	/**
	 * 根据map查询集合
	 * @param paramMap
	 * @return
	 * @throws DAOException
	 */
	List<E> queryListByMap(Map<String, Object> paramMap) throws DAOException;

	/**
	 * 根据map查询分页
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 * @throws DAOException
	 */
	List<E> queryPageByMap(Map<String, Object> paramMap, RowBounds rowBounds) throws DAOException;
}
