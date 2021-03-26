<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品信息</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

<script type="text/javascript">
function add_goods(){
	window.location = "<%=basePath %>CommodityServlet?mark=findCategoryList";
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
			window.location = "<%=basePath %>CommodityServlet?mark=batchDelete&ids="+ids;
		}
	});
})
function fenYe(c){
	//给表单中的隐藏的当前页赋值
	$("#current_page").val(c);
	//提交表单
	$("#bd").submit();
}

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
        
        <li style="cursor: pointer;" onclick="add_goods()"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加商品</li>
        <li style="cursor: pointer;" id="batchDelete"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>
    </div>
    <div style="height: 45px;background-color:#FAFAD2;border-radius: 7px;line-height: 45px;vertical-align: middle;text-align: center;">
		<!-- 模糊查询 -->
        <form id="bd" action="<%=basePath %>CommodityServlet?mark=findCommodityList" method="post" >
        	
        	<!-- 隐藏一个当前页数[通过脚本动态赋值] -->
        	<input type="hidden" id="current_page" name="current_page" value="">
        	分类：
        	<select class="dfinput" style="width: 150px" name="gid">
        		<option value="-1">== 请选择 ==</option>
        		<c:forEach items="${categoryList }" var="cate">
        			<option value="${cate.gid }" <c:if test="${commodity.gid == cate.gid }">selected</c:if>>${cate.name }</option>
        		</c:forEach>
        	</select>
        	&nbsp;
        	热推状态：
        	<select class="dfinput" style="width: 150px" name="state">
        		<option value="-1" <c:if test="${commodity.state == -1 }">selected</c:if>>== 请选择 ==</option>
        		<option value="0" <c:if test="${commodity.state == 0 }">selected</c:if>>正常</option>
        		<option value="1" <c:if test="${commodity.state == 1 }">selected</c:if>>热门产品</option>
        		<option value="2" <c:if test="${commodity.state == 2 }">selected</c:if>>明星单品</option>
        		<option value="3" <c:if test="${commodity.state == 3 }">selected</c:if>>促销产品</option>
        	</select>
        	&nbsp;
        	商品名称：
        	<input type="text" class="dfinput" style="width: 150px" name="name" value="${commodity.name }">
        	&nbsp;
        	商品价格：
        	<input type="text" class="dfinput" style="width: 150px" name="price" value="<c:if test="${commodity.price != -1 }">${commodity.price}</c:if>">
        	
        	
        	&nbsp;
        	<input type="submit" value="查询" style="width: 80px;height: 35px">
        </form>
    </div>
    <table class="tablelist" style="margin-top: 5px">
    	<thead>
	    	<tr>
		        <th><input name="" type="checkbox" value="" id="checkALl" /></th>
		        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
		        <th>商品类别</th>
		        <th>商品名称</th>
		        <th>商品颜色</th>
		        <th>商品价格</th>
		        <th width="10%">简介</th>
		        <th width="20%">详情</th>
		       	<th>商品展示图</th>
		       	<th>是否热推</th>
		       	<th>型号</th>
		       	<th>生产日期</th>
		       	<th>操作</th>
	        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${commodityList }" var="comm" varStatus="i">
        		<tr>
			        <td><input name="ids" type="checkbox" value="${comm.cid }"/></td>
			        <td>${i.count }</td>
			        <td>${comm.category.name }</td>
			        <td>${comm.name}</td>
			        <td>${comm.color}</td>
			        <td>${comm.price}</td>
			        <td width="10%">${comm.description}</td>
			        <td width="20%">${comm.full_description}</td>
			       	<td>
			       		<img width="100px" height="50px" src="http://localhost:8080/${comm.pic }">
			       	</td>
			       	<td>
			       		<c:if test="${comm.state ==0  }">正常</c:if>
			       		<c:if test="${comm.state ==1  }">热门产品</c:if>
			       		<c:if test="${comm.state ==2  }">明星单品</c:if>
			       		<c:if test="${comm.state ==3  }">促销</c:if>
			       	</td>
			       	<td>${comm.version}</td>
			       	<td>${comm.product_date}</td>
			       	<td>
			       		<a href="<%=basePath %>CommodityServlet?mark=toUpdate&cid=${comm.cid}">修改</a> &nbsp;<a href="<%=basePath %>CommodityServlet?mark=deleteCommodity&cid=${comm.cid}">删除</a>
			       	</td>
		        </tr>
        	
        	
        	</c:forEach>
        
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${tools.countRows }</i>条记录，当前显示第&nbsp;<i class="blue">&nbsp;${tools.current_page }</i>页</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(1)">首页</a></li>
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(${tools.pre_page})">上一页</a></li>
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(${tools.next_page})">下一页</a></li>
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(${tools.count_page})">尾页</a></li>
	        
        </ul>
    </div>
</div>
    
</body>
</html>
