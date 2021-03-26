package com.offcn.util;
/**
 * 分页工具
 * @author IBM
 */
public class PageTools {
	//每页条数（必备条件）
	private int size;
	//总条数（必备条件）
	private int countRows;
	//当前页（必备条件）
	private int current_page;
	//总页数
	private int count_page;
	//索引
	private int index;
	//上一页
	private int pre_page;
	//下一页
	private int next_page;
	
	public PageTools(int size, int countRows, String current_page) {
		this.size = size;
		this.countRows = countRows;
		//初始化当前页信息
		init_current_page(current_page);
		//初始化总页数
		init_count_page();
		//初始化索引
		init_index();
		//上一页
		init_pre_page();
		//下一页
		init_next_page();
	}
	
	//下一页
	private void init_next_page() {
		if(this.current_page == this.count_page) {
			this.next_page = this.count_page;
		}else {
			this.next_page = this.current_page +1;
		}
	}

	//上一页
	private void init_pre_page() {
		if(this.current_page == 1) {
			this.pre_page = 1;
		}else {
			this.pre_page = this.current_page - 1;
		}
	}

	//初始化索引
	private void init_index() {
		//(当前页 - 1) * 每页条数 ;
		this.index = (this.current_page-1) * this.size;
	}

	//初始化总页数
	private void init_count_page() {
		//公式  (总条数/每页多少条) + (总条数%每页多少条)>0?1:0
		this.count_page = (this.countRows/this.size) + (this.countRows%this.size>0?1:0);
	}

	//初始化当前页信息
	private void init_current_page(String current_page) {
		if(null == current_page || "".equals(current_page)) {
			this.current_page = 1;
		}else {
			this.current_page = Integer.parseInt(current_page);
		}
	}

	//因为页面和dao层都需要 工具的属性，所以直接提供getter方法即可
	public int getSize() {
		return size;
	}

	public int getCountRows() {
		return countRows;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public int getCount_page() {
		return count_page;
	}

	public int getIndex() {
		return index;
	}

	public int getPre_page() {
		return pre_page;
	}

	public int getNext_page() {
		return next_page;
	}
	
	
	
}
