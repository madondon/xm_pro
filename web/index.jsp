<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>大导航</title>
</head>
<body>
	
	<a href="user/register.jsp">用户注册</a><br>
	<a href="user/login.jsp">用户登录</a><br>
	<a href="admin/login.jsp">管理员登录</a><br>
	<a href="<%=basePath%>IndexServlet?mark=showIndexData">首页面数据</a><br>
</body>
</html>