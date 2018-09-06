<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>查询手机</title>
</head>
<body>
	<form class="wordstyle" action="/shop/QueryGoodsServlet?action=fname"
		method="post">

		手机:<input type="text" name="name"> <input
			class="addsty" type="submit" value="查询"> <input
			class="addsty" type="reset" value="重置">
	</form>
</body>
</html>