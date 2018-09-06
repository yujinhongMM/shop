<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
<title>企业信息管理页面</title>
</head>
<style>
table tr th,table tr td { border:1px solid black;}
</style>
<body>
	<%@include file="Title.jsp"%>
	
	<div class="right">
		<dl>
		
		
			<dd>
				
                    <h2>企业信息管理页面</h2>
                   
                    <!-- responsive table example -->
                    <table class="table table-bordered">
                    
                        <thead>
                            <tr>
                                <th>企业名称</th>
                                <th colspan="2">邮箱</th>
                                <th>企业简介</th>
                                <th>联系人</th>
                                <th>公司地址</th>
                                <th>电话</th>
                                <th colspan="2">操作</th>
                            </tr>
                        </thead>
                        <tbody>
						<c:forEach items="${companys }" var="one">
                           <tr>
                            <td>${one.companyName }</td>
                            <td colspan="2">${one.mailbox }</td>
                            <td>
                            <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" title="<c:if test="${one.profile==null }">无</c:if>${one.profile }">公司简介</button>
                            </td>
                            <td>${one.contacts }</td>
                            <td>${one.address }</td>
                            <td>${one.phone }</td>
                            <td>
                             <c:choose>
                               <c:when test="${one.auditDate == null }">
                               <a href="${pageContext.request.contextPath }/ReviewOOOCompanyServlet?userName=${one.username}" class="btn btn-success">审核</a>
                               </c:when>
                               <c:otherwise>
                               <a href="${pageContext.request.contextPath }/ReviewOOOCompanyServlet?userName=${one.username}" class="btn btn-warning">退审</a>
                               </c:otherwise>
                            </c:choose>
							</td>
							<td><a href="${pageContext.request.contextPath }/DeleteServlet?userName=${one.username}" class="btn btn-danger">删除</a></td>
                          </tr>
                        </c:forEach>
                        </tbody>
                    </table>
               
			</dd>
			
			
			
		</dl>
	</div>
	
</body>
<script>
    $(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>  
</html>