package com.mybatis.config.database.dao;

import java.util.List;
import java.util.Map;

import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.common.mapper.BaseMapper;

public interface DatabaseMapper extends BaseMapper<Database, String> {

    List<TableComment> queryTableNameAndCommentByMap(Map<String,Object> params);

    List<TableComment> queryTableNameAndCommentByPageMap(Map<String,Object> params);
}
