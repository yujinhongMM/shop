<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page import="com.yjh.practice.service.impl.ProjectServiceImpl"%>
<%@page import="java.util.ArrayList,java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实训主页</title>
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
</head>
<style>

</style>
<body>
	<%@include file="Title.jsp"%>
	
	<div class="right group" id="show">
		<dl>
		
		
			<dd>
				<h2>添加实训方案</h2>
				<form action="/practice/AddPracticeServlet" method="post">
					
					<label>企业用户名</label>
						
					<input type="text" disabled="" value="${account }"><br><br>
						
					
					<label>方案名称</label>
							
					<input type="text" value="" required="required" name="name"><br><br>
						
					<label>方案简介</label>
					<textarea required="required" name="introduction" ></textarea><br><br>
			
					<label>适合专业</label>
					<% ProjectServiceImpl projectServiceImpl=new ProjectServiceImpl();
					ArrayList<String> professionals=projectServiceImpl.findAllProfessional();%>
					<c:forEach items="<%=professionals %>" var="professional">
						<label>
							<input type="checkbox" value="${professional}" name="major">
							<span>${professional} </span>
						</label>
					</c:forEach><br><br>
				
				
							<label>学生人数</label>
							<input type="text" value="" name="students_num" required="required"><br><br>
			
				
							<label>类别</label>
						
						<select name="category">
							<option value="技能实训">技能实训</option>
							<option value="概念实训">概念实训</option>
							<option value="综合实训">综合实训</option>
						</select><br>
	
						<label>年级</label>
						
						<select name="grade">
							<option value="1">大一</option>
							<option value="2">大二</option>
							<option value="3">大三</option>
							<option value="4">大四</option>
						</select>
						<label>提示：此选项为目标年级、高年级可选低年级方案。</label><br><br>
				
						<label>校外指导老师</label>
						
						<input type="text" value="" name="company_teacher"><br><br>
						<label>校外指导老师 职称</label>
						
						<input type="text" value="" name="company_teacher_title"><br><br>
						<input type="submit" value="确定" class="btn btn-success"><a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
				</form>		
			</dd>
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>