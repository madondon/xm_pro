package com.offcn.entity;

import java.util.List;

/**
 * 订单的实体类
 * @author IBM
 */
public class Orders {

	private String  orders_number; //主键-订单编号（UUID）
	private int uid; //用户外键
	private double sum_price; //订单总金额
	//订单状态 0未付款 、1代发货、2待收货、3、已收货
	private int state; 
	//订单创建完成
	private String create_time;
	
	//集合
	private List<Trolley> trolleys;
	
	
	public List<Trolley> getTrolleys() {
		return trolleys;
	}
	public void setTrolleys(List<Trolley> trolleys) {
		this.trolleys = trolleys;
	}
	public String getOrders_number() {
		return orders_number;
	}
	public void setOrders_number(String orders_number) {
		this.orders_number = orders_number;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getSum_price() {
		return sum_price;
	}
	public void setSum_price(double sum_price) {
		this.sum_price = sum_price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(String orders_number, int uid, double sum_price, String create_time) {
		super();
		this.orders_number = orders_number;
		this.uid = uid;
		this.sum_price = sum_price;
		this.create_time = create_time;
	}
	
	
	
	
}
