<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/practice/js/jquery-3.2.1.min.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<div class="top"><h1>成都大学信息科学与工程学院实训系统</h1></div>
	<div class="left group" id="list">
		
		<c:if  test="${role eq  '1' }">
			你好,<span style="color:red;"><c:out value="${company.getCompanyName()}"></c:out></span>
			
			<a href="/practice/jsp/index.jsp">主页</a>
			<a href="/practice/SelectPracticeServlet">方案管理</a>
			<a href="/practice/ChoicePracticeInfoServlet">企业管理学生</a>
			<a href="/practice/ShowCompanyServlet">企业信息维护</a>
			<a href="/practice/ShowNoticeListServlet">发布通知公告</a>
			<a href="/practice/SignOutServlet">注销登录</a>
			
		</c:if>
		
		<c:if test="${role eq  '2'}">
			你好,<span style="color:red;"><c:out value="${student.getName()}"></c:out></span>
			
			<a href="/practice/jsp/index.jsp">主页</a>
			<a href="/practice/StudentSelectPracticeServlet">
				
					学生选择方案
				
			</a>
			<a href="/practice/UpdateStudentServlet">
				
					学生个人信息维护
				
			</a>
			<a href="/practice/SignOutServlet">注销登录</a>
			
		</c:if>
		
		<c:if test="${role eq  '9' }">
			你好,admin
			
				<a href="/practice/jsp/index.jsp">主页</a>
				<a href="/practice/SelectPracticeServlet">方案管理</a>
				<a href="/practice/ShowAdminCompanyServlet">企业信息管理</a>
				<a href="/practice/QueryStudentServlet">学生管理</a>
				<a href="/practice/ShowAdminNoticesServlet">学院通知</a>
				<a href="/practice/AdminLookNoticesServlet">审核通知</a>
				<a href="/practice/SelectSystemConfigServlet">系统参数配置</a>
				<a href="/practice/SignOutServlet">注销登录</a>
			
		</c:if>
		
	</div>
	