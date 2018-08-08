package com.mybatis.config.database.service;

import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.config.database.dao.DatabaseDao;
import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.core.orm.core.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseService extends CommonService<Database, String> {

    @Autowired
    private DatabaseDao databaseDao;
    public PagerInfo<TableComment> queryTableNameAndCommentByPageMap(Map<String,Object> params, PageSupport support) {
        List<TableComment> tableComments = databaseDao.queryTableNameAndCommentByMap(params);
        support.setTotalRecord(tableComments.size());
        params.put("startRow", support.getStartRow());
        params.put("size", support.getpageSize());
        List<TableComment> commentList = databaseDao.queryTableNameAndCommentByPageMap(params);
        return new PagerInfo<>(support, commentList);
    }
}
