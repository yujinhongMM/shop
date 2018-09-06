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
    <title>支付订单</title>
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
	<form action="/shop/PayOrders">
		<c:forEach items="${orders}" var="list">
				<div>
					<ul>
						<li>订单号：${list.getOrderid()}</li>
					
			
						<li>商品：${list.getGoodid()}</li>
						
						<li>数量：${list.getNumber()}</li>
						<li>时间：${list.getCreatedate()}</li>
						<li>状态：${list.getStatus()}</li>
						<li>交易金额：${list.getAllmoney()}</li>
					</ul>
				</div>
							<input type="hidden" value="${list.getOrderid()}" name="ordersid"/>
				<input type="submit" value="确认支付"/>
				<a href="javascript:history.back(-1);">取消</a>
			</c:forEach>
	</form>
	
</body>

</html>