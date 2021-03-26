package com.offcn.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.offcn.dao.CommodityDao;
import com.offcn.dao.OrdersDao;
import com.offcn.dao.TrolleyDao;
import com.offcn.entity.Commodity;
import com.offcn.entity.Orders;
import com.offcn.entity.Trolley;
import com.offcn.entity.User;

public class OrdersService {

	private OrdersDao ordersDao = new OrdersDao();
	
	private TrolleyDao trolleyDao = new TrolleyDao();
	
	private CommodityDao commodityDao = new CommodityDao();
	/**
	 * 创建订单
	 * @param orders
	 * @return int
	 */
	public int createOrders(Orders orders) {
		return ordersDao.createOrders(orders);
	}

	/**
	 * 订单成功之后修改订单状态
	 * @param orders_number
	 * @return int
	 */
	public int updateState(String orders_number) {
		return ordersDao.updateState(orders_number);
	}

	/**
	 * 查询当前用户订单
	 * @param session
	 * @return List<Orders>
	 */
	public List<Orders> findOrdersList(HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<Orders> ordersList = ordersDao.findOrdersList(user.getId());
		//循环
		for (Orders orders : ordersList) {
			//获取订单号，根据订单号去购物车表中查询当前订单的商品数据
			String orders_number = orders.getOrders_number();
			List<Trolley> trolleys = trolleyDao.findTrolleyListByOrdersNumber(orders_number);
			//在根据当前的购物车的中的商品ID查询商品信息
			for (Trolley tro : trolleys) {
				//查询当前的商品ID
				int cid = tro.getCid();
				Commodity commodity = commodityDao.showCommodityById(cid+"");
				//将商品对象存到购物车对象中
				tro.setCommodity(commodity);
			}
			//将购物车集合存到订单对象中
			orders.setTrolleys(trolleys);
		}
		return ordersList;
	}

}
