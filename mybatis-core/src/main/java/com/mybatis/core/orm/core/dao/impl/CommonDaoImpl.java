package com.mybatis.core.orm.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.core.orm.core.exception.DAOException;
import org.apache.ibatis.session.SqlSessionFactory;
import com.mybatis.core.orm.core.dao.CommonDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

@SuppressWarnings("unchecked")
public class CommonDaoImpl<T, ID extends Serializable> extends SqlSessionDaoSupport implements CommonDao<T, ID> {

	protected Class<T> clazz;

	public CommonDaoImpl() {
		Class<?> clazz = getClass();
		while (clazz != Object.class) {
			Type t = clazz.getGenericSuperclass();
			if (t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
				if (args[0] instanceof Class) {
					this.clazz = (Class<T>) args[0];
					break;
				}
			}
		}
	}

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#insert(T)
	 */
	@Override
	public T insert(T t) throws DAOException {
		try {
			String statement = this.clazz.getName() + ".insert";
			Class<? extends Object> objClass = t.getClass();
			Field[] fields = objClass.getSuperclass().getDeclaredFields();
			for (Field field : fields) {
				Column column = field.getAnnotation(Column.class);
				field.setAccessible(true);
				if (column != null) {
					if (column.isKey()) {
						field.set(t, UUID.randomUUID().toString().replaceAll("-", ""));
					} else if (column.createTime()) {
						field.set(t, new Date());
					} else if (column.status() == SysConstant.DataStatus.VALID) {
						field.set(t, SysConstant.DataStatus.VALID);
					} else if (column.status() == SysConstant.DataStatus.INVALID) {
						field.set(t, SysConstant.DataStatus.INVALID);
					}
				}
			}
			getSqlSession().insert(statement, t);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage(), new Throwable());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orm.core.dao.CommonDao#delete(java.util.Map)
	 */
	@Override
	public void delete(Map<String, Object> paramsMap) throws DAOException {
		try {
			String statement = this.clazz.getName() + ".delete";
			getSqlSession().insert(statement, paramsMap);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage(), new Throwable());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orm.core.dao.CommonDao#delete(java.io.Serializable)
	 */
	@Override
	public void delete(ID id) throws DAOException {
		try {
			Map<String, Object> paramsMap = Maps.newConcurrentMap();
			paramsMap.put("id", id);
			delete(paramsMap);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage(), new Throwable());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orm.core.dao.CommonDao#deleteBatch(java.util.List)
	 */
	@Override
	public void deleteBatch(List<ID> ids) throws DAOException {
		try {
			String statement = this.clazz.getName() + ".deleteBatch";
			getSqlSession().delete(statement, ids);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage(), new Throwable());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#update(T)
	 */
	@Override
	public T update(T t) throws DAOException {
		try {
			String statement = this.clazz.getName() + ".update";
			getSqlSession().update(statement, t);
			return t;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage(), new Throwable());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#get(java.util.Map)
	 */
	@Override
	public T get(Map<String, Object> paramsMap) {
		String statement = this.clazz.getName() + ".get";
		return this.getSqlSession().selectOne(statement, paramsMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orm.core.dao.CommonDao#get(java.io.Serializable)
	 */
	@Override
	public T get(ID id) throws DAOException {
		Map<String, Object> paramsMap = Maps.newHashMap();
		paramsMap.put("id", id);
		return this.get(paramsMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#queryList(java.lang.String)
	 */
	@Override
	public List<T> queryAll() throws DAOException {
		String statement = this.clazz.getName() + ".queryAll";
		return this.getSqlSession().selectList(statement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#queryListByMap(java.util.Map)
	 */
	@Override
	public List<T> queryListByMap(Map<String, Object> paramsMap) throws DAOException {
		try {
			String statement = this.clazz.getName() + ".queryListByMap";
			return this.getSqlSession().selectList(statement, paramsMap);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage(), new Throwable());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#queryPageByMap(java.util.Map)
	 */
	@Override
	public List<T> queryPageByMap(Map<String, Object> paramsMap) throws DAOException {
		String statement = this.clazz.getName() + ".queryPageByMap";
		return this.getSqlSession().selectList(statement, paramsMap);
	}

}
