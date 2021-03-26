package com.offcn.entity;
/**
 * 用户实体类
 * @author IBM
 * @Date 2019-4-22 14:19:23
 */
public class User {
	
	private int  id;
	private String  name;
	private int  sex;
	private String  phone_number;
	//地区
	private String  area;
	//默认值为1 1为普通用户、0为管理员
	private int  manager;
	private String  username;
	private String  password;
	//头像
	private String  photo;
	private String  create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public User() {
		super();
	}
	public User(String name, int sex, String phone_number, String area, String username, String password, String photo,
			String create_time) {
		super();
		this.name = name;
		this.sex = sex;
		this.phone_number = phone_number;
		this.area = area;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.create_time = create_time;
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
	
}
