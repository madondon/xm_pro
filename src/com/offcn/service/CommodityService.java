package com.offcn.service;

import java.util.List;

import com.offcn.dao.CategoryDao;
import com.offcn.dao.CommodityDao;
import com.offcn.entity.Category;
import com.offcn.entity.Commodity;
import com.offcn.util.PageTools;

public class CommodityService {

	private CommodityDao commodityDao = new CommodityDao();
	
	private CategoryDao categoryDao = new CategoryDao();
	
	/**
	 * ������Ʒ
	 * @param commodity
	 * @return int
	 */
	public int saveCommodity(Commodity commodity) {
		return commodityDao.saveCommodity(commodity);
	}

	/**
	 * �б��ѯ
	 * @param tools 
	 * @param commodity2 
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityList(PageTools tools, Commodity commodity2) {
		List<Commodity> list = commodityDao.findCommodityList(tools,commodity2);
		for (Commodity commodity : list) {
			int gid = commodity.getGid();
			//���÷����dao����ID��ѯ����
			Category category = categoryDao.toUpdate(gid+"");
			commodity.setCategory(category);
		}
		return list;
	}

	/**
	 * ��ȡ������
	 * @param commodity 
	 * @return int
	 */
	public int findCommodityCountRows(Commodity commodity) {
		return commodityDao.findCommodityCountRows(commodity);
	}

	/**
	 * ���ݷ��༰������ѯ��Ʒ����
	 * @param gid ����ID
	 * @param count ��ѯ����
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityList(int gid, int count) {
		return commodityDao.findCommodityList(gid,count);
	}

	/**
	 * ���ݷ��༰������ѯ��Ʒ����
	 * @param state С����״̬��
	 * @param count ��ѯ����
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityListByState(int state, int count) {
		return commodityDao.findCommodityListByState(state, count);
	}

	/**
	 * ��Ʒ������Ϣ��ȡ
	 * @param cid
	 * @return Commodity
	 */
	public Commodity showCommodityById(String cid) {
		return commodityDao.showCommodityById(cid);
	}
	
	/**
	 * ɾ�� ��Ʒ
	 * @param cid
	 * @return int
	 */
	public int deleteCommodity(String cid) {
		return commodityDao.deleteCommodity(cid);
	}
	
	/**
	 * �޸�-���ݻ���
	 * @param cid
	 * @return Category
	 */
	public Commodity toUpdate(String cid) {
		return commodityDao.toUpdate(cid);
	}

	/**
	 * �޸�-��ʽ�޸�
	 * @param commodity
	 * @return int
	 */
	public int doUpdate(Commodity commodity) {
		return commodityDao.doUpdate(commodity);
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 * @return int
	 */
	public int batchDelete(String ids) {
		return commodityDao.batchDelete(ids);
	}

}
