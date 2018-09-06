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
			
			
			
			
			
			
			<!--路径导航-->
				
				
						<h3>${requestScope.noticeAdmin!=null?"学院通知公告":"企业通知公告" }</h3>
					
						
							<h1>${requestScope.notice.title }</h1>
						<hr>
						
							<span>来源:
								${requestScope.noticeAdmin!=null?"管理员":requestScope.noticeCompany.companyUsername }&nbsp;&nbsp;&nbsp;时间:
								${requestScope.notice.releaseDate }
							</span>
						
							<p>
								<span>${requestScope.notice.content }</span>
							</p>
						
			
		 <a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
                   
	
</div>
</body>
<script type="text/javascript">  

</script>  
</html>