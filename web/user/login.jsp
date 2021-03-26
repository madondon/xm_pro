<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="<%=basePath %>css/index.css">
    <script src="<%=basePath %>js/jquery.1.11.1.min.js"></script>
    
    <style>

    </style>
<script type="text/javascript">

$(function(){
	//绑定获取验证码的点击事件
	$("#zphone").click(function(){
		//默认清空提示信息
		$("#msg").html("");
		
		var phone_number = $("#phone_number").val();
		if("" == phone_number){
			$("#msg").html("手机号码不能为空！");
			return;
		}
		
		//进行后台验证
		$.ajax({
			url:"<%=basePath %>UserServlet",
			data:{"mark":"sendPhoneCode","phone_number":phone_number},
			type:"post",
			dataType:"text",
			success:function(bt){
				//0、号码未注册 1、验证码发送失败 2、发送成功
				if("2" == bt){
					//进入读秒
					$("#msg").html("<font style=\"color:green\">验证码发送成功</font>");
					RemainTime();
				}else if("1" ==bt){
					$("#msg").html("验证码发送失败，请检查手机号码是否正确");
				}else{
					$("#msg").html("手机号码未注册 <a href=\"register.jsp\">点我注册</a>");
				}
			}
		});
	});
	
	//在脚本中提交表单
	$("#sub").click(function(){
		$("#f3").submit();
	});
	
	
	
});


//读秒的方法
var iTime = 59;
var Account;
function RemainTime(){
	document.getElementById('zphone').disabled = true;
	var iSecond,sSecond="",sTime="";
	if (iTime >= 0){
		iSecond = parseInt(iTime%60);
		iMinute = parseInt(iTime/60)
		if (iSecond >= 0){
			if(iMinute>0){
				sSecond = iMinute + "分" + iSecond + "秒";
			}else{
				sSecond = iSecond + "秒";
			}
		}
		sTime=sSecond;
		if(iTime==0){
			clearTimeout(Account);
			sTime='获取手机验证码';
			iTime = 59;
			document.getElementById('zphone').disabled = false;
		}else{
			Account = setTimeout("RemainTime()",1000);
			iTime=iTime-1;
		}
	}else{
		sTime='没有倒计时';
	}
	document.getElementById('zphone').value = sTime;
}
</script>
</head>
<body>
<div class="register_head_on">

</div>
<div class="register_head">
    <a href="index.html"><img src="<%=basePath %>img/logo.jpg" alt=""></a>
    <div class="register_head_right">
        <p class="register_head_right_p1">小 米 商 城</p>
        <p class="register_head_right_p2">让每个人都享受科技乐趣</p>
    </div>

</div>

<div class="register">
    <div class="register_boby">
        <div class="register_boby_min">
            <div class="register_boby_no1">
                <div class="register_boby_no1_in">
                    <span style="color: #ff6700">手机验证码登录 </span>
                </div>
            </div>
            <form id="f3" action="<%=basePath %>UserServlet" method="post">
            <!-- 隐藏mark -->
            <input name="mark" value="checkLogin" type="hidden">
            <div class="register_boby_no2">
            	<span id="msg" style="color: red;font-size: 12px;margin-left: 35px;">${msg }</span><br>
                <input id="phone_number" style="margin-left: 32px;" name="phone_number" type="text" placeholder="手机号码">
                
                <input name="code"  type="password" placeholder="手机校验码" style="width: 200px; margin-left: 32px;float: left;">
                <!-- 新增加 -->
                <input id="zphone" type="button" value=" 获取手机验证码 " style="width: 138px;float: left;height: 53px;margin-left: 5px;"  > 
                <div style="clear: both;">
                
                <div class="register_boby_no2_div" style="width: 350px">
                    <span id="sub">登录</span>
                </div>
            </div>
            </div>
            </form>
            
            <div class="register_boby_no3">
                <a href="javascript:void(0);" style="color: #ff6700">账号密码登录</a>
                <sapn class="register_boby_no3_span">
                    <a href="register.jsp">立即注册</a>
                    <span>|</span>
                    <a href="avascript:void(0);">忘记密码?</a>
                </sapn>

            </div>
            <div class="register_boby_no4">
                <img src="<%=basePath %>img/register02.jpg" alt="">
            </div>

        </div>
    </div>
</div>
<div class="register_foot">
    <img  src="<%=basePath %>img/register03.jpg" alt="">
</div>


</body>
</html>