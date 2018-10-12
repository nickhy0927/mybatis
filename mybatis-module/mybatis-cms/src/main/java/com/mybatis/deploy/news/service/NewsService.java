package com.mybatis.deploy.news.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.deploy.news.dao.NewsMapper;
import com.mybatis.deploy.news.entity.News;

public interface NewsService extends BaseService<News, String, NewsMapper> {

}
