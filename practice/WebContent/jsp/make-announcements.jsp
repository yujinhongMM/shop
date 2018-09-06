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
			
                
                           
                     <h2>企业通知公告列表</h2>
                     <div >
                         <a class="btn btn-success" style="float: right;margin-bottom: 10px;" href="/practice/jsp/annuncement-new.jsp">添加新公告</a>
                     </div>
                     
                         <table class="table table-bordered">
                             <thead>
                                 <tr>
                                     <th>ID</th>
                                     <th>企业用户名</th>
                                     <th>标题</th>
                                     <th>发布日期</th>
                                     <th>状态</th>
                                     <th>详情</th>
                                     <th>编辑</th>
                                     <th>删除</th>
                                 </tr>
                             </thead>

                             <tbody>
                             	<c:forEach items="${notices }" var="notice">
                             		<tr>
                             			<td>${notice.id }</td>
                             			<td>${notice.companyUsername }</td>
                             			<td>${notice.title }</td>
                             			<td>${notice.releaseDate }</td>
                             			<td>
                             			<c:choose>
                             				<c:when test="${notice.auditDate == null }">
                             					<c:out value="未通过"/>
                             				</c:when>
                             				<c:otherwise>
                             					<c:out value="通过"/>
                             				</c:otherwise>
                             			</c:choose>
								</td>
								<td><a href="${pageContext.request.contextPath }/LookNoticeByIdServlet?Id=${notice.id}" class="btn btn-info" >详情</a></td>
								<td><a href="${pageContext.request.contextPath }/ShowNoticeByIdServlet?Id=${notice.id}" class="btn btn-warning" >编辑</a></td>
								<td><a href="${pageContext.request.contextPath }/DeleteNoticeServlet?Id=${notice.id}" class="btn btn-danger" >删除</a></td>
								                             		</tr>
								                             	</c:forEach>
								                             </tbody>
								                             <tr>
								<td colspan="8" align="right">
									共${pager.totalSize}条纪录，当前第${pager.pageNow}/${pager.totalPage}页，每页${pager.pageSize}条纪录
									<c:choose>
										<c:when test="${pager.hasPre}">
											<a href="/practice/ShowNoticeListServlet?pageNow=1">首页</a> |
								         				<a href="/practice/ShowNoticeListServlet?pageNow=${pager.pageNow - 1}">上一页</a> |
										</c:when>
										<c:otherwise>
											<c:out value="首页 | 上一页 | "></c:out>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${pager.hasNext }">
								         				<a href="/practice/ShowNoticeListServlet?pageNow=${pager.pageNow + 1}">下一页</a> |
								         			<a href="/practice/ShowNoticeListServlet?pageNow=${pager.totalPage}">尾页</a>
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