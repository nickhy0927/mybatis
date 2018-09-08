/**
 * 
 */
package com.mybatis.core.orm.entity;

import com.mybatis.common.utils.PageSupport;
import org.apache.ibatis.session.RowBounds;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午10:19:20
 */
public class PageRowBounds extends RowBounds {

	/* 默认offset是0 **/
	public static final int NO_ROW_OFFSET = 0;

	/* 默认Limit是int的最大值，因此它使用的是逻辑分页 **/
	public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;

	private int offset;
	private int limit;
	
	private PageSupport support;
	
	public PageRowBounds() {
		this.offset = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
	}

	public PageRowBounds(PageSupport support) {
		this.offset = support.getStartRow();
		this.limit = support.getpageSize();
		setSupport(support);
	}

	public PageSupport getSupport() {
		return support;
	}
	
	public void setSupport(PageSupport support) {
		this.support = support;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.session.RowBounds#getOffset()
	 */
	@Override
	public int getOffset() {
		return offset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.session.RowBounds#getLimit()
	 */
	@Override
	public int getLimit() {
		return limit;
	}

}
