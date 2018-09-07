package com.mybatis.config.database.dao;

import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.common.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DatabaseMapper extends BaseMapper<Database, String> {

    List<TableComment> queryTableNameAndCommentByMap(Map<String, Object> params);

    List<TableComment> queryTableNameAndCommentByPageMap(Map<String, Object> params);

    List<Database> queryConnectDatabase();

}
