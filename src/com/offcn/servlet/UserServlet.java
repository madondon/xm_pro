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
 * �û��Ŀ��Ʋ�
 * @author IBM
 *
 */
@WebServlet("/UserServlet")
@MultipartConfig
public class UserServlet extends BaseServlet{
	
	private UserService us = new UserService();
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * У���ֻ������Ƿ��Ѿ���ע��
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
	 * �û�ע��
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
		 * �ϴ�ͷ�񲿷�
		 */
		//��ȡ�ϴ��ؼ�����
		try {
			Part part = request.getPart("photo");
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
				request.getRequestDispatcher("user/register.jsp").forward(request, response);
			}
			
			//�������·����Ĭ���ϴ���������tomcat�ļ����£�
			//String path = request.getServletContext().getRealPath("upload");
			String path = "D:\\Mi_Load";
			//�ж�·�����ļ����Ƿ����
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			save_photo = UUID.randomUUID()+fileName;
			//д���ļ�
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
			 throw new RuntimeException("�û�ע��ʧ��!");
		}
	}
	
	/**
	 * �����ֻ���֤��
	 * @param request
	 * @param response
	 */
	public void sendPhoneCode(HttpServletRequest request, HttpServletResponse response) {
		String phone_number = request.getParameter("phone_number");
		//��ȡsession����
		HttpSession session = request.getSession();
		//0������δע�� 1����֤�뷢��ʧ�� 2�����ͳɹ�
		int i = us.sendPhoneCode(phone_number,session);
		try {
			response.getWriter().print(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��֤��½
	 * @param request
	 * @param response
	 */
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
		//��ȡsession����
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
			request.setAttribute("msg", "��¼ʧ�ܣ��ֻ��Ż�����֤���������");
			try {
				request.getRequestDispatcher("user/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * ����Ա��¼
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
			request.setAttribute("msg", "�˺Ż����������");
			try {
				request.getRequestDispatcher("admin/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��ѯ�����û���Ϣ
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
