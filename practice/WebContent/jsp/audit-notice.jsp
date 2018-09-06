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
				<h2>未审核通知公告列表</h2>
                      
                           <table class="table table-bordered">
                               <thead>
                                   <tr>
                                       <th>ID</th>
                                       <th>企业用户名</th>
                                       <th>标题</th>
                                       <th>发布日期</th>
                                       <th>查看</th>
                                       <th colspan="2">操作</th>
                                       
                                   </tr>
                               </thead>

                               <tbody>
                                  <c:forEach items="${aditNotice }" var="notice">
                               		<tr>
                               			<td>${notice.id }</td>
                               			<td>${notice.companyUsername }</td>
                               			<td>${notice.title }</td>
                               			<td>${notice.releaseDate }</td>
										<td><a href="${pageContext.request.contextPath }/LookNoticeByIdServlet?Id=${notice.id}" class="btn btn-info">查看</a></td>
										<td><a href="${pageContext.request.contextPath }/ReviewNoticeServlet?Id=${notice.id}" class="btn btn-success">审核通过</a></td>
										<td><a href="${pageContext.request.contextPath }/DeleteNoticeServlet?Id=${notice.id}" class="btn btn-danger" >删除</a></td>
									</tr>
                               	</c:forEach>
                               </tbody>
                			<tr>
								<td colspan="7" align="right">
								共${pager1.totalSize}条纪录，当前第${pager1.pageNow}/${pager1.totalPage}页，每页${pager1.pageSize}条纪录
									<c:choose>
										<c:when test="${pager1.hasPre}">
											<a href="ShowNoticeListsServlet?pageNow=1">首页</a> |
							          				<a href="ShowNoticeListsServlet?pageNow=${pager1.pageNow - 1}">上一页</a> |
										</c:when>
										<c:otherwise>
											<c:out value="首页 | 上一页 | "></c:out>
										</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${pager1.hasNext }">
										<a href="ShowNoticeListsServlet?pageNow=${pager1.pageNow + 1}">下一页</a> |
										<a href="ShowNoticeListsServlet?pageNow=${pager1.totalPage}">尾页</a>
									</c:when>
									<c:otherwise>
										<c:out value="下一页 | 尾页"/>
									</c:otherwise>
  									</c:choose>
 								</td>
							</tr>	  
                       </table>
                                
			</dd>
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>