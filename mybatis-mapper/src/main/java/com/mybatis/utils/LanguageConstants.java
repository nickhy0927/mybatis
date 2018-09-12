package com.mybatis.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mybatis.common.utils.JsonMapper;
import com.mybatis.interceptor.MessageResources;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @program: mybatis-base
 * @description: 常量工具类
 * @author: Mr.Huang
 * @create: 2018-08-29 12:32
 **/
public class LanguageConstants {

    public static final String ZH = "zh_CN";
    public static final String EN = "en_US";
    public static final String JP = "ja_JP";

    public static String getSelectList() {
        List<Map<String, Object>> selectList = Lists.newArrayList();
        Map<String, Object> map = Maps.newConcurrentMap();
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        System.out.println(language);
        map.put("value", ZH);
        map.put("text", MessageResources.getMessage("zh"));
        selectList.add(map);
        map = Maps.newConcurrentMap();
        map.put("value", EN);
        map.put("text", MessageResources.getMessage("en"));
        selectList.add(map);
        map = Maps.newConcurrentMap();
        map.put("value", JP);
        map.put("text", MessageResources.getMessage("jp"));
        selectList.add(map);
        return new JsonMapper().toJson(selectList);
    }
}
