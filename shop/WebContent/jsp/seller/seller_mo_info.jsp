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
	<h2>卖家个人信息</h2>
		<div>
			<form method="POST" action="/shop/ModifySellerServlet?type=1">
				<table> 
					
						<tr><td>账号</td><td><input disabled="" type="text" value="${seller.sellerId}"/></td></tr>
						<tr><td>店名</td><td><input required type="text" value="${seller.shopname}" name="shopname"/></td></tr>
						<tr><td>邮箱</td><td><input required type="text" value="${seller.email}" name="email"/></td></tr>
						<tr><td>生日</td><td><input required type="text" value="${seller.birthday}" name="birthday"/></td></tr>
						
						<tr><td></td><td><input type="submit" value="确认修改"></td></tr>
				
				</table>
			</form>
			<form method="POST" action="/shop/ModifySellerServlet?type=2">
			<table>
				
					<tr><td>旧密码</td><td><input required type="password" name="oldpassword"/></td></tr>
					<tr><td>新密码</td><td><input required type="password"  name="newpassword"/></td></tr>
					<tr><td></td><td><input type="submit" value="确认修改"></td></tr>
				
			</table>
			</form>
		</div>      
	
</body>

</html>