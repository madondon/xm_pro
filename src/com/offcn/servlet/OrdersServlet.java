package com.offcn.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.offcn.entity.Orders;
import com.offcn.service.OrdersService;
import com.offcn.service.TrolleyService;
import com.offcn.util.BaseServlet;
import com.offcn.util.MiTools;

@WebServlet(description = "订单控制层",value = "/OrdersServlet")
public class OrdersServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private OrdersService ordersService = new OrdersService();
	private TrolleyService trolleyService = new TrolleyService();
	
	
	/**
	 * 立即支付
	 * @param request
	 * @param response
	 */
	public void createOrder(HttpServletRequest request,HttpServletResponse response) {
		//生成随机订单号
		String orders_number = UUID.randomUUID().toString();
		
		String uid = request.getParameter("uid");
		String price = request.getParameter("price");
		
		Orders orders = new Orders(orders_number, Integer.parseInt(uid), Double.parseDouble(price), MiTools.getStringDate());
		
		int i = ordersService.createOrders(orders);
		if(i > 0) {
			
			//订单一经创建立即修改后购物车商品给返还一个订单号码（清除购物车商品）
			HttpSession session = request.getSession();
			//修改购物车内容-添加订单号
		
			
			request.setAttribute("orders", orders);
			try {
				//订单创建完成之后直接跳转到支付页面
				request.getRequestDispatcher("alipay/index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else {
			throw new RuntimeException("订单创建失败");
		}
	}
	/**
	 * 创建订单
	 * @param request
	 * @param response
	 */
	public void createOrders(HttpServletRequest request,HttpServletResponse response) {
		//生成随机订单号
		String orders_number = UUID.randomUUID().toString();
		
		String uid = request.getParameter("uid");
		String sum_price = request.getParameter("sum_price");
		
		Orders orders = new Orders(orders_number, Integer.parseInt(uid), Double.parseDouble(sum_price), MiTools.getStringDate());
		
		int i = ordersService.createOrders(orders);
		if(i > 0) {
			
			//订单一经创建立即修改后购物车商品给返还一个订单号码（清除购物车商品）
			HttpSession session = request.getSession();
			//修改购物车内容-添加订单号
			int k = trolleyService.addOrders_number(orders_number,session);
			if(k <= 0) {
				throw new RuntimeException("购物车清空失败");
			}
			
			request.setAttribute("orders", orders);
			try {
				//订单创建完成之后直接跳转到支付页面
				request.getRequestDispatcher("alipay/index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else {
			throw new RuntimeException("订单创建失败");
		}
	}
	
	/**
	 * 订单成功之后修改订单状态
	 * @param request
	 * @param response
	 */
	public void paySuccess(HttpServletRequest request,HttpServletResponse response) {
		
		String orders_number = request.getParameter("orders_number");
		//修改购物车内容-添加订单号
		int i = ordersService.updateState(orders_number);
		if(i > 0) {
			//订单列表
			System.out.println("去订单列表");
		}else {
			throw new RuntimeException("订单修改状态失败");
		}
	}
	
	/**
	 * 查询订单信息
	 * @param request
	 * @param response
	 */
	public void findOrdersList(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<Orders>  ordersList = ordersService.findOrdersList(session);
		request.setAttribute("ordersList", ordersList);
		try {
			request.getRequestDispatcher("user/orders_history.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
