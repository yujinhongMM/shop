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
			 <form action="UpdateNoticeServlet" name="myform" method="post">
				<h3>公告修改</h3>
                                         标题： <input type="text" name="title" value="${notice.title }"><br><br>
              <input type="hidden" name="id" value="${notice.id }" >           
                                
                                    内容: <textarea name="content">${notice.content }</textarea><br><br>
                                    
              <input type="submit" value="提交" class="btn btn-success">
              <input type="reset" value="重置" class="btn btn-danger">
          
              <a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
              </form>
			</dd>			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>