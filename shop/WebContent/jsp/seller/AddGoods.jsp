<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加商品</title>
<script>
	function loadingimg(obj) {
		var file = obl.files[0];//获取上传文件组件里的文件对象
		var url = window.URL.createObjectURL(file);//创建文件对象的URL地址
		document.getElementById("img").src = url;//设置url地址
	}
</script>
</head>

	<%	
		String id = (String)request.getAttribute("id");
		String action = (String)request.getAttribute("action");
	%>


<body>
	<ul>
		<a href="/shop/jsp/seller/seller_mo_info.jsp"><li>个人信息</li></a>
		<c:if test="${seller.power==1 }">
			<a href="/shop/QueryGoodsServlet?action=find"><li>我的商品</li></a>
			<a href="/shop/jsp/seller/AddGoods.jsp"><li>增加商品</li></a>
			<a href="/shop/SellerRecord"><li>交易记录</li></a>
		</c:if>
		<a href="/shop/LoginOutServlet"><li>注销</li></a>
	</ul>

	<form id="aa" action="/shop/AddGoodsServlet?action=add" method="post"
		enctype="multipart/form-data">
		商品名称:<input type="text" name="name" /><br> 
		商品产地：<input type="text" name="city" /><br> 
		商品价格：<input type="text" name="price" /><br> 
		商品数量：<input type="text" name="number" /><br> 
		商品图片：<img id='img' width="300px" height="300px" />
		<input name="goodsPic" type="file" onchange="loadingimg(this)" /> <input
			type="submit" value="增加">
	</form>
	
</body>
</html>