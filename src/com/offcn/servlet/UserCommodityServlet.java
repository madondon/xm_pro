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

@WebServlet(description = "商品查询", value = "/UserCommodityServlet")
@MultipartConfig
public class UserCommodityServlet extends BaseServlet{
	
	private CategoryService categoryService = new CategoryService();
	
	private CommodityService commodityService = new CommodityService();
	
	private static final long serialVersionUID = 1L;


	public void findCommodityUser(HttpServletRequest request,HttpServletResponse response) {

		//模糊查询数据获取
		String name = request.getParameter("name");

		int d_gid = -1;
		int d_state = -1;
		double d_pr = -1;

		//条件为-1
		Commodity commodity = new Commodity(d_gid, name, d_pr, d_state);

		//获取当前页
		String current_page = request.getParameter("current_page");
		//获取总条数
		int countRows = commodityService.findCommodityCountRows(commodity);

		PageTools tools = new PageTools(5, countRows, current_page);

		List<Commodity> commodityList =  commodityService.findCommodityList(tools,commodity);

		//获取当前分类数据
		List<Category> categoryList = categoryService.findCategoryList();

		//数据保存到request
		request.setAttribute("commodity", commodity);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("tools", tools);
		request.setAttribute("commodityList", commodityList);
		try {
			//跳转页面
			request.getRequestDispatcher("user/commodity_list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
