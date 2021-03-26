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
	 * ��������
	 * @param orders
	 * @return int
	 */
	public int createOrders(Orders orders) {
		return ordersDao.createOrders(orders);
	}

	/**
	 * �����ɹ�֮���޸Ķ���״̬
	 * @param orders_number
	 * @return int
	 */
	public int updateState(String orders_number) {
		return ordersDao.updateState(orders_number);
	}

	/**
	 * ��ѯ��ǰ�û�����
	 * @param session
	 * @return List<Orders>
	 */
	public List<Orders> findOrdersList(HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<Orders> ordersList = ordersDao.findOrdersList(user.getId());
		//ѭ��
		for (Orders orders : ordersList) {
			//��ȡ�����ţ����ݶ�����ȥ���ﳵ���в�ѯ��ǰ��������Ʒ����
			String orders_number = orders.getOrders_number();
			List<Trolley> trolleys = trolleyDao.findTrolleyListByOrdersNumber(orders_number);
			//�ڸ��ݵ�ǰ�Ĺ��ﳵ���е���ƷID��ѯ��Ʒ��Ϣ
			for (Trolley tro : trolleys) {
				//��ѯ��ǰ����ƷID
				int cid = tro.getCid();
				Commodity commodity = commodityDao.showCommodityById(cid+"");
				//����Ʒ����浽���ﳵ������
				tro.setCommodity(commodity);
			}
			//�����ﳵ���ϴ浽����������
			orders.setTrolleys(trolleys);
		}
		return ordersList;
	}

}
