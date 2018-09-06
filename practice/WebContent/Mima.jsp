<%@page import="com.yjh.practice.dao.impl.CompanyDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page import="com.yjh.practice.service.impl.CompanyServiceImpl,com.yjh.practice.model.Company"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>


<jsp:include page="head.txt"/>
</head>
<body style="text-align: center;">
<div style="text-align: left;width:50%;margin:0 auto;">

	<% 
		String username=request.getParameter("username");
		System.out.println(username);
		CompanyServiceImpl dao=new CompanyServiceImpl();
		Company company=dao.queryByUserName(username);
		session.setAttribute("company", company);
		System.out.println(company);
	%>
	 <form action="${pageContext.request.contextPath }/UpdateCompanyInfo?type=1" method="post">
		<input type="hidden" name="oldpwd" value="${company.getPassword()}"><br>
		新密码<input type="password" name="newpwd" required><br>
		<input type="submit" value="确认修改" class="btn btn-success"><br>
	</form>
	<a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
</div>
</body>
</html>