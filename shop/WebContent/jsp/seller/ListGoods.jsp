<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品列表</title>
</head>
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
	<table>
		<tr>
			<th>产品id</th>
			<th>产品名称</th>
			<th>产地</th>
			<th>价格</th>
			<th>数量</th>
			<th>图片</th>
			<th>操作</th>

		</tr>
		<c:forEach var="item" items="${sessionScope.goodslist}">
			<tr>
				<td>${item.goodId}</td>
				<td>${item.name}</td>
				<td>${item.city}</td>
				<td>${item.price}</td>
				<td>${item.number}</td>
				<td><img src="../../img/${item.url}" /></td>
				<td><a
					href="/shop/QueryGoodsServlet?id=${item.goodId}&action=update">修改</a>/<a
					href="/shop/DeleteGoodsServlet?id=${item.goodId}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pp">

		<span><a href="/shop/QueryGoodsServlet?pageNow=1&action=find">首页</a></span>
		<c:if test="${sessionScope.pageUtils.hasPre == true}">
			<span><a
				href="/shop/QueryGoodsServlet?pageNow=${sessionScope.pageUtils.pageNow - 1}&action=find">上一页</a>
			</span>
		</c:if>
		<c:if test="${sessionScope.pageUtils.hasNext == true}">
			<span><a
				href="/shop/QueryGoodsServlet?pageNow=${sessionScope.pageUtils.pageNow + 1}&action=find">下一页</a>
			</span>
		</c:if>
		<span><a
			href="/shop/QueryGoodsServlet?pageNow=${sessionScope.pageUtils.totalPage}&action=find">尾页</a></span>
	</div>

</body>
</html>