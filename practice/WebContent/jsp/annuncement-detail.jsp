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
				<h3>公告详情</h3>
                                         标题： ${oneNotice.title }<br><br>
                                      
                                   发布日期： ${oneNotice.releaseDate}<br><br>
                                    内容: ${oneNotice.content }<br><br>
              <a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
			</dd>
			
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>