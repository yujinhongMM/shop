<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<jsp:include page="../head.txt"/>
</head>
<body>
	
	<form action="/practice/LoginServlet" method="post" style="width:50%;margin:0 auto;text-align: center;">
	<hr>
			用户名：<input name="account" type="text" required="required"><br>
		<hr>
			密码：<input name="password" type="password" required="required"><br>
			 <!--用户角色-->
           <hr>
                                      用户角色 :
                                      
            <input type="radio" name="role" value="1" checked>企业  
			<input type="radio" name="role" value="2">学生 
			<input type="radio" name="role" value="9">管理员<br>  
           
           <hr>
			验证码：<input name="verificationCode" type="text" required="required">
			<img src="/practice/VerifyCodeServlet" id="verify" border="0">
			<a href="javascript:change()" class="btn btn-default">点击刷新</a><br>
			<hr>
			<input type="submit" value=" 登     录  " class="btn btn-success">
			<a href="/practice/com.jsp" class="btn btn-default">找回密码</a>
			<br>
			企业还没有账户，点击<a href="/practice/zhuce.jsp" class="btn btn-default">注册</a>
			<div style="color:red;font-size:28px;"> ${msg}</div>  
	</form>
	<script type="text/javascript">  
      function change(){  
          var img =document.getElementById("verify");
          img.src="/practice/VerifyCodeServlet?a="+new Date().getTime();  ////缓存问题，例如ie不能换，google能换；ie自作聪明，不就是图片，路径一样，我上次保存了，直接给你
        //加一个永远不重复的参数，Servlet同一个但参数不同他就不敢自作主张，必须问服务器
      }  
    </script>  
</body>
</html>