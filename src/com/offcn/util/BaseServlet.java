package com.offcn.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{

            // ��ȡ����, ����ʶ���û�������ķ���
            String mark = req.getParameter("mark");

            // �жϸò����Ƿ����, ������,�׳��쳣
            if(mark == null || mark.trim().isEmpty()){
                throw new RuntimeException("��û�д��� mark ����! �޷�ȷ��������õķ���");
            }

            //�õ���ǰ��� class ����
            Class<? extends BaseServlet> c = this.getClass();

            //ͨ�����ұ����������еķ������ƣ�����get��set��ֵ���еķ�����
            Method method = null;
            try{
                method = c.getMethod(mark,HttpServletRequest.class, HttpServletResponse.class);
            } catch(Exception e){
                throw new RuntimeException("��Ҫ���õķ���"+mark+",��������!");
            }

            //��������������������ͨ����������Ǹ���Ӧ�ķ���
            try{
                method.invoke(this,req,resp);  
            } catch(Exception e){                    
                throw new RuntimeException(e);
            }
    }
}