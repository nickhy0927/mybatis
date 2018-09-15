package com.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class PageInfo {

	private int curr;// 当前页
	private int size = 10;// 每页大小
	private int total;// 总记录数
	private int totalPage;// 总页数

	public PageInfo(HttpServletRequest request) {
		String parameter = request.getParameter("curr");
		String size = request.getParameter("size");
		if (StringUtils.isNotEmpty(parameter)) {
			try {
				Integer c = Integer.valueOf(parameter);
				if (c < 1)
					c = 1;
				Integer s = Integer.valueOf(size);
				if (s < 1)
					s = this.size;
				this.curr = c;
				this.size = s;
			} catch (Exception e) {
				this.curr = 1;
			}
		}
	}

	public PageInfo(String parameter, String size) {
		if (StringUtils.isNotEmpty(parameter)) {
			try {
				Integer c = Integer.valueOf(parameter);
				if (c < 1)
					c = 1;
				Integer s = Integer.valueOf(size);
				if (s < 1)
					s = this.size;
				this.curr = c;
				this.size = s;
			} catch (Exception e) {
				this.curr = 1;
			}
		}
	}

	public int getCurr() {
		return curr;
	}

	public void setCurr(int curr) {
		this.curr = curr;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
