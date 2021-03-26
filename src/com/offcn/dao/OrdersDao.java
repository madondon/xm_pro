package com.offcn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.offcn.entity.Orders;
import com.offcn.util.DBTools;

public class OrdersDao {

	/**
	 * 创建订单
	 * @param orders
	 * @return int
	 */
	public int createOrders(Orders orders) {
		int i = 0;
		String sql = "insert into orders (orders_number,uid,sum_price,create_time) values (?,?,?,?)";
		try {
			i = DBTools.qr.update(sql, orders.getOrders_number(),orders.getUid(),orders.getSum_price(),orders.getCreate_time());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 订单成功之后修改订单状态
	 * @param orders_number
	 * @return
	 */
	public int updateState(String orders_number) {
		int i = 0;
		String sql = "update orders set state = 1 where orders_number=?";
		try {
			i = DBTools.qr.update(sql, orders_number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 查询当前用户订单
	 * @param session
	 * @return List<Orders>
	 */
	public List<Orders> findOrdersList(int id) {
		List<Orders> list = null;
		String sql = "select * from orders where uid= ?";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Orders.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
