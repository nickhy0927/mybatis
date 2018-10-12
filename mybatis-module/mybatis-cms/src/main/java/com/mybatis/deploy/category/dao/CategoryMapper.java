package com.mybatis.deploy.category.dao;

import com.mybatis.deploy.category.entity.Category;
import org.springframework.stereotype.Repository;
import com.mybatis.core.orm.common.mapper.BaseMapper;

@Repository
public interface CategoryMapper extends BaseMapper<Category,String> {

}