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

@WebServlet(description = "�������Ʋ�",value = "/OrdersServlet")
public class OrdersServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private OrdersService ordersService = new OrdersService();
	private TrolleyService trolleyService = new TrolleyService();
	
	
	/**
	 * ����֧��
	 * @param request
	 * @param response
	 */
	public void createOrder(HttpServletRequest request,HttpServletResponse response) {
		//�������������
		String orders_number = UUID.randomUUID().toString();
		
		String uid = request.getParameter("uid");
		String price = request.getParameter("price");
		
		Orders orders = new Orders(orders_number, Integer.parseInt(uid), Double.parseDouble(price), MiTools.getStringDate());
		
		int i = ordersService.createOrders(orders);
		if(i > 0) {
			
			//����һ�����������޸ĺ��ﳵ��Ʒ������һ���������루������ﳵ��Ʒ��
			HttpSession session = request.getSession();
			//�޸Ĺ��ﳵ����-��Ӷ�����
		
			
			request.setAttribute("orders", orders);
			try {
				//�����������֮��ֱ����ת��֧��ҳ��
				request.getRequestDispatcher("alipay/index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else {
			throw new RuntimeException("��������ʧ��");
		}
	}
	/**
	 * ��������
	 * @param request
	 * @param response
	 */
	public void createOrders(HttpServletRequest request,HttpServletResponse response) {
		//�������������
		String orders_number = UUID.randomUUID().toString();
		
		String uid = request.getParameter("uid");
		String sum_price = request.getParameter("sum_price");
		
		Orders orders = new Orders(orders_number, Integer.parseInt(uid), Double.parseDouble(sum_price), MiTools.getStringDate());
		
		int i = ordersService.createOrders(orders);
		if(i > 0) {
			
			//����һ�����������޸ĺ��ﳵ��Ʒ������һ���������루������ﳵ��Ʒ��
			HttpSession session = request.getSession();
			//�޸Ĺ��ﳵ����-��Ӷ�����
			int k = trolleyService.addOrders_number(orders_number,session);
			if(k <= 0) {
				throw new RuntimeException("���ﳵ���ʧ��");
			}
			
			request.setAttribute("orders", orders);
			try {
				//�����������֮��ֱ����ת��֧��ҳ��
				request.getRequestDispatcher("alipay/index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else {
			throw new RuntimeException("��������ʧ��");
		}
	}
	
	/**
	 * �����ɹ�֮���޸Ķ���״̬
	 * @param request
	 * @param response
	 */
	public void paySuccess(HttpServletRequest request,HttpServletResponse response) {
		
		String orders_number = request.getParameter("orders_number");
		//�޸Ĺ��ﳵ����-��Ӷ�����
		int i = ordersService.updateState(orders_number);
		if(i > 0) {
			//�����б�
			System.out.println("ȥ�����б�");
		}else {
			throw new RuntimeException("�����޸�״̬ʧ��");
		}
	}
	
	/**
	 * ��ѯ������Ϣ
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
