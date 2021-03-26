<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//图片的狗皮膏药
	String ipath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>

<html lang="en">
<head>
    <title>购物车</title>
    <link rel="stylesheet" href="<%=basePath %>css/index.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(function(){
	//全选全不选
	$("#checkAll").click(function(){
		$("[name='ids']").prop("checked",this.checked);	
	});
})
function addOrDeleteNumber(tid,number){
	if(number <= 0){
		alert("商品数量最小为1");
		return;
	}
	
	window.location = "<%=basePath %>TrolleyServlet?mark=addOrDeleteNumber&tid="+tid+"&number="+number;
	
}
function pay(uid,sum_price){
	var user = '${sessionScope.user}';
	if(null == user){
		alert("请重新登陆在支付订单");
		return;
	}
	
	window.location = "<%=basePath %>OrdersServlet?mark=createOrders&uid="+uid+"&sum_price="+sum_price;
}
	
</script>
</head>
<body>
    <div class="order_head">
        <div class="head_background">
            <div class="head_box">
                <a href="index.html" class="head_left_a"><img src="img/logo.jpg" alt="" class="head_left_p"></a>
                <h1 class="head_h1">我的购物车</h1>
                <div class="head_right">
                    <span class="head_right_in"> 用户名称/用户账号 </span>
                    <span class="head_right_in">|</span>
                    <a href="<%=basePath %>OrdersServlet?mark=findOrdersList" class="head_right_in">我的订单</a>
                </div>

            </div>
        </div>
    </div>
    <div class="trolley_background">
        <div class="trolley_background_in">
            <div class="tro_tab_h">
                <div class="col tro_tab_check">
                    
                    
                    <input name="" type="checkbox" value="" id="checkAll" style="margin-left:30px;margin-top:30px"/>

                </div>
                <div class="col tro_tab_img">
                </div>
                <p class="col tro_tab_name">商品名称</p>
                <div class="col tro_tab_price">单价</div>
                <div class="col tro_tab_num">数量</div>
                <div class="col tro_tab_total">小计</div>
                <div class="col tro_tab_action">操作</div>
            </div>
            
            <!-- 关于全局变量及计算 -->
            <c:set var="comm_number" value="0"></c:set>
            <c:set var="sum_price" value="0"></c:set>
            
            <c:forEach items="${trolleyList }" var="tro">
            	 <!-- 累加数量 -->
            	 <c:set var="comm_number" value="${comm_number + 1 }"></c:set>
            	 <!-- 累加价格 将小计的价格进行累加 -->
	             <c:set var="sum_price" value="${sum_price + tro.commodity.price * tro.number }"></c:set>
	                
	            <div class="tro_tab_h1">
	                <div class="col tro_tab_check">
	                    <h1 class="tro_tab_check_p" style="background-color: #fff">
							<input name="ids" type="checkbox" value="${tro.tid }"/>
						</h1>
	                    <span class="tro_tab_check_sp"></span>
	
	                </div>
	                <div class="col tro_tab_img">
	                    <img src="<%=ipath %>${tro.commodity.pic}" alt="">
	
	                </div>
	                <div class="col tro_tab_name">
	                <!--<h2 tro_tab_name_h2>小米电视4A 32英寸 黑色 32英寸</h2>-->
	               <li class="tro_tab_name_li1" style="font-size: 16px;"> ${tro.commodity.name } &nbsp; ${tro.commodity.color }</li>
	                </div>
	                <div class="col tro_tab_price">
	                    <span  id="price">${tro.commodity.price }</span><span>元</span>
	                </div>
	                <div class="col tro_tab_num">
	                    <a class="tro_tab_num_p1" id="subtract" href="javascript:void(0)" onclick="addOrDeleteNumber(${tro.tid},${tro.number -1})">-</a>
	                    <input type="text" value="${tro.number }" id="num">
	                    <a class="tro_tab_num_p2" id="plus" href="javascript:void(0)" onclick="addOrDeleteNumber(${tro.tid},${tro.number+1})">+</a>
	                </div>
	                <div class="col tro_tab_total "><span class="tro_tab_total_value" id="prices" >${tro.commodity.price * tro.number }</span>元
	                </div>
	                 <input type="hidden" value="${tro.tid }" id="tid">
	            
	                <a class="col tro_tab_action" style="cursor: pointer;width: 40px;height: 40px;" href="<%=basePath %>TrolleyServlet?mark=deleteTrolley&tid=${tro.tid}">删除</a>
	            </div>
	           
            </c:forEach>
            
            
        </div>
        
        <div class="tro_close_bot ">
            <p class="tro_close_p "> <a href="<%=basePath%>IndexServlet?mark=showIndexData">继续购物 </a>  | 共<span> ${comm_number } </span>种商品</p>
           
            <p class="tro_close_p_c">合计:<span id="close">${sum_price }</span>元</p>
            
            <p class="tro_close_p_r" style="cursor: pointer;" onclick="pay(${sessionScope.user.id},${sum_price})">去结算</p>
        </div>

    </div>


   <jsp:include page="footer.jsp"></jsp:include>

   
</body>
</html>