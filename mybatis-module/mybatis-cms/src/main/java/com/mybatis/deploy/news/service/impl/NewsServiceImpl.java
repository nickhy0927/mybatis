package com.mybatis.deploy.news.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.deploy.news.dao.NewsMapper;
import com.mybatis.deploy.news.entity.News;
import com.mybatis.deploy.news.service.NewsService;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News, String, NewsMapper> implements NewsService{

}
