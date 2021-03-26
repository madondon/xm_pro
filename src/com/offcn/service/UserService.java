package com.offcn.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.offcn.dao.UserDao;
import com.offcn.entity.User;
import com.offcn.util.Sendsms;

public class UserService {

	private UserDao ud = new UserDao();
	
	/**
	 * 校验手机号码是否已经被注册
	 * @param phone_number
	 * @return boolean
	 */
	public boolean validate_phone(String phone_number) {
		User user = ud.validate_phone(phone_number);
		if(null == user) {
			return true;
		}
		return false;
	}

	/**
	 * 用户注册
	 * @param user
	 * @return int
	 */
	public int registerUser(User user) {
		return ud.registerUser(user);
	}

	/**
	 * 发送手机验证码
	 * 0、号码未注册 1、验证码发送失败 2、发送成功
	 * @param phone_number
	 * @param session 
	 * @return int
	 */
	public int sendPhoneCode(String phone_number, HttpSession session) {
		//去数据库验证输入的手机号码是否存在
		User user = ud.validate_phone(phone_number);
		if(null == user) {
			return 0;
		}
		//调用发送验证码的工具类
//		int i = Sendsms.send(phone_number,session);
		//固定手机号及验证码
		session.setAttribute("JZ", "18801193448#888888");
		return 2;
	}

	/**
	 * 验证登陆
	 * @param phone_number
	 * @param code
	 * @param session
	 * @return boolean
	 */
	public boolean checkLogin(String phone_number, String code, HttpSession session) {
		//获取session中存放的验证码
		String session_coid = (String)session.getAttribute("JZ");
		//用户输入的验证码拼接
		String input_code = phone_number+"#"+code;
		if(input_code.equals(session_coid)) {
			//移除session中的验证码
			session.removeAttribute("JZ");
			
			//为了能够实现全局的用户数据获取，根据手机号码查询当前用户对象，并且存放在session中
			User user = ud.validate_phone(phone_number);
			session.setAttribute("user", user);
			
			return true;
		}
		return false;
	}

	/**
	 * 管理员登录
	 * @param user
	 * @param session
	 * @return Boolean
	 */
	public Boolean checkAdminLogin(User user, HttpSession session) {
		
		User u = ud.checkAdminLogin(user);
		if(null != u) {
			//将管理员用户存放在session中，区别普通用户
			session.setAttribute("admin_user", u);
			return true;
		}
		return false;
	}

	/**
	 * 查询所有用户信息
	 * @return List<User>
	 */
	public List<User> findUserList() {
		return ud.findUserList();
	}

}
