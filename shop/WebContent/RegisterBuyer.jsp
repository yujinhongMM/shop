<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
	<script src="js/common.js"></script>
	<!--背景图片自动更换-->
	<script src="js/supersized.3.2.7.min.js"></script>
	<script src="js/supersized-init.js"></script>
	<!--表单验证-->
	<script src="js/jquery.validate.min.js?var1.14.0"></script>
	<title>买家页面</title>
</head>
<body>
	 
	   <div class="register-container">
			<h1>买家注册</h1>
	
	  <div class="connect">
		 <p>欢迎注册手机商城</p>
	  </div>  
	  
	 <form action="/shop/RegisterBuyerServlet" method="POST">
		<div>
			<input type="hidden" name="role" value="1"/>
			<input type="text" name="username" required id="username" value="${phone}" placeholder="输入手机号码" autocomplete="off" id="number" class="phone_number">
			
		</div>		
		<div>
		<input type="text" name="verify" required id="phoneNumber" placeholder="验证码">
 	    <a href="/shop/SendMsServlet" style="width:100%;" onclick="getMs(this)" >获取验证码</a> 
	
		</div>
		
		<div>
		    <input type="password" id="password" name="password" required class="password"  placeholder="输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="password" id="repassword" name="repassword" required class="confirm_password" placeholder="再次输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="text" name="nickname" required class="nickname" placeholder="昵称" autocomplete="off"/>		
		</div>
		<div>
		    <input type="text" name="email" required class="email" placeholder="输入邮箱地址" oncontextmenu="return false" onpaste="return false" />   
		</div>
		<div>
			<input type="text" name="birthday" required class="birthday" placeholder="生日"  autocomplete="off">
		</div>
		<div>
			<input type="text" name="address" required class="address" placeholder="地址" autocomplete="off"/>
		</div>
		  <button type="submit" id="submit" onclick="zhuce(this.form)">注册</button>	        
	</form>
	<div>
	<a href="login.jsp">
		<button type="button" class="register-tis">已经有账号？去登录</button>
	</a>
	${msg}
</div>
</div>
	  
	  
	  
	  
	     
	
</body>
<script type="text/javascript"> 
function getMs(othis){
    var username=document.getElementById("username");
    othis.href="/shop/SendMsServlet?type=1&username="+ username.value;
}
function zhuce(oForm){
	  var password1=document.getElementById("password");
	  var password2=document.getElementById("repassword");
	  console.log(password2.value)
	  if(password1.value==password2.value&&password1.value!=""){
		  oForm.submit();
	  }else{
		  alert("两次密码不一样！");
		  return;
	  }
}
</script>
</html>