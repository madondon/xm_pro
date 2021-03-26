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
	 * 增加商品
	 * @param commodity
	 * @return int
	 */
	public int saveCommodity(Commodity commodity) {
		return commodityDao.saveCommodity(commodity);
	}

	/**
	 * 列表查询
	 * @param tools 
	 * @param commodity2 
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityList(PageTools tools, Commodity commodity2) {
		List<Commodity> list = commodityDao.findCommodityList(tools,commodity2);
		for (Commodity commodity : list) {
			int gid = commodity.getGid();
			//调用分类的dao根据ID查询对象
			Category category = categoryDao.toUpdate(gid+"");
			commodity.setCategory(category);
		}
		return list;
	}

	/**
	 * 获取总条数
	 * @param commodity 
	 * @return int
	 */
	public int findCommodityCountRows(Commodity commodity) {
		return commodityDao.findCommodityCountRows(commodity);
	}

	/**
	 * 根据分类及条数查询商品数据
	 * @param gid 分类ID
	 * @param count 查询条数
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityList(int gid, int count) {
		return commodityDao.findCommodityList(gid,count);
	}

	/**
	 * 根据分类及条数查询商品数据
	 * @param state 小分类状态码
	 * @param count 查询条数
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityListByState(int state, int count) {
		return commodityDao.findCommodityListByState(state, count);
	}

	/**
	 * 商品详情信息获取
	 * @param cid
	 * @return Commodity
	 */
	public Commodity showCommodityById(String cid) {
		return commodityDao.showCommodityById(cid);
	}
	
	/**
	 * 删除 商品
	 * @param cid
	 * @return int
	 */
	public int deleteCommodity(String cid) {
		return commodityDao.deleteCommodity(cid);
	}
	
	/**
	 * 修改-数据回显
	 * @param cid
	 * @return Category
	 */
	public Commodity toUpdate(String cid) {
		return commodityDao.toUpdate(cid);
	}

	/**
	 * 修改-正式修改
	 * @param commodity
	 * @return int
	 */
	public int doUpdate(Commodity commodity) {
		return commodityDao.doUpdate(commodity);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return int
	 */
	public int batchDelete(String ids) {
		return commodityDao.batchDelete(ids);
	}

}
