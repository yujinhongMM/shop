<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
<title>实训主页</title>
</head>
<style>
table tr th,table tr td { border:1px solid black; }
table tr td { border:1px solid black; max-width:120px;height:auto; font-size:10px;}
</style>
<body>
	<%@include file="Title.jsp"%>
	
	<div class="right group">
		<dl>
		
		
			<dd>
				
                    <h2>学生管理</h2>
                   	 <table width="100%"  cellspacing="0" class="table-striped">
                   
                       <tr>
                           <td>学号</td>
                           <td>姓名</td>
                           <td>性别</td>
                           <td>入学年份</td>
                           <td>层次</td>
                           <td>专业名称</td>
                           <td>班级</td>
                           <td>研究方向</td>
                           <td>学习经历</td>
                           <td>学科背景</td>
                           <td>邮箱</td>
                       </tr>
                
                       <c:forEach items="${student }" var="stu">
                       		<tr>
                       			<td>${stu.no }</td>
                       			<td>${stu.name }</td>
                       			<td>${stu.gender }</td>
                       			<td>${stu.grade }</td>
                       			<td>${stu.level }</td>
                       			<td>${stu.professional }</td>
                       			<td>${stu.class_ }</td>
                       			<td>${stu.researchDirection }</td>
                       			<td>${stu.learningExperience }</td>
                       			<td>${stu.subjectBackground }</td>
                       			<td>${stu.mailbox }</td>
                       		</tr>
                       </c:forEach>
               
               </table>
                    
               
			</dd>
			
			
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  
      
</script>  
</html>