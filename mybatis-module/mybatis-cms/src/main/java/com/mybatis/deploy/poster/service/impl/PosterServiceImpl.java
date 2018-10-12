package com.mybatis.deploy.poster.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.deploy.poster.dao.PosterMapper;
import com.mybatis.deploy.poster.entity.Poster;
import com.mybatis.deploy.poster.service.PosterService;

@Service
public class PosterServiceImpl extends BaseServiceImpl<Poster, String, PosterMapper> implements PosterService{

}
