package com.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Buyer;
import com.shop.service.UserService;
import com.shop.service.Impl.UserServiceImpl;
@WebServlet("/Addshopcart")
public class Addshopcart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Buyer buyer=(Buyer)request.getSession().getAttribute("buyer");
		String Buyerid=buyer.getBuyerId();
		Integer goodid=Integer.valueOf(request.getParameter("goodId"));
		Integer number=Integer.valueOf(request.getParameter("number"));
		System.out.println(Buyerid+","+goodid+","+number);
		//调用接口
		UserService userservice=new UserServiceImpl();
		try {
			userservice.addCartGoods_service(Buyerid, goodid, number);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//提示添加成功
		request.setAttribute("state", "添加成功");
		//返回商品列表界面
		request.getRequestDispatcher("/goodsInfo.jsp").forward(request, response);
		
	}
	

}
