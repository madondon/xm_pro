package com.offcn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.offcn.entity.Category;
import com.offcn.service.CategoryService;
import com.offcn.util.BaseServlet;
import com.offcn.util.PageTools;

@WebServlet(description = "商品分类的控制层",urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private CategoryService cs = new CategoryService();
	
	/**
	 * 分类的增加
	 * @param request
	 * @param response
	 */
	public void saveCategory(HttpServletRequest request,HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time = request.getParameter("create_time");
		
		Category category = new Category(name, Integer.parseInt(state), Integer.parseInt(order_number), description, create_time);
		int i = cs.saveCategory(category);
		if(i > 0) {
			findCategoryList(request, response);
		}else {
			throw new RuntimeException("增加分类失败");
		}
	}
	
	/**
	 * 查询所有分类-分页
	 * @param request
	 * @param response
	 */
	public void findCategoryList(HttpServletRequest request,HttpServletResponse response) {
		
		//默认没有就null 工具类做判断
		String current_page = request.getParameter("current_page");
		//主动查询总条数信息
		int countRows = cs.findCountRows();
		
		//先搞定分页的相关参数
		PageTools tools = new PageTools(5, countRows, current_page);
		
		List<Category> categoryList =  cs.findCategoryList(tools);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("tools", tools);
		try {
			request.getRequestDispatcher("admin/category_list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 */
	public void deleteCategory(HttpServletRequest request,HttpServletResponse response) {
		String gid = request.getParameter("gid");
		int i = cs.deleteCategory(gid);
		if(i > 0) {
			findCategoryList(request, response);
		}else {
			throw new RuntimeException("删除分类失败");
		}
	}
	
	/**
	 * 修改-数据回显
	 * @param request
	 * @param response
	 */
	public void toUpdate(HttpServletRequest request,HttpServletResponse response) {
		String gid = request.getParameter("gid");
		Category category = cs.toUpdate(gid);
		request.setAttribute("category", category);
		try {
			request.getRequestDispatcher("admin/category_update.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改-正式修改
	 * @param request
	 * @param response
	 */
	public void doUpdate(HttpServletRequest request,HttpServletResponse response) {
		String gid = request.getParameter("gid");
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time = request.getParameter("create_time");
		
		Category category = new Category(Integer.parseInt(gid), name, Integer.parseInt(state), Integer.parseInt(order_number), description, create_time);
		int i = cs.doUpdate(category);
		if(i > 0) {
			findCategoryList(request, response);
		}else {
			throw new RuntimeException("修改分类失败");
		}
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @param response
	 */
	public void batchDelete(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("ids");
		int i = cs. batchDelete(ids);
		if(i > 0) {
			findCategoryList(request, response);
		}else {
			throw new RuntimeException("批量删除失败");
		}
	}
	
	
}
