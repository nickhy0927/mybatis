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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
	}

	@Override
	public E get(ID id) {
		return mapper.get(id);
	}
	
	@Override
	public E getObject(E e) {
		return null;
	}
	
	@Override
	public List<E> queryListByMap(Map<String, Object> paramMap) {
		return mapper.queryListByMap(paramMap);
	}

	@Override
	public PagerInfo<E> queryPage(Map<String, Object> paramMap, PageRowBounds rowBounds) {
		List<E> list = mapper.queryPageByMap(paramMap, rowBounds);
		PageSupport support = rowBounds.getSupport();
		support.setTotalRecord(queryListByMap(paramMap).size());
		return new PagerInfo<>(support, list);
	}

	@Override
	public void updateBatch(E t) {
		this.mapper.updateBatch(t);
	}

	@Override
	public int delete(ID id) {
		return this.mapper.delete(id);
	}

	@Override
	public void deleteBatch(List<ID> ids) {
		this.mapper.deleteBatch(ids);		
	}

	@Override
	public List<E> queryListByObject(E e) {
		return this.mapper.queryListByObject(e);
	}
	
	@Override
	public List<E> queryPageByMap(Map<String, Object> paramMap, PageRowBounds rowBounds) {
		return this.mapper.queryPageByMap(paramMap, rowBounds);
	}
	

}
