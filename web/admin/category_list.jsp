<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品分类</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

<script type="text/javascript">

function  add_category(){
	window.location = "admin/category_add.jsp";
}
$(function(){
	//全选全不选
	$("#checkALl").click(function(){
		$("[name='ids']").prop("checked",this.checked);	
	});
	
	//批量删除
	$("#batchDelete").click(function(){
		var ids = "";
		//读取已经选择的checkbox的ID
		$("[name='ids']:checked").each(function(){
			ids +=","+$(this).val();
		});
		
		ids = ids.substring(1);
		if("" == ids){
			alert("请选择删除项");
			return;
		}
		if(confirm("删除这么多，你良心不会痛么？")){
			//请求后台进行删除
			window.location = "<%=basePath %>CategoryServlet?mark=batchDelete&ids="+ids;
		}
	});
})

</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">分类管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        
        
        <li style="cursor: pointer;" onclick="add_category()"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加类别</li>
        <li style="cursor: pointer;" id="batchDelete"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
    
    <table class="tablelist">
    	<thead>
    	<tr>
	        <th><input name="" id="checkALl" type="checkbox" value=""/></th>
	        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
	        <th>类别名称</th>
	        <th>启用状态</th>
	        <th>排序序号</th>
	        <th>创建时间</th>
	        <th>描述</th>
	        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${categoryList }" var="cate" varStatus="i">
	        	<tr>
			        <td><input name="ids" type="checkbox" value="${cate.gid }"/></td>
			        <td>${i.count }</td>
			        <td>${cate.name }</td>
			        <td>${cate.state == 0 ? '禁用':'启用' }</td>
			        <td>${cate.order_number }</td>
			        <td>${cate.create_time }</td>
			        <td>${cate.description }</td>
			        <td>
			        	<a href="<%=basePath %>CategoryServlet?mark=toUpdate&gid=${cate.gid}">修改</a> &nbsp; <a href="<%=basePath %>CategoryServlet?mark=deleteCategory&gid=${cate.gid}">删除</a>
			        </td>
		        </tr>
        	</c:forEach>
         
        
        </tbody>
    </table>
    
   
	<div class="pagin">
		<div class="message">共<i class="blue">${tools.countRows }</i>条记录，当前显示第&nbsp;<i class="blue">${tools.current_page } &nbsp;</i>页</div>
	    <ul class="paginList">
	      <li class="paginItem"><a href="<%=basePath %>CategoryServlet?mark=findCategoryList&current_page=1">首页</a></li>
	      <li class="paginItem"><a href="<%=basePath %>CategoryServlet?mark=findCategoryList&current_page=${tools.pre_page}">上一页</a></li>
	      <li class="paginItem"><a href="<%=basePath %>CategoryServlet?mark=findCategoryList&current_page=${tools.next_page}">下一页</a></li>
	      <li class="paginItem"><a href="<%=basePath %>CategoryServlet?mark=findCategoryList&current_page=${tools.count_page}">尾页</a></li>
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
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
