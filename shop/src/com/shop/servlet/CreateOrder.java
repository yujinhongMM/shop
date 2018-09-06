package com.shop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Buyer;
import com.shop.entity.Orders;
import com.shop.service.OrderService;

import com.shop.service.Impl.OrderServiceImpl;


@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 生成当订单 参数：1.商品id数组 2.用户id
		// 接收参数
		Buyer buyer=(Buyer)request.getSession().getAttribute("buyer");
		String buyerid=buyer.getBuyerId();

		String StringGoodsid = request.getParameter("Goodsid");

		// 处理数据
		Integer[] Goodsid = new Integer[1];
		
			Goodsid[0] = Integer.valueOf(StringGoodsid);
		

		// 调用接口
		OrderService orderservie=new OrderServiceImpl();
		List<Orders> orders=null;
		try {
			orders=	orderservie.createOrder_service(Goodsid, buyerid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//保存数据
		request.getSession().setAttribute("orders", orders);
		//转发
		
		 request.getRequestDispatcher("/jsp/buyer/createOrder.jsp").forward(request, response);  
	}

}
