package com.mybatis.deploy.poster.dao;

import com.mybatis.deploy.poster.entity.Poster;
import org.springframework.stereotype.Repository;
import com.mybatis.core.orm.common.mapper.BaseMapper;

@Repository
public interface PosterMapper extends BaseMapper<Poster,String> {

}