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
</style>
<body>
	<%@include file="Title.jsp"%>
	
	<div class="right group">
		<dl>
		
		
			<dd>
				
                <h2>学院通知公告</h2>
                 <a style="float: right;margin-bottom: 10px;" href="/practice/jsp/adminAnnuncement-new.jsp" class="btn btn-success">添加新公告</a>
                <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>标题</th>
                                <th>发布日期</th>
                                <th>详情</th>
                                <th>编辑</th>
                                <th>删除</th>
                            </tr>
                        </thead>

                         <tbody>
                         	<c:forEach items="${adminNotice }" var="notice">
                         		<tr>
                         			<td>${notice.id }</td>
                         			<td>${notice.title }</td>
                         			<td>${notice.releaseDate }</td>
							<td><a href="${pageContext.request.contextPath }/LookAdminNoticeByIdServlet?Id=${notice.id}" class="btn btn-info">详情</a></td>
							<td><a href="${pageContext.request.contextPath }/ShowAdminNoticeByIdServlet?Id=${notice.id}" class="btn btn-warning">编辑</a></td>
							<td><a href="${pageContext.request.contextPath }/DeleteAdminNoticeServlet?Id=${notice.id}" class="btn btn-danger">删除</a></td>
                         		</tr>
                         	</c:forEach>
                         	
                         </tbody>
                         <tr>
					  		<td colspan="6" align="right">
					  			共${pagerOne.totalSize}条纪录，当前第${pagerOne.pageNow}/${pagerOne.totalPage}页，每页${pagerOne.pageSize}条纪录
					  		<c:choose>
					  			<c:when test="${pagerOne.hasPre}">
					  				<a href="ShowAdminNotices?pageNow=1">首页</a> |
	                				<a href="ShowAdminNotices?pageNow=${pager.pageNow - 1}">上一页</a> |
					  			</c:when>
					  			<c:otherwise>
					  				<c:out value="首页 | 上一页 | "></c:out>
					  			</c:otherwise>
					  		</c:choose>
					  		<c:choose>
					  			<c:when test="${pager.hasNext }">
	                				<a href="ShowAdminNotices?pageNow=${pager.pageNow + 1}">下一页</a> |
	                			<a href="ShowAdminNotices?pageNow=${pager.totalPage}">尾页</a>
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