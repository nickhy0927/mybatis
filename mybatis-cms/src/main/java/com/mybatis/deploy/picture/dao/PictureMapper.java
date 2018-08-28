package com.mybatis.deploy.picture.dao;

import com.mybatis.deploy.picture.entity.Picture;
import org.springframework.stereotype.Repository;
import com.mybatis.core.orm.common.mapper.BaseMapper;

@Repository
public interface PictureMapper extends BaseMapper<Picture,String> {

}