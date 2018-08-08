package com.mybatis.common.utils;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


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
}
