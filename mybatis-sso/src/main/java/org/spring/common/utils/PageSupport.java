package org.spring.common.utils;

public class PageSupport {

	private int size = 10; // 分页大小
	private int totalRecord;// 总记录数
	private int totalPage;// 总页数
	private int currentPage;// 总页数

	public PageSupport(int currentPage) {
		this.currentPage = currentPage > 0 ? currentPage : 1;
	}

	public PageSupport(int currentPage, int pageSize) {
		this.currentPage = currentPage > 0 ? currentPage : 1;
		this.size = pageSize > 0 ? pageSize : this.size;
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
		if (totalRecord > 0) {
			this.totalPage = (totalRecord % this.size > 0 ? (totalRecord / this.size + 1)
					: (totalRecord / this.size));
		}
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

	public int getStartRow() {
		int startRow = (this.currentPage - 1 > 0 ? (this.currentPage - 1) : 0) * this.size;
		return startRow;
	}
}
