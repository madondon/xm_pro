package com.offcn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.offcn.entity.Category;
import com.offcn.entity.Commodity;
import com.offcn.service.CategoryService;
import com.offcn.service.CommodityService;
import com.offcn.util.BaseServlet;

@WebServlet(description = "首页面专享",value = "/IndexServlet")
public class IndexServlet extends BaseServlet {
	
	
	//分类的业务层
	private CategoryService categoryService = new CategoryService();
	//调用商品的业务层
	private CommodityService commodityService = new CommodityService();
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 展示首页数据
	 */
	public void showIndexData(HttpServletRequest request,HttpServletResponse response){
		//获取首页面分类数据 前10条
		List<Category> categoryList = categoryService.findCategoryList(10);
		//{ 航空航天 } 根据分分类查询商品数据  两个参数， 分类的ID，一个是条数
		List<Commodity> HKHTCommodityList = commodityService.findCommodityList(2,5);
		//{ 武功秘籍 } 根据分分类查询商品数据  两个参数， 分类的ID，一个是条数
		List<Commodity> WGMJCommodityList = commodityService.findCommodityList(2,8);
		//{ 军火库 } 根据分分类查询商品数据  两个参数， 分类的ID，一个是条数
		List<Commodity> JHKCommodityList = commodityService.findCommodityList(3,10);
		//{ 明星单品（小分类） } 根据小分类查询商品数据  两个参数，小 分类的状态号，一个是条数
		List<Commodity> MXDPCommodityList = commodityService.findCommodityListByState(1,5);
		//{ 热门产品（小分类） } 根据小分类查询商品数据  两个参数，小 分类的状态号，一个是条数
		List<Commodity> RMCommodityList = commodityService.findCommodityListByState(2,4);
		
		//数据返回
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("HKHTCommodityList", HKHTCommodityList);
		request.setAttribute("WGMJCommodityList", WGMJCommodityList);
		request.setAttribute("JHKCommodityList", JHKCommodityList);
		request.setAttribute("MXDPCommodityList", MXDPCommodityList);
		request.setAttribute("RMCommodityList", RMCommodityList);
		//将各种数据返回到首页面中
		try {
			request.getRequestDispatcher("user/index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 商品详情信息获取
	 * @param request
	 * @param response
	 */
	public void showCommodityById(HttpServletRequest request,HttpServletResponse response) {
		String cid = request.getParameter("cid");
		Commodity commodity = commodityService.showCommodityById(cid);
		request.setAttribute("commodity", commodity);
		try {
			request.getRequestDispatcher("user/detail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
