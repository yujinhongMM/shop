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
    <title>登录</title>
</head>
<body>
	<% 
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			if(cookies[i].getName().equals("user")){
				String username=cookies[i].getValue().split("#")[0];
				String password=cookies[i].getValue().split("#")[1];
				String checked="checked";
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.setAttribute("checked", checked);
			}
		}
	%>
	 
	    	
	        <div class="login-container">
			<h1>登录</h1>
	
			<div class="connect">
				<p>欢迎登录手机商城</p>
			</div>
	        <form method="POST" action="/shop/LoginServlet">      
	        <div>  
			<input type="text" name="username" placeholder="用户名" required value="${username}"/>
			</div>
			<div>
			<input type="password" name="password" placeholder="密码" required value="${password}"/>	
			</div>		                                                       
			<div class="yzm">	
			<input name="verifycode" type="text" required placeholder="验证码">			
			<img src="/shop/VerifyCodeServlet" id="verify" border="0" onclick="change()" style="cursor:pointer;">
			</div>
			<div  class="yonghu">		        
			<input type="radio" name="role" value="1" checked>买家
			<input type="radio" name="role" value="2">卖家
			<input type="radio" name="role" value="9">管理员
			</div>	
			<div  class="remenber">
	        <input type="checkbox" name="remember" ${checked}>记住密码
	        </div>       
	        <button type="submit" id="submit">登录</button>	        
	        <h3>${msg}</h3>	        
	        </form>
	        <div>
	        <a href="RegisterBuyer.jsp">
	        <button type="button" class="register-tis">我要注册买家</button>
	        </a></div>
	        <div>
	        <a href="RegisterSeller.jsp">
	        <button type="button" class="register-tis">我要注册卖家</button>
	        </a></div>
	        
	</div>
	
	        
	
	
	
</body>
<script type="text/javascript">  
      function change(){  
          var img =document.getElementById("verify");
          img.src="/shop/VerifyCodeServlet?a="+new Date().getTime();  ////缓存问题，例如ie不能换，google能换；ie自作聪明，不就是图片，路径一样，我上次保存了，直接给你
        //加一个永远不重复的参数，Servlet同一个但参数不同他就不敢自作主张，必须问服务器
      }  
    </script>  
</html>