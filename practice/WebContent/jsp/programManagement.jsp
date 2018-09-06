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
</style>
<body>


	<%@include file="Title.jsp"%>
	
	
	<div class="right group" id="show">
		<dl>
		
		
			<dd>
			
				
				
				

			
					<h2 style="text-align: center;">实训方案管理</h2>
					<c:if test="${ role=='1'}">
						<a href="/practice/jsp/addPractice.jsp" class="btn btn-success">添加实训方案</a>
					</c:if>
					
					
					<section>
						<div>

							<table class="table">
								<thead>
									<tr>
										<th colspan="2">方案号</th>
										<th colspan="2">方案名称</th>
										<th>学生人数</th>
										<th>校外指导老师</th>
										<th>校外指导老师职称</th>
										<th>类别</th>
										<th>年级</th>
										<th colspan="2">发布日期</th>
										<th>审核状态</th>
										<th>企业名称</th>
										<th>方案简介</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${selectProjects }" var="selectProject">
										<tr>
											<td colspan="2">${selectProject.no }</td>
											<td colspan="2">${selectProject.name }</td>
											<td>${selectProject.studentsNum }</td>
											<td>${selectProject.companyTeacher }</td>
											<td>${selectProject.companyTeacherTitle }</td>
											<td>${selectProject.category }</td>
											<td>${selectProject.grade }</td>
											<td colspan="2">${selectProject.releaseDate }</td>
											<td>${selectProject.auditDate!=null?"已审核":"未审核" }</td>
											<!-- <td>${selectProject.companyUsername }</td> -->
											<td>${companyInfo[selectProject.no].companyName }</td>
											<td title="${selectProject.introduction }"><c:if
													test="${selectProject.introduction.length()>30 }">
											${selectProject.introduction.substring(0,10) }...
											</c:if> <c:if test="${selectProject.introduction.length()<=30 }">
											${selectProject.introduction }</c:if></td>
											<td><a href="InfoPracticeServlet?no=${selectProject.no }" class="btn btn-info"> 详情</a> 
											</td>
											<td>
												<c:if test="${selectProject.endDate==null }">
													<c:if
														test="${selectProjectsRole==9&&selectProject.auditDate!=null }">

														<a href="CheckPracticeServlet?no=${selectProject.no }&type=2"  class="btn btn-warning">退审</a>
													</c:if>
													<c:if test="${selectProjectsRole==9&&selectProject.auditDate==null }">
														<a href="CheckPracticeServlet?no=${selectProject.no }&type=1" class="btn btn-success">审核</a>
													</c:if>
												</c:if> 
											</td>
											</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- 分页 -->
							第 ${selectProjectPageUtils.pageNow } /
							${selectProjectPageUtils.totalPage }页
							<c:if test="${selectProjectPageUtils.isHasFirst() }">
								<a href="SelectPracticeServlet?nowPage=1">首页&nbsp;</a>
							</c:if>
							<c:if test="${selectProjectPageUtils.isHasPre() }">
								<a
									href="SelectPracticeServlet?nowPage=${selectProjectPageUtils.pageNow-1 }">上一页&nbsp;</a>
							</c:if>
							<c:if test="${selectProjectPageUtils.isHasNext() }">
								<a
									href="SelectPracticeServlet?nowPage=${selectProjectPageUtils.pageNow+1 }">下一页&nbsp;</a>
							</c:if>
							<c:if test="${selectProjectPageUtils.isHasLast() }">
								<a
									href="SelectPracticeServlet?nowPage=${selectProjectPageUtils.totalPage }">尾页</a>
							</c:if>
						</div>
					</section>
				
				<!-- responsive table example end -->

		
			</dd>
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>