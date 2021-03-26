package com.offcn.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.offcn.entity.Category;
import com.offcn.entity.Commodity;
import com.offcn.service.CategoryService;
import com.offcn.service.CommodityService;
import com.offcn.util.BaseServlet;
import com.offcn.util.DBTools;
import com.offcn.util.PageTools;

@WebServlet(description = "��Ʒ���Ʋ�", value = "/CommodityServlet")
@MultipartConfig
public class CommodityServlet extends BaseServlet{
	
	private CategoryService categoryService = new CategoryService();
	
	private CommodityService commodityService = new CommodityService();
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ����-���Է�������
	 * @param request
	 * @param response
	 */
	public void findCategoryList(HttpServletRequest request,HttpServletResponse response) {
		List<Category> categoryList =  categoryService.findCategoryList();
		request.setAttribute("categoryList", categoryList);
		try {
			request.getRequestDispatcher("admin/commodity_add.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ������Ʒ
	 * @param request
	 * @param response
	 */
	public void saveCommodity(HttpServletRequest request,HttpServletResponse response) {
		
		String gid = request.getParameter("gid");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String full_description = request.getParameter("full_description");
		String state = request.getParameter("state");
		String version = request.getParameter("version");
		String product_date = request.getParameter("product_date");
		
		String pic ="";
		/**
		 * �ϴ�ͷ�񲿷�
		 */
		//��ȡ�ϴ��ؼ�����
		try {
			Part part = request.getPart("pic");
			//ͨ��part��ȡ��ǰͼƬ��URL��Ϣ��ͼƬ��·����
			String url = part.getHeader("Content-disposition");
			//��ȡͼƬ����
			String fileName = url.substring(url.indexOf("filename")+10,url.length()-1);
			System.out.println(fileName);
			//��ȡ��ǰ�ļ��ĺ�׺ ���жϺ�׺�Ƿ�Ϸ�
			String suffix = fileName.substring(fileName.indexOf(".")+1);
			System.out.println("suffix:"+suffix);
			if(!suffix.equals("jpg") && !suffix.equals("png") && !suffix.equals("gif") && !suffix.equals("jpeg")) {
				request.setAttribute("msg", "ͼƬֻ֧��JPG��PNG��GIF��JPEG��ʽ");
				request.getRequestDispatcher("admin/commodity_add.jsp").forward(request, response);
			}
			
			String path = "D:\\Mi_Load";
			//�ж�·�����ļ����Ƿ����
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			pic = UUID.randomUUID()+fileName;
			//д���ļ�
			part.write(path+"/"+pic);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//��װ����
		Commodity commodity = new Commodity(Integer.parseInt(gid), name, color, size, 
				Double.parseDouble(price), description, full_description, pic, 
				Integer.parseInt(state), version, product_date);
		
		int i = commodityService.saveCommodity(commodity);
		if(i > 0) {
			findCommodityListClean(request,response);
		}else {
			throw new RuntimeException("������Ʒʧ��");
		}
	}

	/**
	 * ������Ʒ
	 * @param request
	 * @param response
	 */
	public void findCommodityList(HttpServletRequest request,HttpServletResponse response) {
		
		//ģ����ѯ���ݻ�ȡ
		String gid = request.getParameter("gid");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String state = request.getParameter("state");
		
		int d_gid = -1;
		int d_state = -1;
		double d_pr = -1;
		//�ж�GID
		if(null != gid) {
			d_gid = Integer.parseInt(gid);
		}
		//�ж�״̬
		if(null != state) {
			d_state = Integer.parseInt(state);
		}
		if(null != price && !"".equals(price)) {
			d_pr = Double.parseDouble(price);
		}
		//�������Ϊ�գ���ֵ����Ĭ�϶�-1
		Commodity commodity = new Commodity(d_gid, name, d_pr, d_state);
		
		//��ȡ��ǰҳ
		String current_page = request.getParameter("current_page");
		//��ȡ������
		int countRows = commodityService.findCommodityCountRows(commodity);
		
		PageTools tools = new PageTools(5, countRows, current_page);
		
		List<Commodity> commodityList =  commodityService.findCommodityList(tools,commodity);
		
		//��ȡ��ǰ��������
		List<Category> categoryList = categoryService.findCategoryList();
		
		request.setAttribute("commodity", commodity);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("tools", tools);
		request.setAttribute("commodityList", commodityList);
		try {
			request.getRequestDispatcher("admin/commodity_list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ��Ʒ�����ӡ��޸�֮�󷵻��б�ר��
	 * @param request
	 * @param response
	 */
	public void findCommodityListClean(HttpServletRequest request,HttpServletResponse response) {
		
		//ģ����ѯ���ݻ�ȡ
		int d_gid = -1;
		int d_state = -1;
		double d_pr = -1;
		
		//�������Ϊ�գ���ֵ����Ĭ�϶�-1
		Commodity commodity = new Commodity(d_gid, "", d_pr, d_state);
		
		//��ȡ��ǰҳ
		String current_page = request.getParameter("current_page");
		//��ȡ������
		int countRows = commodityService.findCommodityCountRows(commodity);
		
		PageTools tools = new PageTools(5, countRows, current_page);
		
		List<Commodity> commodityList =  commodityService.findCommodityList(tools,commodity);
		
		//��ȡ��ǰ��������
		List<Category> categoryList = categoryService.findCategoryList();
		
		request.setAttribute("commodity", commodity);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("tools", tools);
		request.setAttribute("commodityList", commodityList);
		try {
			request.getRequestDispatcher("admin/commodity_list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 */
	public void deleteCommodity(HttpServletRequest request,HttpServletResponse response) {
		String cid = request.getParameter("cid");
		int i = commodityService.deleteCommodity(cid);
		if(i > 0) {
			findCommodityListClean(request, response);
		}else {
			throw new RuntimeException("ɾ����Ʒʧ��");
		}
	}
	
	/**
	 * �޸�-���ݻ���
	 * @param request
	 * @param response
	 */
	public void toUpdate(HttpServletRequest request,HttpServletResponse response) {
		String cid = request.getParameter("cid");
		Commodity commodity = commodityService.toUpdate(cid);
		request.setAttribute("commodity", commodity);
		Category category =  null;
		String sql = "select * from category where gid = ?";
		try {
			category = DBTools.qr.query(sql, new BeanHandler<>(Category.class),commodity.getGid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("category", category);
		try {
			request.getRequestDispatcher("admin/commodity_update.jsp").forward(request, response);
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
		String cid = request.getParameter("cid");
		String gid = request.getParameter("gid");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String full_description = request.getParameter("full_description");
		String state = request.getParameter("state");
		String version = request.getParameter("version");
		String product_date = request.getParameter("product_date");
		String pic ="";
		/**
		 * �ϴ�ͷ�񲿷�
		 */
		//��ȡ�ϴ��ؼ�����
		try {
			Part part = request.getPart("pic");
			//ͨ��part��ȡ��ǰͼƬ��URL��Ϣ��ͼƬ��·����
			String url = part.getHeader("Content-disposition");
			//��ȡͼƬ����
			String fileName = url.substring(url.indexOf("filename")+10,url.length()-1);
			System.out.println(fileName);
			//��ȡ��ǰ�ļ��ĺ�׺ ���жϺ�׺�Ƿ�Ϸ�
			String suffix = fileName.substring(fileName.indexOf(".")+1);
			System.out.println("suffix:"+suffix);
			if(!suffix.equals("jpg") && !suffix.equals("png") && !suffix.equals("gif") && !suffix.equals("jpeg")) {
				request.setAttribute("msg", "ͼƬֻ֧��JPG��PNG��GIF��JPEG��ʽ");
							}
			
			String path = "D:\\Mi_Load";
			//�ж�·�����ļ����Ƿ����
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			pic = UUID.randomUUID()+fileName;
			//д���ļ�
			part.write(path+"/"+pic);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Commodity commodity = new Commodity(Integer.parseInt(cid),Integer.parseInt(gid), name, color,size,Double.parseDouble(price),description,full_description,pic,Integer.parseInt(state),version,product_date);
		int i = commodityService.doUpdate(commodity);
		if(i > 0) {
			findCommodityListClean(request, response);
		}else {
			throw new RuntimeException("�޸���Ʒʧ��");
		}
	}
	
	/**
	 * ����ɾ��
	 * @param request
	 * @param response
	 */
	public void batchDelete(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("ids");
		int i = commodityService. batchDelete(ids);
		if(i > 0) {
			findCommodityListClean(request, response);
		}else {
			throw new RuntimeException("������Ʒɾ��ʧ��");
		}
	}
	
	
}
