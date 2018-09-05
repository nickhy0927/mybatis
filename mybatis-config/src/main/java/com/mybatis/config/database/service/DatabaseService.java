package com.mybatis.config.database.service;

import java.util.List;
import java.util.Map;

import com.mybatis.code.meta.TableColumn;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.config.database.dao.DatabaseMapper;
import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.common.service.BaseService;

public interface DatabaseService  extends BaseService<Database, String, DatabaseMapper>{

	PagerInfo<TableComment> queryTableNameAndCommentByPageMap(Map<String, Object> params, PageSupport support);

	List<TableColumn> getTableColumnList(Database database, String tableName);

	Database queryConnectDatabase();
}