package com.mybatis.deploy.picture.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.deploy.picture.dao.PictureMapper;
import com.mybatis.deploy.picture.entity.Picture;

public interface PictureService extends BaseService<Picture, String, PictureMapper> {

}
