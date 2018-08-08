package com.mybatis.common.utils;

import java.util.List;

public class PageLay<T> {
	private int code;
	private String msg;
	private long count;
	private List<T> data;

	public PageLay(List<T> data, int code, String msg, long count) {
		this.data = data;
		this.code = code;
		this.msg = msg;
		this.count = count;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
}
