package com.offcn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.offcn.entity.User;
import com.offcn.util.DBTools;
import com.offcn.util.MiTools;
/**
 * �û��־ò�
 * @author IBM
 */
public class UserDao {
	
	/**
	 * У���ֻ������Ƿ��Ѿ���ע��
	 * @param phone_number
	 * @return User
	*/
	public User validate_phone(String phone_number) {
		User user = null;
		String sql = "select * from user where phone_number = ?";
		try {
			user = DBTools.qr.query(sql, new BeanHandler<>(User.class),phone_number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * �û�ע��
	 * @param user
	 * @return int
	 */
	public int registerUser(User user) {
		int i = 0;
		String sql = "insert into user (name,sex,phone_number,area,username,password,photo,create_time) values (?,?,?,?,?,?,?,?)";
		Object [] o = {user.getName(),user.getSex(),user.getPhone_number(),user.getArea(),user.getUsername(),user.getPassword(),user.getPhoto(),user.getCreate_time()};
		
		try {
			i = DBTools.qr.update(sql,o);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ����Ա��¼
	 * @param user
	 * @return User
	 */
	public User checkAdminLogin(User user) {
		User u = null;
		String sql = "select * from user where manager = 0 and username=? and password = ?";
		try {
			u = DBTools.qr.query(sql, new BeanHandler<>(User.class),
					user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	/**
	 * ��ѯ�����û���Ϣ
	 * @return List<User>
	 */
	public List<User> findUserList() {
		List<User> list = null;
		String sql = "select * from user";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
