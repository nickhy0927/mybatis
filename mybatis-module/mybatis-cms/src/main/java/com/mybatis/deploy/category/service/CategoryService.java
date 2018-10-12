package com.mybatis.deploy.category.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.deploy.category.dao.CategoryMapper;
import com.mybatis.deploy.category.entity.Category;

public interface CategoryService extends BaseService<Category, String, CategoryMapper> {

}
