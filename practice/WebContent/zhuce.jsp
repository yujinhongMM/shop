<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>

<jsp:include page="head.txt"/>

</head>
<body style="text-align: center;">
<div style="width:50%;margin:0 auto;text-align: center;">
	
	 <h2> <span> <strong>实训企业注册</strong></span></h2>
               
                <form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath }/RegistCompanyServlet">
                   
                           <hr>
                           
                       
                            <label>企业名称</label>
                           
                                <input type="text" name="qyname" required> <hr>
                       
                            <label>企业帐号</label>
                           
                                <input type="text" name="qyusername" required> <hr>
                           
                       
                            <label>用户密码</label>
                          
                                <input type="password" name="password" id="password1" required> <hr>
                            

                       
                            <label>再次输入密码</label>                   
                                <input type="password" name="confirmPassword" id="password2" required> <hr>
                          
                          
                           <label>密保邮箱</label>
                              
                                 <input type="text" name="email" required> <hr>
                            	验证码：<input name="yzm" type="text" required="required">
								<img src="VerifyCodeServlet" id="verify" border="0">
								<a href="javascript:change()" class="btn btn-default">点击刷新</a><br>
								<hr>
                   
                        	<input  type="button" value="注      册" onclick="zhuce(this.form)" class="btn btn-success btn-lg"> <hr>
                        
								企业已有账户，点击<a href="jsp/login.jsp" class="btn btn-default">登录</a>
                </form>
           
</div>
</body>
<script type="text/javascript">  
      function change(){  
          var img =document.getElementById("verify");
          img.src="VerifyCodeServlet?a="+new Date().getTime();  ////缓存问题，例如ie不能换，google能换；ie自作聪明，不就是图片，路径一样，我上次保存了，直接给你
        //加一个永远不重复的参数，Servlet同一个但参数不同他就不敢自作主张，必须问服务器
      }  
      function zhuce(oForm){
    	  var password1=document.getElementById("password1");
    	  var password2=document.getElementById("password2");
    	  if(password1.value==password2.value&&password1.value!=""){
    		  oForm.submit();
    	  }else{
    		  alert("两次密码不一样！");
    		  return;
    	  }
      }
      
    </script>  
</html>