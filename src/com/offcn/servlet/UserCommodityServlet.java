package com.offcn.servlet;

import com.offcn.entity.Category;
import com.offcn.entity.Commodity;
import com.offcn.service.CategoryService;
import com.offcn.service.CommodityService;
import com.offcn.util.BaseServlet;
import com.offcn.util.PageTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(description = "��Ʒ��ѯ", value = "/UserCommodityServlet")
@MultipartConfig
public class UserCommodityServlet extends BaseServlet{
	
	private CategoryService categoryService = new CategoryService();
	
	private CommodityService commodityService = new CommodityService();
	
	private static final long serialVersionUID = 1L;


	public void findCommodityUser(HttpServletRequest request,HttpServletResponse response) {

		//ģ����ѯ���ݻ�ȡ
		String name = request.getParameter("name");

		int d_gid = -1;
		int d_state = -1;
		double d_pr = -1;

		//����Ϊ-1
		Commodity commodity = new Commodity(d_gid, name, d_pr, d_state);

		//��ȡ��ǰҳ
		String current_page = request.getParameter("current_page");
		//��ȡ������
		int countRows = commodityService.findCommodityCountRows(commodity);

		PageTools tools = new PageTools(5, countRows, current_page);

		List<Commodity> commodityList =  commodityService.findCommodityList(tools,commodity);

		//��ȡ��ǰ��������
		List<Category> categoryList = categoryService.findCategoryList();

		//���ݱ��浽request
		request.setAttribute("commodity", commodity);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("tools", tools);
		request.setAttribute("commodityList", commodityList);
		try {
			//��תҳ��
			request.getRequestDispatcher("user/commodity_list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
