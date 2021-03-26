package com.offcn.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.offcn.dao.CommodityDao;
import com.offcn.dao.TrolleyDao;
import com.offcn.entity.Commodity;
import com.offcn.entity.Trolley;
import com.offcn.entity.User;

/**
 * 购物车业务层
 * @author IBM
 */
public class TrolleyService {
	
	private TrolleyDao trolleyDao = new TrolleyDao();
	
	private CommodityDao commodityDao = new CommodityDao();
	
	/**
	 * 添加购物车
	 * @param cid
	 * @param session 
	 * @return Boolean
	 */
	public Boolean addCommodityToTrolley(String cid, HttpSession session) {
		
		/**
		 * 获取用户的对象
		 * 1、根据CID查询当前购车中是否存在未支付的本商品。
		 * 2、如果存在执行修改方法
		 * 3、如果不存在执行增加方法
		 */
		User user = (User)session.getAttribute("user");
		Trolley trolley =  trolleyDao.findTrolleyByCidAndUid(user.getId(),cid);
		int i = 0;
		if(null == trolley) {
			//执行增加
			i = trolleyDao.saveTrolley(user.getId(),cid);
		}else {
			//累加数量
			int number = trolley.getNumber()+1;
			//执行修改
			i = trolleyDao.updateTrolley(user.getId(),cid,number);
		}
		
		return i > 0 ? true:false;
	}

	/**
	 * 查询当前用户的购物车商品的数量
	 * @param session
	 * @return int
	 */
	public int showNumber(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(null == user) {
			return 0;
		}else {
			int i = trolleyDao.showNumber(user.getId());
			return i;
		}
		
	}

	/**
	 * 查询当前购物车所有商品
	 * @param session
	 * @return List<Trolley>
	 */
	public List<Trolley> findTrolleyListByUser(HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<Trolley> trolleyList = trolleyDao.findTrolleyListByUser(user.getId());
		//循环集合取CID查询商品存到当前集合的对象中
		for (Trolley trolley : trolleyList) {
			int cid = trolley.getCid();
			Commodity commodity = commodityDao.showCommodityById(cid+"");
			trolley.setCommodity(commodity);
		}
		
		return trolleyList;
	}

	/**
	 * 修改购物车商品数量
	 * @param tid
	 * @param number
	 * @param session 
	 * @return int
	 */
	public int addOrDeleteNumber(String tid, String number, HttpSession session) {
		return trolleyDao.addOrDeleteNumber(tid, number,session.getId());
	}

	/**
	 * 添加订单号
	 * @param orders_number
	 * @param session 
	 * @return int
	 */
	public int addOrders_number(String orders_number, HttpSession session) {
		User user = (User)session.getAttribute("user");
		return trolleyDao.addOrders_number(orders_number,user.getId());
	}
	
	/**
	 * 删除 商品
	 * @param cid
	 * @return int
	 */
	public int deleteTrolley(String tid) {
		return trolleyDao.deleteTrolley(tid);
	}
	
}
