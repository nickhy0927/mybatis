package com.mybatis.common.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestData {

    /**
     * 解析请求参数
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getRequestDataToMap(HttpServletRequest request) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
        String name;
        String value = "";
        // 遍历参数
        while (entries.hasNext()) {
            Entry<String, String[]> entry = entries.next();
            // 获取参数key值
            name = entry.getKey();
            // 获取参数所对应的值
            Object valueObj = entry.getValue();
            if (null == valueObj) value = "";
            else if (!(valueObj instanceof String[])) value = valueObj.toString();
            else {
                String[] values = (String[]) valueObj;
                for (String v : values) {
                    value = v + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            if (name.equals("data") && null != request.getAttribute("data")) {
                value = URLDecoder.decode(request.getAttribute("data").toString(), "UTF-8");
                if (StringUtils.startsWith(URLDecoder.decode(value, "UTF-8"), "\"")) {
                    value = value.substring(1, value.length() - 1);
                }
                Map<String, Object> parseMap = toHashMap(value);
                Set<String> keySet = parseMap.keySet();
                for (String key : keySet) {
                    Object item = (null == parseMap.get(key) || "null".equals(parseMap.get(key))) ? ""
                            : parseMap.get(key);
                    paramsMap.put(key, item);
                }
            } else {
                paramsMap.put(name, value);
            }
        }
        return paramsMap;
    }


    /**
     * 将json格式的字符串解析成Map对象
     * <li>json格式：{"name":"admin","retries":"3fff","testname"
     * :"ddd","testretries":"fffffffff"}
     */

    @SuppressWarnings("unchecked")
    private static Map<String, Object> toHashMap(Object object) {
        Map<String, Object> data = new HashMap<>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.fromObject(object);
        Iterator<JSONObject> it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            String value = String.valueOf(jsonObject.get(key));
            data.put(key, value);
        }
        return data;
    }

    /**
     * 字符串去空
     *
     * @param str
     * @return
     * @Description:空格、换行
     * @author jade
     * @date 2016年7月19日
     */
    public static String replaceBlank(String str) {
        String dest = StringUtils.EMPTY;
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
