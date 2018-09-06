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
	
	<div class="right group">
		<dl>
		
		
			<dd>
				<h2>修改实训方案</h2>
						<form action="UpdatePracticeServlet" method="post">
								
													<label>企业用户名</label>
												
												<input type="text" disabled="" value="${company.companyName }"><br><br>
										
									
													<label>方案号</label>
											
												<input type="text" value="${updateProjectNo }"><br><br> <input type="hidden" value="${updateProjectNo }" name="no">
											

										
													<label>方案名称</label>
												
												<input type="text" value="${updateProjectInfo.name }" name="name"><br><br>
											
										
											<label>方案简介</label>
											<textarea required name="introduction">${updateProjectInfo.introduction }</textarea><br><br>
										
											<label>适合专业</label>
											<%
												ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
												ArrayList<String> professionals = projectServiceImpl.findAllProfessional();
											%>
											<c:forEach items="<%=professionals%>" var="professional">
												<label>
													<c:if test="${majorInfo.get(professional)==1 }">
														<input type="checkbox" value="${professional }" name="major"
															checked="checked">
													</c:if> <c:if test="${majorInfo.get(professional)!=1 }">
														<input type="checkbox" value="${professional }" name="major">
													</c:if> <span>${professional }</span>
												</label>
											</c:forEach><br><br>

											
										
													<label>学生人数</label>
												
												<input type="text" value="${updateProjectInfo.studentsNum }" name="students_num"><br><br>
											
													<label>类别</label>
											
												<select name="category">
													<c:if
														test="${updateProjectInfo.category.equals(\"技能实训\") }">
														<option value="技能实训" selected="selected">技能实训</option>
														<option value="概念实训">概念实训</option>
														<option value="综合实训">综合实训</option>
													</c:if>
													<c:if
														test="${updateProjectInfo.category.equals(\"概念实训\") }">
														<option value="技能实训">技能实训</option>
														<option value="概念实训" selected="selected">概念实训</option>
														<option value="综合实训">综合实训</option>
													</c:if>
													<c:if
														test="${updateProjectInfo.category.equals(\"综合实训\") }">
														<option value="技能实训">技能实训</option>
														<option value="概念实训">概念实训</option>
														<option value="综合实训" selected="selected">综合实训</option>
													</c:if>
												</select><br><br>
											
										
													<label>年级</label>
											
												<select name="grade">
													<c:if test="${gradeFlag==1}">
														<option value="1" selected="selected">大一</option>
														<option value="2">大二</option>
														<option value="3">大三</option>
														<option value="4">大四</option>
													</c:if>

													<c:if test="${gradeFlag==2}">
														<option value="1">大一</option>
														<option value="2" selected="selected">大二</option>
														<option value="3">大三</option>
														<option value="4">大四</option>
													</c:if>

													<c:if test="${gradeFlag==3}">
														<option value="1">大一</option>
														<option value="2">大二</option>
														<option value="3" selected="selected">大三</option>
														<option value="4">大四</option>
													</c:if>

													<c:if test="${gradeFlag==4}">
														<option value="1">大一</option>
														<option value="2">大二</option>
														<option value="3">大三</option>
														<option value="4" selected="selected">大四</option>
													</c:if>

												</select><br><br>
											
													<label>校外指导老师</label>
												
												<input type="text" value="${updateProjectInfo.companyTeacher }" name="company_teacher"><br><br>
										
													<label>校外指导老师 职称</label>
												
												<input type="text" value="${updateProjectInfo.companyTeacherTitle }" name="company_teacher_title"><br><br>
											
											<input type="submit" value="确定" class="btn btn-danger">
											<a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
										

							</form>
							
				
			</dd>
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>