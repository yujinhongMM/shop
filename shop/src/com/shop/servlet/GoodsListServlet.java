package com.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.GoodsService;
import com.shop.service.Impl.GoodsServiceImpl;

/**
 * 
 * Description 全部商品列表
 * @author 方琦
 * @date 2018年6月28日  
 *
 */
@WebServlet("/GoodsListServlet")
public class GoodsListServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		GoodsService goods=new GoodsServiceImpl();
		
		
		try {
			System.out.println(goods.listGood());
			request.getSession().setAttribute("GoodsList", goods.listGood());
			request.getSession().setAttribute("type", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("goodsList.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}