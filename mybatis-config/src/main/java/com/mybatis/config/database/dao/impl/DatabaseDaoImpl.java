package com.mybatis.config.database.dao.impl;

import com.mybatis.config.database.dao.DatabaseDao;
import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DatabaseDaoImpl extends CommonDaoImpl<Database, String> implements DatabaseDao {

    @Override
    public List<TableComment> queryTableNameAndCommentByMap(Map<String, Object> params) {
        String statement = "com.mybatis.config.database.entity.Database.queryTableNameAndCommentByMap";
        return getSqlSession().selectList(statement, params);

    }

    @Override
    public List<TableComment> queryTableNameAndCommentByPageMap(Map<String, Object> params) {
        String statement = "com.mybatis.config.database.entity.Database.queryTableNameAndCommentByPageMap";
        List<TableComment> objectList = getSqlSession().selectList(statement, params);
        return objectList;
    }
}
