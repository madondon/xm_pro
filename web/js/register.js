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