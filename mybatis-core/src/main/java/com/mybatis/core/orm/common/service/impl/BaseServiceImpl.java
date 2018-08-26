package com.mybatis.core.orm.common.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.core.orm.core.exception.ServiceException;
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
	public int insert(E t) throws ServiceException {
        try {
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
            return mapper.insert(t);
        } catch (ServiceException e) {
        	e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
	}

	@Override
	public int update(E t) throws ServiceException {
	    try {
            Class<? extends Object> objClass = t.getClass();
            Field[] fields = objClass.getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                field.setAccessible(true);
                if (column != null) {
                    if (column.updateTime()) {
                        field.set(t, new Date());
                    }
                }
            }
            return mapper.update(t);
        } catch (ServiceException e) {
        	e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
	}

	@Override
	public E get(ID id) {
		try {
			return mapper.get(id);
		} catch (Exception e) {
			e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
		}
	}
	
	@Override
	public E getObject(E e) {
		try {
			return this.mapper.getObject(e);
		} catch (Exception e1) {
			e1.printStackTrace();
        	throw new ServiceException(e1.getMessage(), new Throwable());
		}
	}
	
	@Override
	public List<E> queryListByMap(Map<String, Object> paramMap) {
		try {
			return mapper.queryListByMap(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	@Override
	public PagerInfo<E> queryPage(Map<String, Object> paramMap, PageRowBounds rowBounds) {
        try {
			PageSupport support = rowBounds.getSupport();
			paramMap.put("order", support.getOrder());
			paramMap.put("sort", support.getSort());
			List<E> list = mapper.queryPageByMap(paramMap, rowBounds);
			support.setTotalRecord(queryListByMap(paramMap).size());
			return new PagerInfo<>(support, list);
		} catch (ServiceException e) {
			e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	@Override
	public void updateBatch(E t) {
		try {
			this.mapper.updateBatch(t);
		} catch (Exception e) {
			e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
		}
	}

	@Override
	public int delete(ID id) {
		return this.mapper.delete(id);
	}

	@Override
	public void deleteBatch(List<ID> ids) {
		try {
			this.mapper.deleteBatch(ids);
		} catch (Exception e) {
			e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
		}		
	}

	@Override
	public List<E> queryListByObject(E e) {
		try {
			return this.mapper.queryListByObject(e);
		} catch (ServiceException ex) {
			ex.printStackTrace();
        	throw new ServiceException(ex.getMessage(), new Throwable());
		}
	}
	
	@Override
	public List<E> queryPageByMap(Map<String, Object> paramMap, PageRowBounds rowBounds) {
		try {
			return this.mapper.queryPageByMap(paramMap, rowBounds);
		} catch (ServiceException e) {
			e.printStackTrace();
        	throw new ServiceException(e.getMessage(), new Throwable());
		}
	}
	

}
