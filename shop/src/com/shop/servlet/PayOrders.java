package com.shop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Buyer;
import com.shop.service.OrderService;
import com.shop.service.Impl.OrderServiceImpl;

@WebServlet("/PayOrders")
public class PayOrders extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderService orderservie=new OrderServiceImpl();
		//支付订单 参数： 1.用户id 2.用户钱包数 3.订单id数组
		Buyer buyer=(Buyer)request.getSession().getAttribute("buyer");
		String buyerid=buyer.getBuyerId();
		int wallet=buyer.getWallet();
		
		String StringOrdersid=request.getParameter("ordersid");
		
		//处理数据
		Integer[] Ordersid=new Integer[1];
	
			Ordersid[0]=Integer.valueOf(StringOrdersid);
		
		try {
			orderservie.payOrder_service(buyerid, wallet, Ordersid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//跳转到订单列表
		
		
		request.getRequestDispatcher("/SellerRecord").forward(request, response);
	}

	
}
