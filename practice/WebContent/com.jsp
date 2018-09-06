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
<div style="text-align: center;width:50%;margin:0 auto;">
	<hr>
	<form action="Mima.jsp" method="post">
		输入企业的账户：<input type="text" name="username" required><br>
		<input type="submit" value="确认" class="btn btn-success">
		<a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
	</form>
	
	
</div>
</body>
</html>