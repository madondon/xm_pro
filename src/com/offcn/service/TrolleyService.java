package com.offcn.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.offcn.dao.CommodityDao;
import com.offcn.dao.TrolleyDao;
import com.offcn.entity.Commodity;
import com.offcn.entity.Trolley;
import com.offcn.entity.User;

/**
 * ���ﳵҵ���
 * @author IBM
 */
public class TrolleyService {
	
	private TrolleyDao trolleyDao = new TrolleyDao();
	
	private CommodityDao commodityDao = new CommodityDao();
	
	/**
	 * ��ӹ��ﳵ
	 * @param cid
	 * @param session 
	 * @return Boolean
	 */
	public Boolean addCommodityToTrolley(String cid, HttpSession session) {
		
		/**
		 * ��ȡ�û��Ķ���
		 * 1������CID��ѯ��ǰ�������Ƿ����δ֧���ı���Ʒ��
		 * 2���������ִ���޸ķ���
		 * 3�����������ִ�����ӷ���
		 */
		User user = (User)session.getAttribute("user");
		Trolley trolley =  trolleyDao.findTrolleyByCidAndUid(user.getId(),cid);
		int i = 0;
		if(null == trolley) {
			//ִ������
			i = trolleyDao.saveTrolley(user.getId(),cid);
		}else {
			//�ۼ�����
			int number = trolley.getNumber()+1;
			//ִ���޸�
			i = trolleyDao.updateTrolley(user.getId(),cid,number);
		}
		
		return i > 0 ? true:false;
	}

	/**
	 * ��ѯ��ǰ�û��Ĺ��ﳵ��Ʒ������
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
	 * ��ѯ��ǰ���ﳵ������Ʒ
	 * @param session
	 * @return List<Trolley>
	 */
	public List<Trolley> findTrolleyListByUser(HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<Trolley> trolleyList = trolleyDao.findTrolleyListByUser(user.getId());
		//ѭ������ȡCID��ѯ��Ʒ�浽��ǰ���ϵĶ�����
		for (Trolley trolley : trolleyList) {
			int cid = trolley.getCid();
			Commodity commodity = commodityDao.showCommodityById(cid+"");
			trolley.setCommodity(commodity);
		}
		
		return trolleyList;
	}

	/**
	 * �޸Ĺ��ﳵ��Ʒ����
	 * @param tid
	 * @param number
	 * @param session 
	 * @return int
	 */
	public int addOrDeleteNumber(String tid, String number, HttpSession session) {
		return trolleyDao.addOrDeleteNumber(tid, number,session.getId());
	}

	/**
	 * ��Ӷ�����
	 * @param orders_number
	 * @param session 
	 * @return int
	 */
	public int addOrders_number(String orders_number, HttpSession session) {
		User user = (User)session.getAttribute("user");
		return trolleyDao.addOrders_number(orders_number,user.getId());
	}
	
	/**
	 * ɾ�� ��Ʒ
	 * @param cid
	 * @return int
	 */
	public int deleteTrolley(String tid) {
		return trolleyDao.deleteTrolley(tid);
	}
	
}
