package com.anrongtec.lasa.interfaces.result;

import java.io.Serializable;

/**
 * 分页信息
 */
public class PagingInfo implements Serializable {
	// 当前页数
	private int pageIndex;
	// 一共的页数
	private int pageSize;
	// 数据条数
	private int count;
	// 每页的数据条数
	private int pageCount;
	// 起始数据位置(本页返回数据在全部数据中的起始位置)
	private int start;
	// 结束数据位置
	private int end;


	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
