<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/shop/css/goodsList.css" rel='stylesheet' type='text/css' />
    <title>首页</title>
</head>

<body>
<c:if test="${type==null}">
		<c:redirect url="GoodsListServlet" />  
</c:if>
		<center>
			<div id="a">
				<ul id="menu">
				    <li>
						<img class="b" src="img/登录图标.png">
					</li>
					<li>
						<a href="/shop/LoginOutServlet">注销</a>
					</li>
					
					<li>
						<img class="b" src="img/卖家图标.png">
					</li>
					<li>
						<a href="/shop/GoodsListServlet">首页</a>
					</li>
					<li>
						<img class="b" src="img/个人信息.png">
					</li>
					<li>
						<a href="/shop/jsp/buyer/buyer_mo_info.jsp">个人中心</a>
					</li>
					<li>
						<img class="b" src="img/购物车图标.jpg">
					</li>
					<li>
						<a href="/shop/Seeshopcart">购物车</a>
					</li>

				</ul>
			</div>
		</center>
		<div style="width:80%;margin:0 auto;">
			<div id="flow-box">
			<c:choose>
				<c:when test="${GoodsList != null }">
					<c:forEach items="${GoodsList}" var="goods">
						<div>
							<a href="GoodsInfoServlet?goodId=${goods.getGoodId()}"><img src="/shop/img/${goods.getUrl()}" /></a>
							<ul>
							
								<li><strong>商品名：</strong>${goods.getName()}</li>
								<li><strong>价格：</strong>${goods.getPrice()}</li>
							</ul>
						</div>

					</c:forEach>
				</c:when>
				<c:otherwise>
					<h2>没有图片</h2>
				</c:otherwise>
			</c:choose>
		    </div>
		    
	   </div>

	</body>

</html>