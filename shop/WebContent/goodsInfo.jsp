<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/shop/css/goodsInfo.css" rel='stylesheet' type='text/css' />
    <title>商品详情</title>

</head>
<body>
<center>
			<div id="a">
				<ul id="menu">
					<li>
						<img class="g" src="img/登录图标.png">
					</li>
					<li>
						<a href="/shop/LoginOutServlet">注销</a>
					</li>
					<li>
						<img class="g" src="img/卖家图标.png">
					</li>
					<li>
						<a href="/shop/GoodsListServlet">首页</a>
					</li>
					<li>
						<img class="g" src="img/个人信息.png">
					</li>
					<li>
						<a href="/shop/jsp/buyer/buyer_mo_info.jsp">个人中心</a>
					</li>
					<li>
						<img class="g" src="img/购物车图标.jpg">
					</li>
					<li>
						<a href="/shop/Seeshopcart">购物车</a>
					</li>
 					
				</ul>
			</div>
			
			<c:choose>
				<c:when test="${sellerGoodsInfoView != null }">
				 <div id="b">
					<form action="/shop/Addshopcart">
					<div id="c">
					<img id="d" src="/shop/img/${sellerGoodsInfoView.getGoods().getUrl()}" />
					</div>
					<div id="e">
					<ul>
						<li><strong>商品名：</strong>${sellerGoodsInfoView.getGoods().getName()}</li><br />
						<li><strong>价格：</strong>${sellerGoodsInfoView.getGoods().getPrice()}</li><br />
						<li><strong>城市：</strong>${sellerGoodsInfoView.getGoods().getCity()}</li><br />
						<li><strong>卖家：</strong>${sellerGoodsInfoView.getSeller().getShopname()}</li><br />
						<input type="hidden" name="goodId" value="${sellerGoodsInfoView.getGoods().getGoodId()}"/>
						
						购买数量：<input type="button" value="-" onClick="javascript:this.form.number.value--;"/>
						<input id="f" type="text" name="number" value="1"/>
						<input type="button" value="+" onClick="javascript:this.form.number.value++;"><br /><br />
						<input type="submit" value="加入购物车"/>
						<a href="/shop/Seeshopcart"><input type="button" value="查看购物车"/></a>
					</ul>
					</div>
					</form>
				</div>	
				</c:when>
				
				<c:otherwise>
					<h2>没有图片</h2>
				</c:otherwise>
			</c:choose>
		    ${state }
     </center> 

</body>
</html>