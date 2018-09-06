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
				<form action="/practice/AddNoticeCompanyServlet" name="myform" method="post">
                   
                      
                           
                                <label>标题：</label>
                                <input type="text" name="title" id="ti">
                           
                        <div>
                            <label>内容:</label>
                            <textarea id="editor-trigger" id="con" name="content" style="width:100%;height:400px;"></textarea>
                        </div>
                        <div>
                            <input type="submit" value="提交" class="btn btn-danger">
                            <input type="reset" value="重置" class="btn btn-success">
                            <a href="javascript:history.back(-1);" class="btn btn-default" > 返回 </a>
                        </div>
                    
                </form>
			</dd>
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>