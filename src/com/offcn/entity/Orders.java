package com.offcn.entity;

import java.util.List;

/**
 * ������ʵ����
 * @author IBM
 */
public class Orders {

	private String  orders_number; //����-������ţ�UUID��
	private int uid; //�û����
	private double sum_price; //�����ܽ��
	//����״̬ 0δ���� ��1��������2���ջ���3�����ջ�
	private int state; 
	//�����������
	private String create_time;
	
	//����
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
