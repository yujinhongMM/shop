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
	        
	
	<h2>买家个人信息</h2>
		<div>
			<form method="POST" action="/shop/ModifyBuyerServlet?type=1">
				<table> 
					
						<tr><td>账号</td><td><input disabled="" type="text" value="${buyer.buyerId}"/></td></tr>
						<tr><td>昵称</td><td><input required type="text" value="${buyer.nickname}" name="nickname"/></td></tr>
						<tr><td>邮箱</td><td><input required type="text" value="${buyer.email}" name="email"/></td></tr>
						<tr><td>生日</td><td><input required type="text" value="${buyer.birthday}" name="birthday"/></td></tr>
						<tr><td>地址</td><td><input required type="text" value="${buyer.address}" name="address"/></td></tr>
						<tr><td></td><td><input type="submit" value="确认修改"></td></tr>
				
				</table>
			</form>
			<form method="POST" action="/shop/ModifyBuyerServlet?type=2">
			<table>
				
					<tr><td>旧密码</td><td><input required type="password" name="oldpassword"/></td></tr>
					<tr><td>新密码</td><td><input required type="password"  name="newpassword"/></td></tr>
					<tr><td></td><td><input type="submit" value="确认修改"></td></tr>
				
			</table>
			</form>
		</div>      
	    
	
	
</body>

</html>