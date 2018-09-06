package com.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.OrderView;
import com.shop.service.GoodsService;
import com.shop.service.Impl.GoodsServiceImpl;

/**
 * 
 * Description 商品和卖家详细信息
 * @author 方琦
 * @date 2018年6月28日  
 *
 */
@WebServlet("/GoodsInfoServlet")
public class GoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		GoodsService goodsService=new GoodsServiceImpl();
		OrderView orderView=new OrderView();
		int goodId=Integer.valueOf(request.getParameter("goodId"));
		
		try {
			System.out.println(goodId);
			orderView=goodsService.findByGoodId(goodId);
			System.out.println(orderView);
			if(orderView!=null) {
				request.getSession().setAttribute("sellerGoodsInfoView", orderView);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("goodsInfo.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}