package com.offcn.entity;

/**
 * ���ﳵʵ����
 * @author IBM
 *
 */
public class Trolley {
	
	private int tid; //���ﳵ����
	private int uid; //�û����
	private int cid; //��Ʒ���
	private int number; //��Ʒ����
	private String orders_number; //��������һ����������
	
	//����
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
