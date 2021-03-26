<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户信息</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

<script type="text/javascript">

// old write 
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});


</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">商品管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        
        <!-- 
        
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
         -->
        <li style="cursor: pointer;" onclick="batchDelete()"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th><input name="" type="checkbox" value="" checked="checked"/></th>
		        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
		        <th>姓名</th>
		        <th>性别</th>
		        <th>电话号码</th>
		        <th>所在地区</th>
		        <th>权限</th>
		        <th>账号</th>
		       	<th>头像</th>
		       	<th>注册时间</th>
		       	<th>操作</th>
	        </tr>
	     </thead>
        <tbody>
        <c:forEach items="${userList }" var="u" varStatus="i">
        	<tr>
		        <td><input name="ids" type="checkbox" /></td>
		        <td>${i.count }</td>
		        <td>${u.name }</td>
		        <td>${u.sex==0?'女':'男' }</td>
		        <td>${u.phone_number }</td>
		        <td>${u.area }</td>
		        <td>${u.manager==0?'管理员':'普通用户' }</td>
		        <td>${u.username }</td>
		       	<td align="center"><img width="100px" height="50px" src="http://localhost:8080/${u.photo }"/></td>
		       	<td>${u.create_time }</td>
		       	<td>
		       		<a href="#">修改</a>
		       		<a href="#">删除</a>
		       	</td>
	        </tr>
        </c:forEach>
        	
        </tbody>
    </table>
   
    <div class="pagin">
    	<div class="message">共<i class="blue"></i>条记录，当前显示第&nbsp;<i class="blue">&nbsp;</i>页</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="">首页</a></li>
	         <li class="paginItem"><a href="">上一页</a></li>
	         <li class="paginItem"><a href="">下一页</a></li>
	         <li class="paginItem"><a href="">尾页</a></li>
	         
	        
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
