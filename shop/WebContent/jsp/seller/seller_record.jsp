<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>交易记录</title>
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
	        
	余额:${seller.getWallet()}
	<form action="/shop/WalletServlet?type=1" method="POST">
	 	<input type="text" name="money" value="0"/>
	 	<input type="submit" value="充值"/>
	</form>
	<form action="/shop/WalletServlet?type=2" method="POST">
		<input type="text" name="money" value="0"/>
	 	<input type="submit" value="提现"/>
	</form>         

	<c:choose>
		<c:when test="${orderSellerList!=null}">
			<c:forEach items="${orderSellerList }" var="list">
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
	第 ${SellerPageUtils.pageNow } /
	${SellerPageUtils.totalPage }页
	<c:if test="${SellerPageUtils.isHasFirst() }">
		<a href="/shop/SellerRecord?nowPage=1">首页&nbsp;</a>
	</c:if>
	<c:if test="${SellerPageUtils.isHasPre() }">
		<a
			href="/shop/SellerRecord?nowPage=${SellerPageUtils.pageNow-1 }">上一页&nbsp;</a>
	</c:if>
	<c:if test="${SellerPageUtils.isHasNext() }">
		<a
			href="/shop/SellerRecord?nowPage=${SellerPageUtils.pageNow+1 }">下一页&nbsp;</a>
	</c:if>
	<c:if test="${SellerPageUtils.isHasLast() }">
		<a
			href="/shop/SellerRecord?nowPage=${SellerPageUtils.totalPage }">尾页</a>
	</c:if>
	
</body>

</html>