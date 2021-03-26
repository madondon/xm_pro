package com.offcn.service;

import java.util.List;

import com.offcn.dao.CategoryDao;
import com.offcn.entity.Category;
import com.offcn.util.PageTools;
/**
 * 分类业务层
 * @author IBM
 */
public class CategoryService {
	
	private CategoryDao cd = new CategoryDao();
	
	/**
	 * 分类的增加
	 * @param category
	 * @return int
	 */
	public int saveCategory(Category category) {
		return cd.saveCategory(category);
	}

	/**
	 * 查询所有分类
	 * @param tools 
	 * @return List<Category>
	 */
	public List<Category> findCategoryList(PageTools tools) {
		return cd.findCategoryList(tools);
	}

	/**
	 * 删除分类
	 * @param gid
	 * @return int
	 */
	public int deleteCategory(String gid) {
		return cd.deleteCategory(gid);
	}

	/**
	 * 修改-数据回显
	 * @param gid
	 * @return Category
	 */
	public Category toUpdate(String gid) {
		return cd.toUpdate(gid);
	}

	/**
	 * 修改-正式修改
	 * @param category
	 * @return int
	 */
	public int doUpdate(Category category) {
		return cd.doUpdate(category);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return int
	 */
	public int batchDelete(String ids) {
		return cd.batchDelete(ids);
	}

	/**
	 * 主动查询总条数信息
	 * @return int
	 */
	public int findCountRows() {
		return cd.findCountRows();
	}

	/**
	 * 为了增加商品回显数据使用
	 */
	public List<Category> findCategoryList() {
		return cd.findCategoryList();
	}

	/**
	 * 重载方法。获取首页面分类数据
	 * @param i
	 * @return List<Category>
	 */
	public List<Category> findCategoryList(int i) {
		return cd.findCategoryList(i);
	}

	

}
