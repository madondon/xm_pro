<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <meta content="text/html; charset=UTF-8" http-equiv="content-type">
    
    <script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="<%=basePath %>css/index.css">
    <script type="text/javascript">

  //表单验证的四个状态
  var namef = false;
  var phone_numberf = false;
  var usernamef = false;
  var passwordf = false;

  $(function(){
  	
  	//校验名称
  	$("[name='name']").blur(function(){
  		var name = $(this).val();
  		if("" == name)
  			$("#name_span").html("<font style=\"color:red\">姓名不能为空</font>");
  		else{
  			$("#name_span").html("  <font style=\"color:green;font-size:20px\"><b>✔<b></font>");
  			namef = true;
  		}
  	});
  	//电话号码
  	$("[name='phone_number']").blur(function(){
  		var phone_number = $(this).val();
  		if("" == phone_number)
  			$("#phone_number_span").html("<font style=\"color:red\">电话号码不能为空</font>");
  		else
  			//向后台查询当前号码是否存在
  			$.ajax({
  				url:"../UserServlet",
  				data:{"phone_number":phone_number,"mark":"validate_phone"},
  				type:"post",
  				dataType:"json",
  				success:function(bt){
  					if(bt == true){
  						$("#phone_number_span").html("<font style=\"color:green;\"><b>手机号可以注册<b></font>");
  						phone_numberf = true;
  					}else{
  						$("#phone_number_span").html("<font style=\"color:red\">已被注册,<a href=\"login.jsp\">点我登录</a></font>");
  					}
  				}
  			});
  	});
  	
  	//用户名
  	$("[name='username']").blur(function(){
  		var username = $(this).val();
  		if("" == username)
  			$("#username_span").html("<font style=\"color:red\">用户名不能为空</font>");
  		else{
  			$("#username_span").html("  <font style=\"color:green;font-size:20px\"><b>✔<b></font>");
  			usernamef = true;
  		}
  			
  	});
  	
  	//密码
  	$("[name='password']").blur(function(){
  		var password = $(this).val();
  		if("" == password)
  			$("#password_span").html("<font style=\"color:red\">密码不能为空</font>");
  		else{
  			passwordf = true;
  			$("#password_span").html("  <font style=\"color:green;font-size:20px\"><b>✔<b></font>");
  		}
  			
  	});
  	
  	//提交表单时间
  	$("#btn").click(function(){
  		if(namef == true && phone_numberf == true && usernamef == true && passwordf == true){
  			$("#MyForm").submit();
  		}else{
  			alert("请确认输入项是否正确");
  		}
  	});
  });

    
    </script>
</head>
<body>
<body>
<div class="sign_background">
    <div class="sign_background_in">
        <div class="sign_background_no1">
            <a href="index.html"><img src="<%=basePath %>img/logo.jpg" alt=""></a>
        </div>
        <div class="sign_background_no2">注册小米帐号</div>
        <div class="sign_background_no3">
               
            <div class="sign_background_no5">
             	<span style="color: red">${msg }</span>
             	<form id="MyForm" action="<%=basePath %>UserServlet" method="post" enctype="multipart/form-data">
             		<input type="hidden" name="mark" value="registerUser">
             		<table style="width: 500px;" >
             			<tr>
             				<td width="25%" class="_left">姓名：</td>
             				<td><input type="text" name="name"><span id="name_span"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">性别：</td>
             				<td>
             					男<input type="radio" name="sex" checked="checked" value="1">
             				 	女<input type="radio" name="sex" value="0">
							</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">电话号码：</td>
             				<td><input type="text" name="phone_number"><span id="phone_number_span"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">所在地区：</td>
             				<td><input type="text" name="area"></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">账号：</td>
             				<td><input type="text" name="username"><span id="username_span"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">密码：</td>
             				<td><input type="text" name="password"><span id="password_span"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">上传头像：</td>
             				<td><input type="file" name="photo"></td>
             			</tr>
             		</table>
             		<div class="sign_background_no6" id="btn" >立即注册</div>
             	</form>
             	 
            </div>
        </div>
        <div class="sign_background_no7">注册帐号即表示您同意并愿意遵守小米 <span>用户协议</span>和<span>隐私政策</span> </div>
    </div>
    <div class="sign_background_no8"><img src="<%=basePath %>img/sign01.jpg" alt=""></div>

</div>
</body>
</html>