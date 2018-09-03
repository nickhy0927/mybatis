package org.spring.common.utils;

import java.util.List;

/**
 * 分页对象
 * @author yuanhuangd
 *
 * @param <T>
 */
public class Pager<T> {

	private int size = 10; // 分页大小
	private int totalRecord;// 总记录数
	private int totalPage;// 总页数
	private int currentPage;// 总页数

	private List<T> content;

	public Pager(PageSupport page) {
		this.size = page.getSize();
		this.currentPage = page.getCurrentPage();
		this.totalRecord = page.getTotalRecord();
		this.totalPage = page.getTotalPage();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Pager [size=" + size + ", totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", currentPage="
				+ currentPage + ", content=" + content + "]";
	}
}
