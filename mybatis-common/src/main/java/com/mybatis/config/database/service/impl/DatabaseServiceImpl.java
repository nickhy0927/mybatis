package com.mybatis.config.database.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.config.database.dao.DatabaseMapper;
import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.config.database.service.DatabaseService;
import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;

@Service
public class DatabaseServiceImpl extends BaseServiceImpl<Database, String, DatabaseMapper> implements DatabaseService {

	@Autowired
	private DatabaseMapper databaseMapper;

	/* (non-Javadoc)
	 * @see com.mybatis.config.database.service.DatabaseService#queryTableNameAndCommentByPageMap(java.util.Map, com.mybatis.common.utils.PageSupport)
	 */
	@Override
	public PagerInfo<TableComment> queryTableNameAndCommentByPageMap(Map<String, Object> params, PageSupport support) {
		List<TableComment> tableComments = databaseMapper.queryTableNameAndCommentByMap(params);
		support.setTotalRecord(tableComments.size());
		params.put("startRow", support.getStartRow());
		params.put("size", support.getpageSize());
		List<TableComment> commentList = databaseMapper.queryTableNameAndCommentByPageMap(params);
		return new PagerInfo<>(support, commentList);
	}
}
