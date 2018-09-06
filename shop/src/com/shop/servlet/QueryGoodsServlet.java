package com.shop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.entity.Goods;
import com.shop.entity.Seller;
import com.shop.service.GoodsService;
import com.shop.service.Impl.GoodsServiceImpl;

/**
 * 
 * Description
 * @author 陈浩雨
 * @date 2018年6月28日  
 *
 */
@WebServlet("/QueryGoodsServlet")
public class QueryGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryGoodsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");


		String role =(String)session.getAttribute("role");
		if(!role.equals("2")){
			request.setAttribute("msg", "没有权限访问");  
            request.getRequestDispatcher("/404.jsp").forward(request, response);  
            return;
		}
		
    	String pageNowString;
    	if (request.getParameter("pageNow") == null ||request.getParameter("pageNow") == "0"){
    		pageNowString = "1";
    	}else{
    		pageNowString = request.getParameter("pageNow");
    	}
    	int pageNow = Integer.valueOf(pageNowString);
    	
    	
		Seller seller = (Seller) session.getAttribute("seller");
		String id = seller.getSellerId();
		// String id = "1111";
	
		
		GoodsService goodsService = new GoodsServiceImpl();
		String action = request.getParameter("action");
		if("update".equals(action)){
			
			try {
				Integer Id= Integer.parseInt(request.getParameter("id"));
				Goods goods = goodsService.findGoodById(Id);
				request.getSession().setAttribute("goods", goods);
//				request.getRequestDispatcher(request.getContextPath() + "/jsp/seller/UpdateGoods.jsp").forward(request, response);
				request.getRequestDispatcher("/jsp/seller/UpdateGoods.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if ("find".equals(action)) {
			try {
//				List<Goods> list = goodsService.findAllGoods(id);
				List<Goods> list = goodsService.findAllGoods(id,pageNow,session,seller.getSellerId());
				
				
				request.getSession().setAttribute("goodslist", list);
				response.sendRedirect(request.getContextPath() + "/jsp/seller/ListGoods.jsp");
			} catch (Exception e) {

				e.printStackTrace();
			}
		}else if("fname".equals(action)) {
			try {
				String name = request.getParameter("name");
				List<Goods> list= goodsService.findGoodByName(name,pageNow,session,seller.getSellerId());
				request.getSession().setAttribute("goodslist", list);
//				request.getRequestDispatcher(request.getContextPath() + "/jsp/seller/UpdateGoods.jsp").forward(request, response);
				request.getRequestDispatcher("/jsp/seller/ListGoods.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
