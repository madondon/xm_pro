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

            // 获取参数, 用来识别用户想请求的方法
            String mark = req.getParameter("mark");

            // 判断该参数是否存在, 不存在,抛出异常
            if(mark == null || mark.trim().isEmpty()){
                throw new RuntimeException("您没有传递 mark 参数! 无法确定您想调用的方法");
            }

            //得到当前类的 class 对象
            Class<? extends BaseServlet> c = this.getClass();

            //通过查找本类这子类中的方法名称（不是get和set是值类中的方法）
            Method method = null;
            try{
                method = c.getMethod(mark,HttpServletRequest.class, HttpServletResponse.class);
            } catch(Exception e){
                throw new RuntimeException("您要调用的方法"+mark+",它不存在!");
            }

            //如果发现类中有这个方法通过反射调用那个对应的方法
            try{
                method.invoke(this,req,resp);  
            } catch(Exception e){                    
                throw new RuntimeException(e);
            }
    }
}