<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业管理学生</title>
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
</head>
<style>
table tr th,table tr td { border:1px solid black; }
</style>
<body>



	<%@include file="Title.jsp"%>
	
	
	
	<div class="right">
		<dl>
		
		
			<dd>
			
				
				
				

			
					<h2 style="text-align: center;">企业管理学生</h2>
					
					<table cellspacing="0" width="100%">
						<thead>
							<tr>
								<th colspan="2">方案号</th>
								<th>方案名称</th>
								<th colspan="2">学生学号</th>
								<th>学生姓名</th>
								<th>学生年级</th>
								<th>校外指导老师</th>
								<th>校外指导老师职称</th>
								<th>方案类别</th>
								<th>年级下限</th>
								<th colspan="2">发布日期</th>
								
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${proProSelStuViews }" var="proProSelStuView">
								<tr>
									<td colspan="2">${proProSelStuView.project.no }</td>
									<td>${proProSelStuView.project.name }</td>
									<td colspan="2">${proProSelStuView.student.no }</td>
									<td>${proProSelStuView.student.name }</td>
									<td>${proProSelStuView.student.grade }</td>
									<td>${proProSelStuView.project.companyTeacher }</td>
									<td>${proProSelStuView.project.companyTeacherTitle }</td>
									<td>${proProSelStuView.project.category }</td>
									<td>${proProSelStuView.project.grade }</td>
									<td colspan="2">${proProSelStuView.project.releaseDate }</td>
									
									<td><c:if test="${proProSelStuView.projectSelect.companySelDate==null&&stuHasProject.get(proProSelStuView.student.no) }">
											<a href="/practice/ChoiceStudentServlet?type=1&stu_no=${proProSelStuView.student.no }&p_no=${proProSelStuView.project.no }" class="btn btn-success">
												选择 </a>
										</c:if> <c:if test="${proProSelStuView.projectSelect.companySelDate!=null }">
											<a 
												href="/practice/ChoiceStudentServlet?type=2&stu_no=${proProSelStuView.student.no }&p_no=${proProSelStuView.project.no }" class="btn btn-warning">
												退选 </a>
										</c:if></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>
								<!-- 分页 -->
								第 ${choiceProjectInfoPageUtils.pageNow } /
								${choiceProjectInfoPageUtils.totalPage }页
								<c:if test="${choiceProjectInfoPageUtils.isHasFirst() }">
									<a href="ChoicePracticeInfoServlet?nowPage=1">首页&nbsp;</a>
								</c:if>
								<c:if test="${choiceProjectInfoPageUtils.isHasPre() }">
									<a
										href="ChoicePracticeInfoServlet?nowPage=${choiceProjectInfoPageUtils.pageNow-1 }">上一页&nbsp;</a>
								</c:if>
								<c:if test="${choiceProjectInfoPageUtils.isHasNext() }">
									<a
										href="ChoicePracticeInfoServlet?nowPage=${choiceProjectInfoPageUtils.pageNow+1 }">下一页&nbsp;</a>
								</c:if>
								<c:if test="${choiceProjectInfoPageUtils.isHasLast() }">
									<a
										href="ChoicePracticeInfoServlet?nowPage=${choiceProjectInfoPageUtils.totalPage }">尾页</a>
								</c:if>
			</dd>
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>