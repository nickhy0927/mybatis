package com.mybatis.core.orm.core.service;

import com.mybatis.common.utils.PageLay;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.Underline2Camel;
import com.mybatis.core.orm.core.dao.CommonDao;
import com.mybatis.core.orm.core.exception.DAOException;
import com.mybatis.core.orm.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CommonService<T, ID extends Serializable> {

	@Autowired
	private CommonDao<T, ID> commonDao;

	/**
	 * 插入数据
	 * 
	 * @param t
	 * @return
	 */
	public T insert(T t) throws ServiceException {
		try {
			return commonDao.insert(t);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	/**
	 * 根据map集合删除数据
	 * 
	 * @param paramsMap
	 * @throws DAOException
	 */
	public void delete(Map<String, Object> paramsMap) throws DAOException {
		try {
			commonDao.delete(paramsMap);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	/**
	 * 根据ID删除数据
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void delete(ID id) throws DAOException {
		try {
			commonDao.delete(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	/**
	 * 根据ID集合批量删除数据
	 * 
	 * @param ids
	 * @throws DAOException
	 */
	public void deleteBatch(List<ID> ids) throws DAOException {
		try {
			commonDao.deleteBatch(ids);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	/**
	 * 修改数据
	 * 
	 * @param t
	 * @throws ServiceException
	 */
	public void update(T t) throws ServiceException {
		try {
			commonDao.update(t);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	/**
	 * 查询所有的数据
	 * 
	 * @return
	 */
	public List<T> queryAll() {
		try {
			return commonDao.queryAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一条数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	public T get(Map<String, Object> paramsMap) {
		try {
			return commonDao.get(paramsMap);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T get(ID id) {
		try {
			return commonDao.get(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询一个不带分页的集合
	 * 
	 * @param paramsMap
	 * @return
	 */
	public List<T> queryListByMap(Map<String, Object> paramsMap) {
		try {
			return commonDao.queryListByMap(paramsMap);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分页
	 * 
	 * @param paramsMap
	 * @param support
	 * @return
	 * @throws ServiceException
	 */
	public PagerInfo<T> queryPageByMap(Map<String, Object> paramsMap, PageSupport support) throws ServiceException {
		support.setTotalRecord(queryListByMap(paramsMap).size());
		paramsMap.put("startRow", support.getStartRow());
		paramsMap.put("size", support.getpageSize());
		paramsMap.put("endRow", support.getEntRow());
        paramsMap.put("order", support.getOrder());
        paramsMap.put("sort", support.getSort().toString().toLowerCase());
		List<T> list = commonDao.queryPageByMap(paramsMap);
		PagerInfo<T> pager = new PagerInfo<>(support, list);
		pager.setContent(list);
		return pager;
	}

	public PageLay<T> queryPageLayByMap(Map<String, Object> paramsMap, PageSupport support) throws ServiceException {
		try {
			paramsMap.put("startRow", support.getStartRow());
			paramsMap.put("size", support.getpageSize());
			paramsMap.put("endRow", support.getEntRow());
			paramsMap.put("order", Underline2Camel.camel2Underline(support.getOrder()).toLowerCase());
			paramsMap.put("sort", support.getSort().toString().toLowerCase());
			List<T> list = commonDao.queryPageByMap(paramsMap);
			PageLay<T> pager = new PageLay<T>(list, 0, "获取列表数据成功", queryListByMap(paramsMap).size());
			return pager;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return new PageLay<T>(null, 203, "获取列表数据失败", 0);
	}

}
