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
	
	你好，超级管理员<a href="/shop/LoginOutServlet">注销</a>
	<hr>
	<table>
		<thead>
			<tr>
				<th>卖家id</th>
				<th>商店名称</th>
				<th>是否有权限</th>
				<th colspan="2">操作</th>
			</tr>
		</thead>
		<tbody>
			
				<c:choose>
					<c:when test="${sellerlist!=null}">
						<c:forEach items="${sellerlist }" var="list">
							<tr>
								<td>${list.getSellerId()}</td>
								<td>${list.getShopname()}</td>
								<td>
									<c:if test="${list.getPower()==0}">
										无权限
									</c:if>
									<c:if test="${list.getPower()==1}">
										有权限
									</c:if>
								</td>
								<td>
									<c:if test="${list.getPower()==0}">
										<a href="/shop/ToExamineServlet?power=1&sellerId=${list.getSellerId()}">审核</a>
									</c:if>
									<c:if test="${list.getPower()==1}">
										<a href="/shop/ToExamineServlet?power=0&sellerId=${list.getSellerId()}">退审</a>
									</c:if>
								</td>
								<td><a href="/shop/DelectSellerServlet?sellerId=${list.getSellerId()}">删除</a></td>
							</tr>
							
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">没有卖家信息</td>
						</tr>
					</c:otherwise>
				</c:choose>
			
			<tr>
				<td colspan="5">
					<!-- 分页 -->
					第 ${AdminSellerPageUtils.pageNow } /
					${AdminSellerPageUtils.totalPage }页
					<c:if test="${AdminSellerPageUtils.isHasFirst() }">
						<a href="/shop/AdminSellerListServlet?nowPage=1">首页&nbsp;</a>
					</c:if>
					<c:if test="${AdminSellerPageUtils.isHasPre() }">
						<a
							href="/shop/AdminSellerListServlet?nowPage=${AdminSellerPageUtils.pageNow-1 }">上一页&nbsp;</a>
					</c:if>
					<c:if test="${AdminSellerPageUtils.isHasNext() }">
						<a
							href="/shop/AdminSellerListServlet?nowPage=${AdminSellerPageUtils.pageNow+1 }">下一页&nbsp;</a>
					</c:if>
					<c:if test="${AdminSellerPageUtils.isHasLast() }">
						<a
							href="/shop/AdminSellerListServlet?nowPage=${AdminSellerPageUtils.totalPage }">尾页</a>
					</c:if>
				</td>
				
			</tr>
		</tbody>
	</table>
	
</body>

</html>