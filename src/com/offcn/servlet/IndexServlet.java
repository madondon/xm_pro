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

@WebServlet(description = "��ҳ��ר��",value = "/IndexServlet")
public class IndexServlet extends BaseServlet {
	
	
	//�����ҵ���
	private CategoryService categoryService = new CategoryService();
	//������Ʒ��ҵ���
	private CommodityService commodityService = new CommodityService();
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * չʾ��ҳ����
	 */
	public void showIndexData(HttpServletRequest request,HttpServletResponse response){
		//��ȡ��ҳ��������� ǰ10��
		List<Category> categoryList = categoryService.findCategoryList(10);
		//{ ���պ��� } ���ݷַ����ѯ��Ʒ����  ���������� �����ID��һ��������
		List<Commodity> HKHTCommodityList = commodityService.findCommodityList(2,5);
		//{ �书�ؼ� } ���ݷַ����ѯ��Ʒ����  ���������� �����ID��һ��������
		List<Commodity> WGMJCommodityList = commodityService.findCommodityList(2,8);
		//{ ����� } ���ݷַ����ѯ��Ʒ����  ���������� �����ID��һ��������
		List<Commodity> JHKCommodityList = commodityService.findCommodityList(3,10);
		//{ ���ǵ�Ʒ��С���ࣩ } ����С�����ѯ��Ʒ����  ����������С �����״̬�ţ�һ��������
		List<Commodity> MXDPCommodityList = commodityService.findCommodityListByState(1,5);
		//{ ���Ų�Ʒ��С���ࣩ } ����С�����ѯ��Ʒ����  ����������С �����״̬�ţ�һ��������
		List<Commodity> RMCommodityList = commodityService.findCommodityListByState(2,4);
		
		//���ݷ���
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("HKHTCommodityList", HKHTCommodityList);
		request.setAttribute("WGMJCommodityList", WGMJCommodityList);
		request.setAttribute("JHKCommodityList", JHKCommodityList);
		request.setAttribute("MXDPCommodityList", MXDPCommodityList);
		request.setAttribute("RMCommodityList", RMCommodityList);
		//���������ݷ��ص���ҳ����
		try {
			request.getRequestDispatcher("user/index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��Ʒ������Ϣ��ȡ
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
