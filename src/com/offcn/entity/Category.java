package com.offcn.entity;
/**
 * 商品分类
 * @author IBM
 *
 */
public class Category {
	
	private int gid;
	private String name;
	//分类状态 1正常，0，不可用
	private int state;
	//排序，0最大
	private int order_number;
	//描述
	private String description;
	private String create_time;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String name, int state, int order_number, String description, String create_time) {
		super();
		this.name = name;
		this.state = state;
		this.order_number = order_number;
		this.description = description;
		this.create_time = create_time;
	}
	public Category(int gid, String name, int state, int order_number, String description, String create_time) {
		super();
		this.gid = gid;
		this.name = name;
		this.state = state;
		this.order_number = order_number;
		this.description = description;
		this.create_time = create_time;
	}
	
	
	
}
