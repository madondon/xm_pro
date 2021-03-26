package com.offcn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.offcn.entity.Trolley;
import com.offcn.util.DBTools;

/**
 * ���ﳵ�־ò�
 * @author IBM
 */
public class TrolleyDao {

	/**
	 * ����CID��ѯ��ǰ�������Ƿ����δ֧���ı���Ʒ��
	 * @param id
	 * @param cid
	 * @return Trolley
	 */
	public Trolley findTrolleyByCidAndUid(int id, String cid) {
		Trolley trolley = null;
		String sql = "select * from trolley where uid = ? and cid =? and orders_number is null";
		try {
			trolley = DBTools.qr.query(sql, new BeanHandler<>(Trolley.class),id,cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trolley;
	}

	/**
	 * ִ�����ӹ��ﳵ
	 * @param id
	 * @param cid
	 * @return int
	 */
	public int saveTrolley(int id, String cid) {
		int i = 0;
		String sql = "insert into trolley (uid,cid) values (?,?)";
		try {
			i = DBTools.qr.update(sql, id,cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ִ���޸Ĺ��ﳵ��Ʒ����
	 * @param id
	 * @param cid
	 * @param number
	 * @return int
	 */
	public int updateTrolley(int id, String cid, int number) {
		String sql = "update trolley set number = ? where uid= ? and cid = ? and orders_number is null";
		int i = 0;
		try {
			i = DBTools.qr.update(sql, number,id,cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ��ѯ��ǰ�û��Ĺ��ﳵ��Ʒ������
	 * @param uid
	 * @return int
	 */
	public int showNumber(int id) {
		long number =  0;
		String sql = "select count(*) from trolley where uid = ? and orders_number is null";
		try {
			number = (long)DBTools.qr.query(sql, new ScalarHandler(), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)number;
	}

	/**
	 * ��ѯ��ǰ���ﳵ������Ʒ
	 * @param session
	 * @return List<Trolley>
	 */
	public List<Trolley> findTrolleyListByUser(int id) {
		List<Trolley> list = null;
		String sql = "select * from trolley where uid = ? and orders_number is null";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Trolley.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * �޸Ĺ��ﳵ��Ʒ����
	 * @param tid
	 * @param number
	 * @param session 
	 * @return int
	 */
	public int addOrDeleteNumber(String tid, String number, String id) {
		String sql = "update trolley set number = ? where tid = ?";
		int i = 0;
		try {
			i = DBTools.qr.update(sql, number,tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ��Ӷ�����
	 * @param orders_number
	 * @param session 
	 * @return int
	 */
	public int addOrders_number(String orders_number, int id) {
		int i = 0;
		String sql = "update trolley set orders_number = ? where uid = ? and orders_number is null";
		try {
			i = DBTools.qr.update(sql, orders_number,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ���ݶ����Ų�ѯ��ǰ���ﳵ��Ϣ
	 * @param orders_number
	 * @return List<Trolley>
	 */
	public List<Trolley> findTrolleyListByOrdersNumber(String orders_number) {
		List<Trolley> list = null;
		String sql = "select * from trolley where orders_number = ?";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Trolley.class),orders_number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ɾ����Ʒ
	 * @param gid
	 * @return int
	 */
	public int deleteTrolley(String tid) {
		int i = 0;
		String sql = "delete from trolley where tid= ?";
		try {
			i = DBTools.qr.update(sql, tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}


}
