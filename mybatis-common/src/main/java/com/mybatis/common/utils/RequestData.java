package com.mybatis.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class RequestData {

	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = Maps.newConcurrentMap();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			Entry<String, String[]> entry = entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

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
		String value = "";
		// 遍历参数
		while (entries.hasNext()) {
			Entry<String, String[]> entry = entries.next();
			// 获取参数key值
			// 获取参数所对应的值
			Object valueObj = entry.getValue();
			if (null == valueObj)
				value = "";
			else if (!(valueObj instanceof String[]))
				value = valueObj.toString();
			else {
				String[] values = (String[]) valueObj;
				for (String v : values) {
					value = v + ",";
				}
				value = value.substring(0, value.length() - 1);
			}
		}
		return paramsMap;
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
