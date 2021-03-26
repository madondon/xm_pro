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
<title>修改商品</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=basePath %>admin/js/My97DatePicker/WdatePicker.js">
$(function(){
	  $('#state').click(function(){
	      alert("haha");
	   })
	})
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">修改商品</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>商品信息</span></div>
    

    <form action="<%=basePath %>CommodityServlet" method="post" enctype="multipart/form-data">
    	<!-- 隐藏方法名 -->
    	<input type="hidden" name="mark" value="doUpdate">
	    <ul class="forminfo">
	    	 
	    	 <!-- 商品分类信息 -->
	    	 <li>
	    	 	<label>商品分类</label>
	    	<input name="gname" type="text" class="dfinput" value="${category.name }" readonly/>
	    	 </li>
	    	 
	    	 <input name="gid" type="hidden" class="dfinput" value="${category.gid }"/>
	    	  <input name="cid" type="hidden" class="dfinput" value="${commodity.cid }"/>
		    <li><label>商品名称</label><input name="name" type="text" class="dfinput" value="${commodity.name }"/><i>商品名称不能超过30个字符</i></li>
		    <li><label>颜色</label><input name="color" type="text" class="dfinput" value="${commodity.color }"/></li>
		    <li><label>规格尺寸</label><input name="size" type="text" class="dfinput" value="${commodity.size }"/></li>
		    <li><label>单价</label><input name="price" type="text" class="dfinput" value="${commodity.price }"/></li>
		    <li><label>简介</label>
		    	<textarea name="description" cols="10" rows="10" class="textinput" style="height: 80px" >${commodity.description }</textarea>
		    </li>
		    <li><label>完整介绍</label>
		    	<textarea name="full_description" cols="10" rows="10" class="textinput" style="height: 80px">${commodity.full_description }</textarea>
		    </li>
		    <li><label>商品展示图</label>
		    	<input name="pic" type="file" value="${commodity.pic }"/><img width="100px" height="50px" src="http://localhost:8080/${commodity.pic }">
		    </li>
		    <li><label>商品小类别</label>
			    <cite>
				    <input name="state"  type="radio" value="0" <c:if test="${commodity.state == 0}">checked</c:if> />正常&nbsp;&nbsp;&nbsp;&nbsp;
				    <input name="state"  type="radio" value="1" <c:if test="${commodity.state == 1}">checked</c:if>/>热门产品
				    <input name="state"  type="radio" value="2" <c:if test="${commodity.state == 2}">checked</c:if>/>为你推荐
				    <input name="state" type="radio" value="3" <c:if test="${commodity.state == 3}">checked</c:if>/>新品
				    <input name="state" type="radio" value="4" <c:if test="${commodity.state == 4}">checked</c:if>/>小米明星单品
			    </cite>
		    </li>
		    <li><label>型号</label><input name="version" type="text" class="dfinput" value="${commodity.version }" /></li>
		    
		    <li><label>生产日期</label>
		    	<input class="Wdate" style="width: 345px;height: 32px;line-height: 32px;" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="product_date" type="text" class="dfinput" value="${commodity.product_date }" />
		    </li>
		    
		    <li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    </form>
    </div>
</body>
</html>
