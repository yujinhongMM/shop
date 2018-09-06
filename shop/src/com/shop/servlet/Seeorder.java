package com.shop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.OrderView;
import com.shop.service.OrderService;
import com.shop.service.Impl.OrderServiceImpl;

@WebServlet("/Seeorder")
public class Seeorder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderService orderservie=new OrderServiceImpl();
		//查看订单详情 参数 1.订单id 2.商品id 3.用户id
		Integer orderid=Integer.valueOf(request.getParameter("orderid"));
		Integer goodsid=Integer.valueOf(request.getParameter("goodsid"));
		String buyerid=request.getParameter("orderid");
		OrderView orderview=null;
		try {
			orderview=orderservie.seeorder_service(orderid, goodsid, buyerid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回orderview
		//返回到订单详情界面
	}

	
}
