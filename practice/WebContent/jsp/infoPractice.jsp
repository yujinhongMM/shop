<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实训主页</title>
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
</head>
<style>
table tr th,table tr td { border:1px solid black; }
</style>
<body>


	<%@include file="Title.jsp"%>
	
	
	<div class="right group" id="show">
		<dl>
		
		
			<dd>
			
				
				<h2>实训方案详细信息</h2>
				<table cellspacing="0" width="100%" class="table-striped">
					<tr>
						<td><label>企业用户名</label></td><td>${infoProject.companyUsername }infoCompany</td>
					</tr>
					<tr>
						<td><label>企业名称</label></td><td>${infoCompany.companyName }</td>
					</tr>
					<tr>
						<td><label>方案号</label></td><td><label>${infoProject.no }</label></td>
					</tr>
					<tr>
						<td><label>方案名称</label></td><td><label>${infoProject.name }</label></td>
					</tr>
					<tr>
						<td><label>方案简介</label></td><td>${infoProject.introduction }</td>
					</tr>
					<tr>
						<td><label>适合专业</label></td><td>${infoProject.major }</td>
					</tr>
					<tr>
						<td><label>学生人数</label></td><td>${infoProject.studentsNum }</td>
					</tr>
					<tr>
						<td><label>类别</label></td><td>${infoProject.category }</td>
					</tr>
					<tr>
						<td><label>年级</label></td><td>${infoProject.grade }</td>
					</tr>
					<tr>
						<td><label>校外指导老师</label></td><td>${infoProject.companyTeacher }</td>
					</tr>
					<tr>
						<td><label>校外指导老师 职称</label></td><td>${infoProject.companyTeacherTitle }</td>
					</tr>
					
				</table>
				<c:if test="${InfoRole==1&&infoProject.auditDate==null }">
					<a 
						href="/practice/UpdatePracticeServlet?no=${infoProject.no }" class="btn btn-warning"> 修改</a>
					<a
						href="/practice/DeletePracticeServlet?no=${infoProject.no }" class="btn btn-danger"> 删除</a>
				</c:if>

				<a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
										
		
			</dd>
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>