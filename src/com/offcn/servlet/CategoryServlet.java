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

@WebServlet(description = "��Ʒ����Ŀ��Ʋ�",urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private CategoryService cs = new CategoryService();
	
	/**
	 * ���������
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
			throw new RuntimeException("���ӷ���ʧ��");
		}
	}
	
	/**
	 * ��ѯ���з���-��ҳ
	 * @param request
	 * @param response
	 */
	public void findCategoryList(HttpServletRequest request,HttpServletResponse response) {
		
		//Ĭ��û�о�null ���������ж�
		String current_page = request.getParameter("current_page");
		//������ѯ��������Ϣ
		int countRows = cs.findCountRows();
		
		//�ȸ㶨��ҳ����ز���
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
	 * ɾ������
	 * @param request
	 * @param response
	 */
	public void deleteCategory(HttpServletRequest request,HttpServletResponse response) {
		String gid = request.getParameter("gid");
		int i = cs.deleteCategory(gid);
		if(i > 0) {
			findCategoryList(request, response);
		}else {
			throw new RuntimeException("ɾ������ʧ��");
		}
	}
	
	/**
	 * �޸�-���ݻ���
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
	 * �޸�-��ʽ�޸�
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
			throw new RuntimeException("�޸ķ���ʧ��");
		}
	}
	
	/**
	 * ����ɾ��
	 * @param request
	 * @param response
	 */
	public void batchDelete(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("ids");
		int i = cs. batchDelete(ids);
		if(i > 0) {
			findCategoryList(request, response);
		}else {
			throw new RuntimeException("����ɾ��ʧ��");
		}
	}
	
	
}
