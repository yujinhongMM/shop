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
    <title>交易记录</title>
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
	<ul>
		<a href="/shop/jsp/buyer/buyer_mo_info.jsp"><li>个人信息</li></a>
		<a href="/shop/SellerRecord"><li>交易记录</li></a>
		<a href="/shop/LoginOutServlet"><li>注销</li></a>
	</ul>
	        
	 余额:${buyer.getWallet()}
	<form action="/shop/WalletServlet?type=1" method="POST">
	 	<input type="text" name="money" value="0"/>
	 	<input type="submit" value="充值"/>
	</form>
	<form action="/shop/WalletServlet?type=2" method="POST">
		<input type="text" name="money" value="0"/>
	 	<input type="submit" value="提现"/>
	</form>          
	    

	<c:choose>
		<c:when test="${orderBuyerList!=null}">
			<c:forEach items="${orderBuyerList }" var="list">
				<div>
					<ul>
						<li>订单号：${list.getOrder().getOrderid()}</li>
						<li>用户：${list.getBuyer().getNickname()}</li>
						<li>商铺：${list.getSeller().getShopname()}</li>
						<li>商品：${list.getGoods().getName()}</li>
						<li>单价：${list.getGoods().getPrice()}</li>
						<li>数量：${list.getOrder().getNumber()}</li>
						<li>时间：${list.getOrder().getCreatedate()}</li>
						<li>交易金额：${list.getOrder().getAllmoney()}</li>
					</ul>
				</div>
				
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h2>没有交易信息</h2>
		</c:otherwise>
	</c:choose>
	<!-- 分页 -->
	第 ${BuyerPageUtils.pageNow } /
	${BuyerPageUtils.totalPage }页
	<c:if test="${BuyerPageUtils.isHasFirst() }">
		<a href="/shop/SellerRecord?nowPage=1">首页&nbsp;</a>
	</c:if>
	<c:if test="${BuyerPageUtils.isHasPre() }">
		<a
			href="/shop/SellerRecord?nowPage=${BuyerPageUtils.pageNow-1 }">上一页&nbsp;</a>
	</c:if>
	<c:if test="${BuyerPageUtils.isHasNext() }">
		<a
			href="/shop/SellerRecord?nowPage=${BuyerPageUtils.pageNow+1 }">下一页&nbsp;</a>
	</c:if>
	<c:if test="${SellerPageUtils.isHasLast() }">
		<a
			href="/shop/SellerRecord?nowPage=${BuyerPageUtils.totalPage }">尾页</a>
	</c:if>
	
	
</body>

</html>