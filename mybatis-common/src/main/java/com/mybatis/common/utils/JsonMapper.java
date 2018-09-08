package com.mybatis.common.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;


public class JsonMapper {

    public static String toJson(Object object) {
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            JsonGenerator jg = new JsonFactory().createGenerator(sw);
            om.writeValue(jg, object);
            jg.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static <T> T jsonToObject(String str, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonToList(String str, Class<T> clazz) {
        JSONArray json = JSONArray.fromObject(str);
        JSONObject object;
        T t;
        List<T> list = Lists.newArrayList();
        if (StringUtils.isNotEmpty(str))
            for (int i = 0; i < json.size(); i++) {
                object = JSONObject.fromObject(json.get(i));
                t = (T) JSONObject.toBean(object, clazz);
                list.add(t);
            }
        return list;
    }
}
