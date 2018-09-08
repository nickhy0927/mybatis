package com.mybatis.config.database.dao;

import com.mybatis.common.utils.PagerInfo;
import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.platform.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DatabaseMapper extends BaseMapper<Database, String> {

    List<TableComment> queryTableNameAndCommentByMap(Map<String, Object> params);

    List<TableComment> queryTableNameAndCommentByPageMap(Map<String, Object> params);

    List<Database> queryConnectDatabase();

    /**
     * 查询用户列表
     * @param paramMap
     * @param rowBounds
     * @return
     */
    PagerInfo<User> queryConnectDatabaseUserList(Map<String, Object> paramMap, PageRowBounds rowBounds);
}
