<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//http://localhost:8080/
	String ipath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<html lang="en">
<head>
    <title>${commodity.name }</title>
    <link rel="stylesheet" href="<%=basePath %>css/index.css">
    <script src="<%=basePath %>js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	//添加购物车
	function addTrolley(cid){
		var user = '${sessionScope.user}';
		if(null == user || "" == user){
			alert("请先登录在购买商品");
			return;
		}
		
		$.ajax({
			url:"<%=basePath%>TrolleyServlet",
			data:{"mark":"addCommodityToTrolley","cid":cid},
			type:"post",
			dataType:"json",
			success:function(bt){
				if(bt == true){
					showNumber();
				}else{
					alert("购物车商品添加失败！");
				}
			}
		});
	}
	//查询当前用户购物车数量
	function showNumber(){
		$.ajax({
			url:"<%=basePath%>TrolleyServlet",
			data:{"mark":"showNumber"},
			type:"post",
			dataType:"text",
			success:function(bt){
				$("#tspan").html(bt);
				alert("添加商品成功");
			}
		});
	}
	function pay(uid,price ){
		var user = '${sessionScope.user}';
		if(null == user){
			alert("请重新登陆在支付订单");
			return;
		}
		
		window.location = "<%=basePath %>OrdersServlet?mark=createOrder&uid="+uid+"&price="+price;
	}

</script>
</head>
<body>
    
    <jsp:include page="header.jsp"></jsp:include>
    
    <div class="plus5_no2 ">
        <div class="plus5_no2_in">
            <a class="plus5_no2_in_a"> 为方便您购买，请提前登录 </a>
            <a class="plus5_no2_in_a orange"style="color: pink;" href="login.jsp">立即登录</a>
            <a id="plus5_no2_in" class="plus5_no2_in_a" href="javascript:;">x</a>
        </div>
    </div>
    <div class="plus5_no3">
        <div class="plus5_no3_img">
            <img width="560px" height="560px" src="<%=ipath %>${commodity.pic }" style="display: block" alt="">
        </div>
        <div class="plus5_no3_right">
            <h3>${commodity.name }</h3>
            <p class="plus5_no3_right_p2">
            	<span class="plus5_no3_right_span2">「 ${commodity.description } 」</span>
            	<div style="font-size: 14px;">${commodity.full_description }</div>
            	
            </p>
            <p class="plus5_no3_right_p3">商品价格 ${commodity.price } 元<span class="plus5_no3_right_span3">价格 元</span></p>
            <div class="plus5_no3_right_div4">
                <div class="plus5_no3_right_div4_in">
                    <p class="plus5_no3_right_p4">赠品</p><span>**</span>
                </div>
                <div class="plus5_no3_right_div4_in">
                    <p class="plus5_no3_right_p4">赠品</p><span>**</span>
                </div>


            </div>
            <div class="plus5_no3_right_div5">
                    <p >北京 北京市 东城区 永定门外街道 <span class="plus5_no3_right_div5_span"><a class="plus5_no3_right_div5_span" href="">修改</a> &nbsp;&nbsp;  有现货</span></p>
            </div>
            <div class="plus5_no3_right_div6">选择版本</div>
            <div class="plus5_no3_right_div7">
            
            	<li class="plus5_no3_right_div7_in plus5_no3_right_div7_in_hover">
                    <span class="plus5_no3_right_div7_span0 ">${commodity.version }</span>
                    <span class="plus5_no3_right_div7_span " >商品价格 ${commodity.price }  元</span>
                     <c:set var="price" value="${commodity.price }"></c:set>
                </li>
            	
                <!-- <li class="plus5_no3_right_div7_in plus5_no3_right_div7_in_hover">
                    <span class="plus5_no3_right_div7_span0 ">3GB+32GB</span>
                    <span class="plus5_no3_right_div7_span " >999元</span>
                </li>
                <li class="plus5_no3_right_div7_in plus5_no3_float">
                    <span class="plus5_no3_right_div7_span0 ">4GB+64GB</span>
                    <span class="plus5_no3_right_div7_span " >1299元</span>
                </li> -->
            </div>
            <div class="plus5_no3_right_div6">选择颜色</div>
            <div class="plus5_no3_right_div9">
            	<li class="plus5_no3_right_div7_in plus5_no3_right_div7_in_hover ">
            		<img src="img/plus5_phone_01.png" alt=""><span>${commodity.color }</span>
            	</li>
                <!-- <li class="plus5_no3_right_div7_in plus5_no3_right_div7_in_hover "><img src="img/plus5_phone_01.png" alt=""><span>金色</span></li>
                <li class="plus5_no3_right_div7_in plus5_no3_float"><img src="img/plus5_phone_02.png" alt=""><span>黑色</span></li>
                <li class="plus5_no3_right_div7_in"><img src="img/plus5_phone_03.png" alt=""><span>玫瑰金</span></li>
                <li class="plus5_no3_right_div7_in plus5_no3_float"><img src="img/plus5_phone_04.png" alt=""><span>浅蓝色</span></li> -->
            </div>
            <div class="plus5_no3_right_div10">
                <p class="plus5_no3_right_div10_p1">${commodity.name } <span class="plus5_no3_right_div10_span0">${commodity.version }</span> <span class="plus5_no3_right_div10_span1"></span><span class="plus5_no3_right_div10_span2">${commodity.color }</span></p><span class="plus5_no3_right_span10">${commodity.price }元</span>
                <p class="plus5_no3_right_div10_p2" style="font-size: 18px;font-weight: bold;">总计 ：商品价格 ${commodity.price } 元</p>

            </div>
          
			<!-- 假日购物车/支付 -->
			<div style="float: left;">
				<a href="javascript:void(0)" ><div onclick="addTrolley(${commodity.cid })" class="plus5_no3_right_div11" style="cursor: pointer;width: 150px;height: 50px;border-radius: 5px;float: left;" >加入购物车</div></a>
	            <div class="plus5_no3_right_div11" style="cursor: pointer;width: 150px;height: 50px;border-radius: 5px;float: left;margin-left: 20px" onclick="pay(${sessionScope.user.id},${commodity.price})">立即支付</div>
            </div>
            
            <div style="clear: both;"></div>
            
            <div class="plus5_no3_right_div12">
                <div class="plus5_no3_right_div12_y">√</div>
                <div class="plus5_no3_right_div12_z">七天无理由退货</div>
                <div class="plus5_no3_right_div12_y">√</div>
                <div class="plus5_no3_right_div12_z">15天质量问题换货</div>
                <div class="plus5_no3_right_div12_y">√</div>
                <div class="plus5_no3_right_div12_z">360天保障</div>
            </div>
            <a href="javascript:void (0);"></a>

        </div>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
    <script>
        var plus5_no2_in = document.getElementById("plus5_no2_in");
        var plus5_no2 = document.getElementsByClassName("plus5_no2")[0];
        plus5_no2.onclick = function () {
            plus5_no2.className="plus5_no2 plus5_no2_close";
//            plus5_no2.style.display="none";
        }
        $(".plus5_no3_right_div9 .plus5_no3_right_div7_in").click(function () {
            console.log($(this).children().eq(1).html());
            $(".plus5_no3_right_div10_span2").html($(this).children().eq(1).html());

            $(".plus5_no3_img img").css("display","none").eq($(this).index()).css("display", "block");
            $(this).siblings().removeClass("plus5_no3_right_div7_in_hover").end().addClass("plus5_no3_right_div7_in_hover");

        })
        $(".plus5_no3_right_div7 .plus5_no3_right_div7_in").click(function () {
//            var value = $(".plus5_no3_right_div7_span0 ").html();
            console.log($(this).children().eq(1).html());
            $(".plus5_no3_right_div10_span0").html($(this).children().html());
            $(".plus5_no3_right_span10").html($(this).children().eq(1).html());
            var value = "总计:"+$(this).children().eq(1).html()
            $(".plus5_no3_right_div10_p2").html(value);

            $(this).siblings().removeClass("plus5_no3_right_div7_in_hover").end().addClass("plus5_no3_right_div7_in_hover");
        })
    </script>

</body>
</html>