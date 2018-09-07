package com.mybatis.config.database.dao;

import java.util.List;
import java.util.Map;

import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.platform.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseMapper extends BaseMapper<Database, String> {

    List<TableComment> queryTableNameAndCommentByMap(Map<String,Object> params);

    List<TableComment> queryTableNameAndCommentByPageMap(Map<String,Object> params);

    List<Database> queryConnectDatabase();

    /**
     * 查询所有的用户
     * @param params
     * @return
     */
    List<User> queryConnectDatabaseUserPage(Map<String, Object> params, PageRowBounds rowBounds);
}
