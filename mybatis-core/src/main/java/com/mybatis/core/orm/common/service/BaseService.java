package com.mybatis.core.orm.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.core.orm.core.exception.DAOException;
import com.mybatis.core.orm.entity.PageRowBounds;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午11:00:43
 */
public interface BaseService<E, ID extends Serializable, T extends BaseMapper<E, ID>> {

    /**
     * 新增
     * @param t
     * @return
     * @throws DAOException
     */
    int insert(E t) throws DAOException;

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
     * @param support
     * @return
     * @throws DAOException
     */
    PagerInfo<E> queryPageByMap(Map<String, Object> paramMap, PageSupport support) throws DAOException;
}
