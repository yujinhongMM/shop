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
			
				
				
				

			
					<h2 style="text-align: center;">详情</h2>
					
					
		                                            标题：${oneAdminNotice.title }<br><br>
		                                           发布日期：${oneAdminNotice.releaseDate}<br><br>
		                                           内容:${oneAdminNotice.content }<br><br>
                    <a href="javascript:history.back(-1);" class="btn btn-default">返回</a>                 
		
			</dd>
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>