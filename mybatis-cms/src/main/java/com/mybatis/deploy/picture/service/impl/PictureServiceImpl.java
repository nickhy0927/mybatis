package com.mybatis.deploy.picture.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.deploy.picture.dao.PictureMapper;
import com.mybatis.deploy.picture.entity.Picture;
import com.mybatis.deploy.picture.service.PictureService;

@Service
public class PictureServiceImpl extends BaseServiceImpl<Picture, String, PictureMapper> implements PictureService{

}
