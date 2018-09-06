package com.shop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Buyer;
import com.shop.entity.Shopcart;
import com.shop.service.UserService;
import com.shop.service.Impl.UserServiceImpl;

@WebServlet("/Seeshopcart")
public class Seeshopcart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//接受参数
		Buyer buyer=(Buyer)request.getSession().getAttribute("buyer");
		String buyerid=buyer.getBuyerId();
		List<Shopcart> shopcart=new ArrayList<Shopcart>();
		UserService userservicedao=new UserServiceImpl();
			try {
				shopcart=userservicedao.seecart_service(buyerid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("shopcart", shopcart);
			response.sendRedirect("/shop/jsp/buyer/BuygoodsList.jsp");
			
	}
	
	

}
