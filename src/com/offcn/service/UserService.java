package com.offcn.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.offcn.dao.UserDao;
import com.offcn.entity.User;
import com.offcn.util.Sendsms;

public class UserService {

	private UserDao ud = new UserDao();
	
	/**
	 * У���ֻ������Ƿ��Ѿ���ע��
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
	 * �û�ע��
	 * @param user
	 * @return int
	 */
	public int registerUser(User user) {
		return ud.registerUser(user);
	}

	/**
	 * �����ֻ���֤��
	 * 0������δע�� 1����֤�뷢��ʧ�� 2�����ͳɹ�
	 * @param phone_number
	 * @param session 
	 * @return int
	 */
	public int sendPhoneCode(String phone_number, HttpSession session) {
		//ȥ���ݿ���֤������ֻ������Ƿ����
		User user = ud.validate_phone(phone_number);
		if(null == user) {
			return 0;
		}
		//���÷�����֤��Ĺ�����
//		int i = Sendsms.send(phone_number,session);
		//�̶��ֻ��ż���֤��
		session.setAttribute("JZ", "18801193448#888888");
		return 2;
	}

	/**
	 * ��֤��½
	 * @param phone_number
	 * @param code
	 * @param session
	 * @return boolean
	 */
	public boolean checkLogin(String phone_number, String code, HttpSession session) {
		//��ȡsession�д�ŵ���֤��
		String session_coid = (String)session.getAttribute("JZ");
		//�û��������֤��ƴ��
		String input_code = phone_number+"#"+code;
		if(input_code.equals(session_coid)) {
			//�Ƴ�session�е���֤��
			session.removeAttribute("JZ");
			
			//Ϊ���ܹ�ʵ��ȫ�ֵ��û����ݻ�ȡ�������ֻ������ѯ��ǰ�û����󣬲��Ҵ����session��
			User user = ud.validate_phone(phone_number);
			session.setAttribute("user", user);
			
			return true;
		}
		return false;
	}

	/**
	 * ����Ա��¼
	 * @param user
	 * @param session
	 * @return Boolean
	 */
	public Boolean checkAdminLogin(User user, HttpSession session) {
		
		User u = ud.checkAdminLogin(user);
		if(null != u) {
			//������Ա�û������session�У�������ͨ�û�
			session.setAttribute("admin_user", u);
			return true;
		}
		return false;
	}

	/**
	 * ��ѯ�����û���Ϣ
	 * @return List<User>
	 */
	public List<User> findUserList() {
		return ud.findUserList();
	}

}
