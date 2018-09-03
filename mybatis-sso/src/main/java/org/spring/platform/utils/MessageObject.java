package org.spring.platform.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MessageObject {

	private int code;
	private String message;
	private Object object;

	private static MessageObject messageObject = new MessageObject();

	private MessageObject() {

	}

	public static MessageObject getInstance() {
		return messageObject;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		if (code == ResultCode.SUCCESS) {
			this.message = ResultMessage.SUCCESS_MESSAGE;
		} else
			this.message = ResultMessage.FAILIAR_MESSAGE;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public static class ResultCode {
		public static int SUCCESS = 200;
		public static int FAILIAR = 403;
	}

	public static class ResultMessage {
		public static String SUCCESS_MESSAGE = "操作成功";
		public static String FAILIAR_MESSAGE = "操作失败";
	}

	public void returnData(HttpServletResponse response, MessageObject messageObject) throws IOException {
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(new Gson().toJson(messageObject));
		if (writer != null) {
			writer.flush();
			writer.close();
		}
	}
}
