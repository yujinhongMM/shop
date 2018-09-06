<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>主页</title>
<jsp:include page="head.txt"/>
</head>
<body style="text-align: center;">
<div style="text-align: left;width:50%;margin:0 auto;">
	<hr>
	<c:if test="${sessionScope.info == null}">
	<c:redirect url="/ShowIndexNoticeListServlet"></c:redirect>
	</c:if>
	<dl>
		<dt>学院通知公告<a style="float:right" href="MoreIndexNoticesListServlet?tole=1">更多</a></dt>
              <c:forEach items="${adminyNotices }" var="list2">
              	<dd><a href="NoticeInfoService?type=1&id=${list2.id }">${list2.title }</a>
              	<span style="float:right">${list2.releaseDate }</span>
              	</dd>
              </c:forEach>
         
	</dl>
	<hr>
	<dl>
		<dt>企业通知公告 <a style="float:right" href="MoreIndexNoticesListServlet?tole=2">更多</a></dt>
            <c:forEach items="${companyNotices }" var="list">
            	<dd><a href="NoticeInfoService?type=2&id=${list.id }">${list.title }</a>
            	<span style="float:right">${list.releaseDate }</span>
            	</dd>
            </c:forEach>
        
	</dl>
	
	
</div>
</body>
</html>