package com.mybatis.deploy.category.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.deploy.category.dao.CategoryMapper;
import com.mybatis.deploy.category.entity.Category;
import com.mybatis.deploy.category.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, String, CategoryMapper> implements CategoryService{

}
