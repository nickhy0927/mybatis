package com.mybatis.deploy.news.dao;

import com.mybatis.deploy.news.entity.News;
import org.springframework.stereotype.Repository;
import com.mybatis.core.orm.common.mapper.BaseMapper;

@Repository
public interface NewsMapper extends BaseMapper<News,String> {

}