<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生选择方案</title>
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
				<h3>学生选择方案</h3>
				<table class="table table-bordered">
						<thead>
							<tr>
								<th  colspan="2">方案号</th>
								<th>方案名称</th>
								<th>方案简介</th>
								<th>学生人数</th>
								<th>校外指导老师</th>
								<th>校外指导老师职称</th>
								<th>类别</th>
								<th>年级</th>
								<th>发布日期</th>
								<th>企业名称</th>
								<th>审核状态</th>
								<th>选择状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${selectProjects }" var="selectProject">
								<tr>
									<td  colspan="2">${selectProject.no }</td>
									<td>${selectProject.name }</td>
									<td title="${selectProject.introduction }"><c:if
											test="${selectProject.introduction.length()>30 }">
											${selectProject.introduction.substring(0,30) }...
											</c:if> <c:if test="${selectProject.introduction.length()<=30 }">
											${selectProject.introduction }</c:if></td>
									<td>${selectProject.studentsNum }</td>
									<td>${selectProject.companyTeacher }</td>
									<td>${selectProject.companyTeacherTitle }</td>
									<td>${selectProject.category }</td>
									<td>${selectProject.grade }</td>
									<td>${selectProject.releaseDate }</td>
									<!--  <td>${selectProject.companyUsername }</td>-->
									<td>${companyInfo[selectProject.no].companyName }</td>
									<td><c:if test="${stuProjectNo.equals(selectProject.no) }">
											已审核
										</c:if> <c:if
											test="${!stuProjectNo.equals(selectProject.no)&&choiceState[selectProject.no]==1 }">
											未审核
										</c:if> <c:if
											test="${choiceState[selectProject.no]==0||choiceState[selectProject.no]==null }">
											-
										</c:if></td>
									<td><c:if test="${choiceState[selectProject.no]==1 }">
											已选
										</c:if> <c:if
											test="${choiceState[selectProject.no]==0||choiceState[selectProject.no]==null }">
											未选
										</c:if></td>
									<td><a href="InfoPracticeServlet?no=${selectProject.no }" class="btn btn-info"> 详情</a> 
									<c:if test="${PracticeIsUnderWay }">
											<c:if test="${choiceState[selectProject.no]==1 }">
												<a href="/practice/StudentChoicePracticeServlet?no=${selectProject.no }" class="btn btn-danger">退选 </a>
											</c:if>
											<c:if
												test="${choiceState[selectProject.no]==0||choiceState[selectProject.no]==null }">
												<form action="/practice/StudentChoicePracticeServlet" method="post">
							
														<input type="hidden" readonly="" value="${selectProject.no }" name="no">
														
														<input type="hidden" readonly="" value="${selectProject.name }" name="name">
														
															选题理由:<textarea required name="reason" style="width:100%;"></textarea>
															
														<input type="submit" value="选择" class="btn btn-success">
													
												</form>
												
											</c:if>
										</c:if>
									</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
			</dd>
			
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>