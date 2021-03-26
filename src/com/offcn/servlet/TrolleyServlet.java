package com.offcn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.offcn.entity.Trolley;
import com.offcn.service.TrolleyService;
import com.offcn.util.BaseServlet;

@WebServlet(description = "购物车控制层",value = "/TrolleyServlet")
public class TrolleyServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private TrolleyService trolleyService = new TrolleyService();
	
	/**
	 * 添加购物车
	 * @param request
	 * @param response
	 */
	public void addCommodityToTrolley(HttpServletRequest request,HttpServletResponse response){
		String cid = request.getParameter("cid");
		HttpSession session = request.getSession();
		Boolean b = trolleyService.addCommodityToTrolley(cid,session);
		try {
			response.getWriter().print(b+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询当前用户的购物车商品的数量
	 * @param request
	 * @param response
	 */
	public void showNumber(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		int number = trolleyService.showNumber(session);
		try {
			response.getWriter().print(number);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询当前购物车所有商品
	 * @param request
	 * @param response
	 */
	public void findTrolleyListByUser(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<Trolley> trolleyList = trolleyService.findTrolleyListByUser(session);
		request.setAttribute("trolleyList", trolleyList);
		try {
			request.getRequestDispatcher("user/trolley.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改购物车商品数量
	 * @param request
	 * @param response
	 */
	public void addOrDeleteNumber(HttpServletRequest request,HttpServletResponse response) {
		String tid = request.getParameter("tid");
		String number = request.getParameter("number");
		HttpSession session = request.getSession();
		int i = trolleyService.addOrDeleteNumber(tid,number,session);
		if(i > 0) {
			findTrolleyListByUser(request,response);
		}else {
			throw new RuntimeException("修改数量失败");
		}
	}
	
	/**
	 * 删除购物车
	 * @param request
	 * @param response
	 */
	public void deleteTrolley(HttpServletRequest request,HttpServletResponse response) {
		String tid = request.getParameter("tid");
		int i = trolleyService.deleteTrolley(tid);
		if(i > 0) {
			findTrolleyListByUser(request, response);
		}else {
			throw new RuntimeException("删除购物车失败");
		}
}
}
