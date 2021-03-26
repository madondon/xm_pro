package com.offcn.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.offcn.entity.User;
import com.offcn.service.UserService;
import com.offcn.util.BaseServlet;
import com.offcn.util.MiTools;

/**
 * 用户的控制层
 * @author IBM
 *
 */
@WebServlet("/UserServlet")
@MultipartConfig
public class UserServlet extends BaseServlet{
	
	private UserService us = new UserService();
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 校验手机号码是否已经被注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void validate_phone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String phone_number = request.getParameter("phone_number");
		boolean b = us.validate_phone(phone_number);
		PrintWriter pw = response.getWriter();
		pw.print(b+"");
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 */
	public void registerUser(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String phone_number = request.getParameter("phone_number");
		String area = request.getParameter("area");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String save_photo ="";
		/**
		 * 上传头像部分
		 */
		//获取上传控件对象
		try {
			Part part = request.getPart("photo");
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
				request.getRequestDispatcher("user/register.jsp").forward(request, response);
			}
			
			//定义输出路径（默认上传到服务器tomcat文件夹下）
			//String path = request.getServletContext().getRealPath("upload");
			String path = "D:\\Mi_Load";
			//判断路径及文件夹是否存在
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			save_photo = UUID.randomUUID()+fileName;
			//写入文件
			part.write(path+"/"+save_photo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User user = new User(name, Integer.parseInt(sex), phone_number, area, username, password,save_photo, MiTools.getStringDate());
		int i = us.registerUser(user);
		if(i > 0) {
			try {
				response.sendRedirect("user/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			 throw new RuntimeException("用户注册失败!");
		}
	}
	
	/**
	 * 发送手机验证码
	 * @param request
	 * @param response
	 */
	public void sendPhoneCode(HttpServletRequest request, HttpServletResponse response) {
		String phone_number = request.getParameter("phone_number");
		//获取session对象
		HttpSession session = request.getSession();
		//0、号码未注册 1、验证码发送失败 2、发送成功
		int i = us.sendPhoneCode(phone_number,session);
		try {
			response.getWriter().print(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证登陆
	 * @param request
	 * @param response
	 */
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
		//获取session对象
		HttpSession session = request.getSession();
		String phone_number = request.getParameter("phone_number");
		String code = request.getParameter("code");
		
		boolean b = us.checkLogin(phone_number,code,session);
		if(b == true) {
			try {
				response.sendRedirect("user/redirect.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("msg", "登录失败，手机号或者验证码输入错误");
			try {
				request.getRequestDispatcher("user/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 */
	public void checkAdminLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		
		HttpSession session = request.getSession();
		
		Boolean b = us.checkAdminLogin(user,session);
		if(b == true) {
			try {
				response.sendRedirect("admin/main.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("msg", "账号或者密码错误！");
			try {
				request.getRequestDispatcher("admin/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查询所有用户信息
	 * @param request
	 * @param response
	 */
	public void findUserList(HttpServletRequest request, HttpServletResponse response) {
		List<User> userList = us.findUserList();
		request.setAttribute("userList", userList);
		try {
			request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
