package com.mybatis.core.orm.core.exception;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 格式化返回客户端数据格式（json）
 */

public class ReturnFormat {

    private static Map<String, String> messageMap = Maps.newHashMap();

    //初始化状态码与文字说明
    static {
        messageMap.put("0", "");

        messageMap.put("400", "Bad Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method Not Allowed");
        messageMap.put("406", "Not Acceptable");
        messageMap.put("500", "Internal Server Error");

        messageMap.put("1000", "[服务器]运行时异常");
        messageMap.put("1001", "[服务器]空值异常");
        messageMap.put("1002", "[服务器]数据类型转换异常");
        messageMap.put("1003", "[服务器]IO异常");
        messageMap.put("1004", "[服务器]未知方法异常");
        messageMap.put("1005", "[服务器]数组越界异常");
        messageMap.put("1006", "[服务器]网络异常");


        messageMap.put("200", "数据保存成功");
        messageMap.put("201", "数据保存失败");

        messageMap.put("301", "数据修改成功");
        messageMap.put("302", "数据修改失败");

        messageMap.put("407", "数据删除成功");
        messageMap.put("402", "数据删除失败");

        messageMap.put("501", "获取数据列表成功");
        messageMap.put("502", "获取数据列表失败");
    }

    public static class StatusCode {
        public static final int CODE_400 = 400;
        public static final int CODE_407 = 401;
        public static final int CODE_405 = 405;
        public static final int CODE_406 = 406;
        public static final int CODE_500 = 500;
        public static final int CODE_1000 = 1000;
        public static final int CODE_1001 = 1001;
        public static final int CODE_1002 = 1002;
        public static final int CODE_1003 = 1003;
        public static final int CODE_1004 = 1004;
        public static final int CODE_41005 = 1005;
        public static final int CODE_1006 = 1006;
        public static final int CODE_1007 = 1007;
    }

    public static String retParam(int status, Object data) {
        OutputJson json = new OutputJson(status, messageMap.get(String.valueOf(status)), data);
        return json.toString();
    }
}
