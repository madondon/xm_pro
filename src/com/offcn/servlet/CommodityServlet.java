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

@WebServlet(description = "商品控制层", value = "/CommodityServlet")
@MultipartConfig
public class CommodityServlet extends BaseServlet{
	
	private CategoryService categoryService = new CategoryService();
	
	private CommodityService commodityService = new CommodityService();
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 增加-回显分类数据
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
	 * 增加商品
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
		 * 上传头像部分
		 */
		//获取上传控件对象
		try {
			Part part = request.getPart("pic");
			//通过part获取当前图片的URL信息（图片的路径）
			String url = part.getHeader("Content-disposition");
			//读取图片名称
			String fileName = url.substring(url.indexOf("filename")+10,url.length()-1);
			System.out.println(fileName);
			//获取当前文件的后缀 ，判断后缀是否合法
			String suffix = fileName.substring(fileName.indexOf(".")+1);
			System.out.println("suffix:"+suffix);
			if(!suffix.equals("jpg") && !suffix.equals("png") && !suffix.equals("gif") && !suffix.equals("jpeg")) {
				request.setAttribute("msg", "图片只支持JPG、PNG、GIF、JPEG格式");
				request.getRequestDispatcher("admin/commodity_add.jsp").forward(request, response);
			}
			
			String path = "D:\\Mi_Load";
			//判断路径及文件夹是否存在
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			pic = UUID.randomUUID()+fileName;
			//写入文件
			part.write(path+"/"+pic);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//封装对象
		Commodity commodity = new Commodity(Integer.parseInt(gid), name, color, size, 
				Double.parseDouble(price), description, full_description, pic, 
				Integer.parseInt(state), version, product_date);
		
		int i = commodityService.saveCommodity(commodity);
		if(i > 0) {
			findCommodityListClean(request,response);
		}else {
			throw new RuntimeException("增加商品失败");
		}
	}

	/**
	 * 增加商品
	 * @param request
	 * @param response
	 */
	public void findCommodityList(HttpServletRequest request,HttpServletResponse response) {
		
		//模糊查询数据获取
		String gid = request.getParameter("gid");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String state = request.getParameter("state");
		
		int d_gid = -1;
		int d_state = -1;
		double d_pr = -1;
		//判断GID
		if(null != gid) {
			d_gid = Integer.parseInt(gid);
		}
		//判断状态
		if(null != state) {
			d_state = Integer.parseInt(state);
		}
		if(null != price && !"".equals(price)) {
			d_pr = Double.parseDouble(price);
		}
		//如果条件为空，数值类型默认都-1
		Commodity commodity = new Commodity(d_gid, name, d_pr, d_state);
		
		//获取当前页
		String current_page = request.getParameter("current_page");
		//获取总条数
		int countRows = commodityService.findCommodityCountRows(commodity);
		
		PageTools tools = new PageTools(5, countRows, current_page);
		
		List<Commodity> commodityList =  commodityService.findCommodityList(tools,commodity);
		
		//获取当前分类数据
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
	 * 查询商品，增加、修改之后返回列表专享。
	 * @param request
	 * @param response
	 */
	public void findCommodityListClean(HttpServletRequest request,HttpServletResponse response) {
		
		//模糊查询数据获取
		int d_gid = -1;
		int d_state = -1;
		double d_pr = -1;
		
		//如果条件为空，数值类型默认都-1
		Commodity commodity = new Commodity(d_gid, "", d_pr, d_state);
		
		//获取当前页
		String current_page = request.getParameter("current_page");
		//获取总条数
		int countRows = commodityService.findCommodityCountRows(commodity);
		
		PageTools tools = new PageTools(5, countRows, current_page);
		
		List<Commodity> commodityList =  commodityService.findCommodityList(tools,commodity);
		
		//获取当前分类数据
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
	 * 删除分类
	 * @param request
	 * @param response
	 */
	public void deleteCommodity(HttpServletRequest request,HttpServletResponse response) {
		String cid = request.getParameter("cid");
		int i = commodityService.deleteCommodity(cid);
		if(i > 0) {
			findCommodityListClean(request, response);
		}else {
			throw new RuntimeException("删除商品失败");
		}
	}
	
	/**
	 * 修改-数据回显
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
	 * 修改-正式修改
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
		 * 上传头像部分
		 */
		//获取上传控件对象
		try {
			Part part = request.getPart("pic");
			//通过part获取当前图片的URL信息（图片的路径）
			String url = part.getHeader("Content-disposition");
			//读取图片名称
			String fileName = url.substring(url.indexOf("filename")+10,url.length()-1);
			System.out.println(fileName);
			//获取当前文件的后缀 ，判断后缀是否合法
			String suffix = fileName.substring(fileName.indexOf(".")+1);
			System.out.println("suffix:"+suffix);
			if(!suffix.equals("jpg") && !suffix.equals("png") && !suffix.equals("gif") && !suffix.equals("jpeg")) {
				request.setAttribute("msg", "图片只支持JPG、PNG、GIF、JPEG格式");
							}
			
			String path = "D:\\Mi_Load";
			//判断路径及文件夹是否存在
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			pic = UUID.randomUUID()+fileName;
			//写入文件
			part.write(path+"/"+pic);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Commodity commodity = new Commodity(Integer.parseInt(cid),Integer.parseInt(gid), name, color,size,Double.parseDouble(price),description,full_description,pic,Integer.parseInt(state),version,product_date);
		int i = commodityService.doUpdate(commodity);
		if(i > 0) {
			findCommodityListClean(request, response);
		}else {
			throw new RuntimeException("修改商品失败");
		}
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @param response
	 */
	public void batchDelete(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("ids");
		int i = commodityService. batchDelete(ids);
		if(i > 0) {
			findCommodityListClean(request, response);
		}else {
			throw new RuntimeException("批量商品删除失败");
		}
	}
	
	
}
