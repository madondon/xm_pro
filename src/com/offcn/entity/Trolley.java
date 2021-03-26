package com.offcn.entity;

/**
 * 购物车实体类
 * @author IBM
 *
 */
public class Trolley {
	
	private int tid; //购物车主键
	private int uid; //用户外键
	private int cid; //商品外键
	private int number; //商品数量
	private String orders_number; //反向填充的一个订单号码
	
	//附加
	private Commodity commodity;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getOrders_number() {
		return orders_number;
	}
	public void setOrders_number(String orders_number) {
		this.orders_number = orders_number;
	}
	public Trolley() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	
	
	
}
