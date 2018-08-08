package com.mybatis.config.database.dao;

import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.core.dao.CommonDao;

import java.util.List;
import java.util.Map;

public interface DatabaseDao extends CommonDao<Database, String> {

    List<TableComment> queryTableNameAndCommentByMap(Map<String,Object> params);

    List<TableComment> queryTableNameAndCommentByPageMap(Map<String,Object> params);
}
