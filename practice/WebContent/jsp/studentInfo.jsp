<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生个人信息维护</title>
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
</head>
<style>

</style>
<body>
	<%@include file="Title.jsp"%>
	<div class="right group" id="show">
		<dl>
		
			
			<dd>
				<h3>学生个人信息维护</h3>
				<form action="/practice/UpdateStudentServlet"  method="post">
					<table class="table">
						  <tr>
						    <td width="25%">学号：</td>
						    <td><input type="text" disabled="" value="${student.no }"></td>
						  </tr>
						  <tr>
						    <td>姓名：</td>
						    <td><input type="text" disabled=""  value="${student.name }"></td>
						  </tr>
						  <tr>
						    <td>性别：</td>
						    <td><input type="text" disabled="" value="${student.gender }"></td>
						  </tr>
						  <tr>
						    <td>入学年份：</td>
						    <td><input type="text" disabled="" value="${student.grade }"></td>
						  </tr>
						  <tr>
						    <td>层次：</td>
						    <td><input type="text" disabled="" value="${student.level }"></td>
						  </tr>
						   <tr>
						    <td>专业名称：</td>
						    <td><input type="text" disabled="" value="${student.professional }"></td>
						  </tr>
						  <tr>
						    <td>学科背景：</td>
						    <td><textarea required name="background">${student.subjectBackground }</textarea></td>
						  </tr>
						   <tr>
						    <td>学科经历：</td>
						    <td><textarea required name="experience">${student.learningExperience }</textarea></td>
						  </tr>
						   <tr>
						    <td>研究方向：</td>
						    <td><textarea required name="direction">${student.researchDirection }</textarea></td>
						  </tr>
						  <tr>
						    <td><input type="reset" value="重置" class="btn btn-danger"></td>
						    <td><input type="submit" value="确认修改" class="btn btn-success"></td>
						  </tr>
				     </table>
				</form>
				<hr>
				<form action="/practice/UpdateStudentServlet" method="post">
					输入原密码<input type="password" name="oldpwd"><br>
					新密码<input type="password"  name="newpwd"><br>
					<input type="submit" value="确认修改" class="btn btn-danger"><br>
				</form>
			</dd>
			
			
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>