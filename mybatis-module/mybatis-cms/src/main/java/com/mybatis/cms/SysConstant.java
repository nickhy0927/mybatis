package com.mybatis.cms;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mybatis.common.utils.JsonMapper;

import java.util.List;
import java.util.Map;

public class SysConstant {

    /**
     * 栏目分类
     */
    public final static class CategoryType {
        public final static int NEWS = 1;
        public final static int BLOG = 2;

        public final static String BLOG_NAME = "博客栏目";
        public final static String NEWS_NAME = "新闻栏目";
    }

    public final static String getCategoryName(int code) {
        String name = "";
        switch (code) {
            case CategoryType.NEWS:
                name = CategoryType.NEWS_NAME;
                break;
            case CategoryType.BLOG:
                name = CategoryType.BLOG_NAME;
                break;
        }
        return name;
    }
    public final static String getCategoryList() {
        List<Map<String,Object>> categoryList = Lists.newArrayList();
        Map<String,Object> categoryMap = Maps.newConcurrentMap();
        categoryMap.put("value", CategoryType.NEWS);
        categoryMap.put("text", CategoryType.NEWS_NAME);
        categoryList.add(categoryMap);
        categoryMap = Maps.newConcurrentMap();
        categoryMap.put("value", CategoryType.BLOG);
        categoryMap.put("text", CategoryType.BLOG_NAME);
        categoryList.add(categoryMap);
        return new JsonMapper().toJson(categoryList);
    }
}
