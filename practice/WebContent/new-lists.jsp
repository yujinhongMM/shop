<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实训主页</title>



<jsp:include page="head.txt"/>
</head>
<style>

</style>
<body style="text-align: center;">
<div style="text-align: left;width:50%;margin:0 auto;">

				 <h3>${tole.equals("1")?"学院通知公告":"企业通知公告" }</h3><hr>
				
						<c:if test="${tole.equals('1') }">
						
						<c:forEach items="${allList }" var="list">
				      
				      	
                                	<a href="NoticeInfoService?type=1&id=${list.id }">${list.title }</a>
                                	<span style="float:right">${list.releaseDate }</span><br>
                                	
                            
					
				      </c:forEach>
						</c:if>
				      <c:if test="${tole.equals('2') }">
						
						<c:forEach items="${allList }" var="list">
				      
				      	
                                	<a href="NoticeInfoService?type=2&id=${list.id }">${list.title }</a>
                                	<span style="float:right">${list.releaseDate }</span><br>
                                	
                            
					
				      </c:forEach>
						</c:if>
				      
				      
				   
				
				<ul>
				 <li> 共${pager.totalSize}条纪录，当前第${pager.pageNow}/${pager.totalPage}页，每页${pager.pageSize}条纪录
				<c:choose>
				<c:when test="${pager.hasPre}">
				<a href="ShowNoticeListsServlet?pageNow=1">首页</a> |
				      				<a href="ShowNoticeListsServlet?pageNow=${pager.pageNow - 1}">上一页</a> |
				</c:when>
				<c:otherwise>
				<c:out value="首页 | 上一页 | "></c:out>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${pager.hasNext }">
				<a href="ShowNoticeListsServlet?pageNow=${pager.pageNow + 1}">下一页</a> |
				<a href="ShowNoticeListsServlet?pageNow=${pager.totalPage}">尾页</a>
				</c:when>
				<c:otherwise>
				<c:out value="下一页 | 尾页"/>
				</c:otherwise>
				</c:choose>
				</li>
				  </ul>
				          <a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
                   
	
</div>
</body>
<script type="text/javascript">  

</script>  
</html>