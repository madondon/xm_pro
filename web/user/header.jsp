<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	
$(function(){
	//回显当前购物车商品数量
	var user = '${sessionScope.user}';
	if(null == user){
		return;
	}
	
	//判断当前购物车状态是否有值
	var tspan = $("#tspan").text();
	if(tspan != "0"){
		return;
	}
	//获取数量
	$.ajax({
		url:"<%=basePath%>TrolleyServlet",
		data:{"mark":"showNumber"},
		type:"post",
		dataType:"text",
		success:function(bt){
			$("#tspan").html(bt);
		}
	});
	
	//点击购物车去购物车详情页面
	$("#got").click(function(){
		window.location = "<%=basePath%>TrolleyServlet?mark=findTrolleyListByUser";
	});
	
});

</script>
</head>
<body>

<div class="box">
      <div class="inner whiteGL">
          <div class="left">
              <a class="mix" href="">仿小米商城-学习专用</a>
          </div>
          <div class="right">
          	  <c:if test="${sessionScope.user == null }">
	          	   <a class="mix" href="user/login.jsp">登录</a>
	               <a href="user/register.jsp">注册</a>
          	  </c:if>
              <c:if test="${sessionScope.user != null }">
              	   <img alt="" style="width: 30px;height: 30px;border-radius: 15px" src="http://localhost:8080/${sessionScope.user.photo }">
	               <a href="#">${sessionScope.user.name }</a>
          	  </c:if>
              <a class="max"  href="">消息通知</a>
              <a href="Javascript:void(0)" id="got" >购物车( <span id="tspan" style="color: orange;font-weight: bold;">0</span> )</a>
          </div>
      </div>
  </div>
  <div class="logo">
      <div class="logo_left">
          <div>
              <a href="javascript:void(0);" title="小米官网"><img class="xiaomi" src="<%=basePath %>img/logo.jpg"></a>
          </div>
      </div>
      <ul class="logo_center orangeGL">
          <a href="">小米手机</a>
          <a href="">红米</a>
          <a href="">笔记本</a>
          <a href="">电视</a>
          <a href="">盒子</a>
          <a href="">新品</a>
          <a href="">路由器</a>
          <a href="">智能硬件</a>
          <a href="">服务</a>
          <a href="">社区</a>
      </ul>
	  <form name="form" class="logo_right" action="<%=basePath %>UserCommodityServlet?mark=findCommodityUser" method="post">

         <div class="logo_input"><input type="text" name="name">
             <div class="logo_input_div">
                
             </div>
         </div>
          <a class="logo_right_a"><img src="<%=basePath %>img/find.jpg" onclick="form.submit();"></a>
      </form>
  </div>
</body>
</html>