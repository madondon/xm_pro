package com.offcn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.offcn.entity.Category;
import com.offcn.util.DBTools;
import com.offcn.util.PageTools;
/**
 * ����־ò�
 * @author IBM
 */
public class CategoryDao {
	
	/**
	 * ���������
	 * @param category
	 * @return int
	 */
	public int saveCategory(Category category) {
		int i = 0;
		String sql = "insert into category (name,state,order_number,description,create_time) values (?,?,?,?,?)";
		Object o []  = {category.getName(),category.getState(),category.getOrder_number(),category.getDescription(),category.getCreate_time()};
		
		try {
			i = DBTools.qr.update(sql, o);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}

	/**
	 * ��ѯ���з���
	 * @param tools 
	 * @return List<Category>
	 */
	public List<Category> findCategoryList(PageTools tools) {
		List<Category> list = null;
		String sql = "select * from category order by order_number asc limit ?,?";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Category.class),tools.getIndex(),tools.getSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ɾ������
	 * @param gid
	 * @return int
	 */
	public int deleteCategory(String gid) {
		int i = 0;
		String sql = "delete from category where gid= ?";
		try {
			i = DBTools.qr.update(sql, gid);
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
	public Category toUpdate(String gid) {
		Category category =  null;
		String sql = "select * from category where gid = ?";
		try {
			category = DBTools.qr.query(sql, new BeanHandler<>(Category.class),gid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	/**
	 * �޸�-��ʽ�޸�
	 * @param category
	 * @return int
	 */
	public int doUpdate(Category category) {
		int i = 0;
		Object o []  = {category.getName(),category.getState(),category.getOrder_number(),category.getDescription(),category.getCreate_time(),category.getGid()};
		String sql = "update category set name =?, state = ? , order_number = ?, description= ? , create_time = ? where gid = ?";
		
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
		String sql = "delete from category where gid in ("+ids+")";
		try {
			i = DBTools.qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ������ѯ��������Ϣ
	 * @return int
	 */
	public int findCountRows() {
		long countrows = 0;
		String sql = "select count(*) from category";
		try {
			countrows = (long)DBTools.qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)countrows;
	}

	/**
	 * Ϊ��������Ʒ��������ʹ��
	 */
	public List<Category> findCategoryList() {
		List<Category> list = null;
		String sql = "select * from category order by order_number asc";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ���ط�������ȡ��ҳ���������
	 * @param i
	 * @return List<Category>
	 */
	public List<Category> findCategoryList(int i) {
		List<Category> list = null;
		String sql = "select * from category order by order_number asc limit ?";
		try {
			list = DBTools.qr.query(sql, new BeanListHandler<>(Category.class),i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
