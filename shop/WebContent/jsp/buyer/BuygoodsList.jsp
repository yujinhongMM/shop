<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/shop/css/goodsList.css" rel='stylesheet' type='text/css' />
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>购物车列表</title>
</head>
<body>
	<center>
			<div id="a">
				<ul id="menu">
				    <li>
						<img class="b" src="/shop/img/登录图标.png">
					</li>
					<li>
						<a href="/shop/LoginOutServlet">注销</a>
					</li>
					
					<li>
						<img class="b" src="/shop/img/卖家图标.png">
					</li>
					<li>
						<a href="/shop/GoodsListServlet">首页</a>
					</li>
					<li>
						<img class="b" src="/shop/img/个人信息.png">
					</li>
					<li>
						<a href="/shop/jsp/buyer/buyer_mo_info.jsp">个人中心</a>
					</li>
					<li>
						<img class="b" src="/shop/img/购物车图标.jpg">
					</li>
					<li>
						<a href="/shop/Seeshopcart">购物车</a>
					</li>

				</ul>
			</div>
		</center>
	<form action="">
		<table>
			
					<tr>
						<td>商品名称</td>
						<td>商品单价</td>
						<td>购买数量</td>
						<td colspan="2">操作</td>
					</tr>
				<c:choose>
					<c:when test="${shopcart!=null}">
						<c:forEach items="${shopcart }" var="list">
							<tr>
								<td>${list.getGoods().getName()}</td>
								<td>${list.getGoods().getPrice()}</td>
								<td>${list.number}</td>
								<td><a href="/shop/Deleteshopcart?goodsid=${list.getGoods().getGoodId()}">删除</a></td>
								<td><a href="/shop/CreateOrder?Goodsid=${list.getGoods().getGoodId()}">购买</a></td>
							</tr>
							
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">没有商品信息</td>
						</tr>
					</c:otherwise>
			</c:choose>
		</table>
	</form>
	
</body>

</html>