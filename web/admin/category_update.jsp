<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加类别</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="<%=basePath %>admin/js/My97DatePicker/WdatePicker.js"></script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">修改类别</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>类别信息</span></div>
    
    	<form action="<%=basePath %>CategoryServlet" method="get">
    	<!--  隐藏方法 -->
    	<input name="mark" value="doUpdate" type="hidden">
    	<!-- 隐藏ID -->
    	<input name="gid" value="${category.gid }" type="hidden">
    	
	    <ul class="forminfo">
		    <li><label>类别名称</label><input name="name" type="text" value="${category.name }" class="dfinput" /><i>类别名称不能超过30个字符</i></li>
		    <li><label>是否启用</label>
			    <cite>
				    <input name="state" type="radio" value="1" <c:if test="${category.state == 1 }">checked</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
				    
				    <input name="state" type="radio" value="0" <c:if test="${category.state == 0 }">checked</c:if>/> 否
			    </cite>
		    </li>
		    
		    <li><label>排序序号</label>
		    	<input name="order_number" type="text" value="${category.order_number }" class="dfinput" /><i>输入数字，越大越靠后排列</i>
		    </li>
		    
		    <li><label>类别描述</label>
		    	<textarea name="description" cols="" rows="" class="textinput">${category.description }</textarea>
		    </li>
		    
		    <li><label>创建时间</label>
		    	
		    	<input class="Wdate" style="width: 345px;height: 32px;line-height: 32px;"  onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="create_time" type="text" class="dfinput" value="${category.create_time }" />
		    </li>
		    
		    <li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    </form>
    </div>
</body>
</html>
