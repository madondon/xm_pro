package com.offcn.service;

import java.util.List;

import com.offcn.dao.CategoryDao;
import com.offcn.entity.Category;
import com.offcn.util.PageTools;
/**
 * ����ҵ���
 * @author IBM
 */
public class CategoryService {
	
	private CategoryDao cd = new CategoryDao();
	
	/**
	 * ���������
	 * @param category
	 * @return int
	 */
	public int saveCategory(Category category) {
		return cd.saveCategory(category);
	}

	/**
	 * ��ѯ���з���
	 * @param tools 
	 * @return List<Category>
	 */
	public List<Category> findCategoryList(PageTools tools) {
		return cd.findCategoryList(tools);
	}

	/**
	 * ɾ������
	 * @param gid
	 * @return int
	 */
	public int deleteCategory(String gid) {
		return cd.deleteCategory(gid);
	}

	/**
	 * �޸�-���ݻ���
	 * @param gid
	 * @return Category
	 */
	public Category toUpdate(String gid) {
		return cd.toUpdate(gid);
	}

	/**
	 * �޸�-��ʽ�޸�
	 * @param category
	 * @return int
	 */
	public int doUpdate(Category category) {
		return cd.doUpdate(category);
	}

	/**
	 * ����ɾ��
	 * @param ids
	 * @return int
	 */
	public int batchDelete(String ids) {
		return cd.batchDelete(ids);
	}

	/**
	 * ������ѯ��������Ϣ
	 * @return int
	 */
	public int findCountRows() {
		return cd.findCountRows();
	}

	/**
	 * Ϊ��������Ʒ��������ʹ��
	 */
	public List<Category> findCategoryList() {
		return cd.findCategoryList();
	}

	/**
	 * ���ط�������ȡ��ҳ���������
	 * @param i
	 * @return List<Category>
	 */
	public List<Category> findCategoryList(int i) {
		return cd.findCategoryList(i);
	}

	

}
