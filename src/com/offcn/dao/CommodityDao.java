package com.offcn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.offcn.entity.Category;
import com.offcn.entity.Commodity;
import com.offcn.util.DBTools;
import com.offcn.util.PageTools;

public class CommodityDao {

	/**
	 * ������Ʒ
	 * @param commodity
	 * @return int
	 */
	public int saveCommodity(Commodity commodity) {
		int i = 0;
		String sql = "insert into commodity (gid,name, color,size,price,description,full_description,pic,state,version, product_date)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?)";
		Object o [] = {commodity.getGid(),commodity.getName(),commodity.getColor(),commodity.getSize()
				,commodity.getPrice(),commodity.getDescription(),commodity.getFull_description()
				,commodity.getPic(),commodity.getState(),commodity.getVersion(),commodity.getProduct_date()};
		
		try {
			i = DBTools.qr.update(sql,o);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �б��ѯ
	 * @param tools 
	 * @param commodity 
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityList(PageTools tools, Commodity commodity) {
		List<Commodity> list = null;
		
		StringBuffer sbf = new StringBuffer("select * from commodity where 1=1 ");
		//����
		if(commodity.getGid() != -1) {
			sbf.append(" and gid = "+commodity.getGid()+"");
		}
		//С����״̬
		if(commodity.getState() != -1) {
			sbf.append(" and state = "+commodity.getState()+"");
		}
		//��Ʒ����
		if(null != commodity.getName() && !"".equals(commodity.getName())) {
			sbf.append(" and name like '%"+commodity.getName()+"%'");
		}
		//�۸�
		if(commodity.getPrice() != -1) {
			sbf.append(" and price = "+commodity.getPrice()+"");
		}
		
		//��ӷ�ҳ
		sbf.append(" limit ?,?");
		
		try {
			list = DBTools.qr.query(sbf.toString(), new BeanListHandler<>(Commodity.class),tools.getIndex(),tools.getSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ��ȡ������
	 * @param commodity 
	 * @return int
	 */
	public int findCommodityCountRows(Commodity commodity) {
		long rows = 0;
		
		StringBuffer sbf = new StringBuffer("select count(*) from commodity where 1=1 ");
		//����
		if(commodity.getGid() != -1) {
			sbf.append(" and gid = "+commodity.getGid()+"");
		}
		//С����״̬
		if(commodity.getState() != -1) {
			sbf.append(" and state = "+commodity.getState()+"");
		}
		//��Ʒ����
		if(null != commodity.getName() && !"".equals(commodity.getName())) {
			sbf.append(" and name like '%"+commodity.getName()+"%'");
		}
		//�۸�
		if(commodity.getPrice() != -1) {
			sbf.append(" and price = "+commodity.getPrice()+"");
		}
		
		
		try {
			rows = (long)DBTools.qr.query(sbf.toString(), new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)rows;
	}

	/**
	 * ���ݷ��༰������ѯ��Ʒ����
	 * @param gid ����ID
	 * @param count ��ѯ����
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityList(int gid, int count) {
		List<Commodity> list = null;
		String sql = "select * from commodity where gid = ? order by product_date desc limit ?";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Commodity.class),gid,count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ���ݷ��༰������ѯ��Ʒ����
	 * @param state С����״̬��
	 * @param count ��ѯ����
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommodityListByState(int state, int count) {
		List<Commodity> list = null;
		String sql = "select * from commodity where state = ? order by product_date desc limit ?";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Commodity.class),state,count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ��Ʒ������Ϣ��ȡ
	 * @param cid
	 * @return Commodity
	 */
	public Commodity showCommodityById(String cid) {
		Commodity commodity = null;
		String sql = "select * from commodity where cid = ?";
		try {
			commodity = DBTools.qr.query(sql, new BeanHandler<>(Commodity.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commodity;
	}
	
	/**
	 * ɾ����Ʒ
	 * @param gid
	 * @return int
	 */
	public int deleteCommodity(String cid) {
		int i = 0;
		String sql = "delete from commodity where cid= ?";
		try {
			i = DBTools.qr.update(sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �޸�-���ݻ���
	 * @param gid
	 * @return Category
	 */
	public Commodity toUpdate(String cid) {
		Commodity commodity =  null;
		String sql = "select * from commodity where cid = ?";
		try {
			commodity = DBTools.qr.query(sql, new BeanHandler<>(Commodity.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commodity;
	}

	/**
	 * �޸�-��ʽ�޸�
	 * @param category
	 * @return int
	 */
	public int doUpdate(Commodity commodity) {
		int i = 0;
		Object[] o   = {commodity.getGid(),commodity.getName(),commodity.getColor(),commodity.getSize(),commodity.getPrice(),commodity.getDescription(),commodity.getFull_description(),commodity.getPic(),commodity.getState(),commodity.getVersion(),commodity.getProduct_date(),commodity.getCid()};
		String sql = "update commodity set gid =?, name = ? , color = ?, size= ? , price = ?, description = ?, full_description= ?, pic = ?, state = ?, version = ?, product_date = ? where cid = ?";
		try {
			i = DBTools.qr.update(sql, o);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * ����ɾ��
	 * @param ids
	 * @return int
	 */
	public int batchDelete(String ids) {
		int i = 0;
		String sql = "delete from commodity where cid in ("+ids+")";
		try {
			i = DBTools.qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	
}
