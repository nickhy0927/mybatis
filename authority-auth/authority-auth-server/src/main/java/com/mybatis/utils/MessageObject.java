package com.mybatis.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageObject {

	private int code;
	private String msg;
	private Object object;

	private MessageObject() {
	}

	public static MessageObject getDefaultMessageObjectInstance() {
		return new MessageObject();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public static class ResultCode {
		public static int SUCCESS = 200;
		public static int UNAUTH = 401;
		public static int FAILIAR = 403;

	}

	public void error(String msg) {
		this.msg = msg;
		this.code = ResultCode.FAILIAR;
	}

	public void error(int code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public void ok(String msg) {
		this.msg = msg;
		this.code = ResultCode.SUCCESS;
	}

	public void returnData(HttpServletResponse response, MessageObject messageObject) throws IOException {
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String json = new Gson().toJson(messageObject);
		if (writer != null) {
			writer.write(json);
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
}
