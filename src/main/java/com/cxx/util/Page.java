package com.cxx.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于分页
 * @author Cool
 *
 */
public class Page {
	//当前页
	private int curPage=1;
	//每页数据数
	private int pageSize=10;
	//总页数
	private int totalPage;
	//总数据数
	private int sumDatas;
	//保存的数据
	private List data=new ArrayList();
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(int curPage, int pageSize, int totalPage, int sumDatas, List data) {
		super();
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.sumDatas = sumDatas;
		this.data = data;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getSumDatas() {
		return sumDatas;
	}
	public void setSumDatas(int sumDatas) {
		this.sumDatas = sumDatas;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Page [curPage=" + curPage + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", sumDatas="
				+ sumDatas + ", data=" + data + "]";
	};
	
	
}
