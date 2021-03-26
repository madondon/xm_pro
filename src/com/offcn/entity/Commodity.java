package com.offcn.entity;
/**
 * ��Ʒ��ʵ����
 * @author IBM
 */
public class Commodity {
	
	 private int cid; //����
	 private int gid; //���
	 private Category category;//��ȡ��������
	 private String name;
	 private String color;
	 private String size;
	 private double price;
	 private String description; //���
	 private String full_description; //��ϸ����
	 private String pic; //��ƷͼƬ
	 private int state;//С���� 0��������Ʒ��1���Ų�Ʒ��2���ǵ�Ʒ��3��������Ʒ
	 private String version; //�汾
	 private String product_date;//��������
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFull_description() {
		return full_description;
	}
	public void setFull_description(String full_description) {
		this.full_description = full_description;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getProduct_date() {
		return product_date;
	}
	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}
	public Commodity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commodity(int cid,int gid, String name, String color, String size, double price, String description,
			String full_description, String pic, int state, String version, String product_date) {
		super();
		this.cid=cid;
		this.gid = gid;
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.description = description;
		this.full_description = full_description;
		this.pic = pic;
		this.state = state;
		this.version = version;
		this.product_date = product_date;
	}
	public Commodity(int gid, String name, String color, String size, double price, String description,
			String full_description, String pic, int state, String version, String product_date) {
		super();
		this.gid = gid;
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.description = description;
		this.full_description = full_description;
		this.pic = pic;
		this.state = state;
		this.version = version;
		this.product_date = product_date;
	}
	public Commodity(int gid, String name, double price, int state) {
		super();
		this.gid = gid;
		this.name = name;
		this.price = price;
		this.state = state;
	}
	
	
	 
}
