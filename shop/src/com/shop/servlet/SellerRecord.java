package com.shop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Buyer;
import com.shop.entity.OrderView;
import com.shop.entity.Seller;
import com.shop.service.OrderService;
import com.shop.service.Impl.OrderServiceImpl;
import com.shop.utils.PageUtils;

/**
 * 
 * @author YJH
 * @category 卖家/买家查看交易记录
 */
@WebServlet("/SellerRecord")
public class SellerRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SellerRecord() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role =(String)request.getSession().getAttribute("role");
		String nowPage = request.getParameter("nowPage");
		if(role.equals("2")){
			Seller seller =(Seller)request.getSession().getAttribute("seller");
			
			if (nowPage == null)	// 未得到请求的页数，默认为1
				nowPage = 1 + "";
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("SellerPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(1);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			
			OrderService orderService=new OrderServiceImpl();
			try {
				
				List<OrderView> orderSellerList = orderService.findOrderBySellerId(seller.getSellerId(),pageUtils);
				
				if(orderSellerList!=null){
					request.getSession().setAttribute("orderSellerList",orderSellerList);
					request.getSession().setAttribute("SellerPageUtils", pageUtils);
				}
				
				request.getRequestDispatcher("/jsp/seller/seller_record.jsp").forward(request, response); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(role.equals("1")){
			
			if (nowPage == null)	// 未得到请求的页数，默认为1
				nowPage = 1 + "";
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("BuyerPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(1);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			
			Buyer buyer=(Buyer)request.getSession().getAttribute("buyer");
			OrderService orderService=new OrderServiceImpl();
			try {
				
				List<OrderView> orderBuyerList = orderService.findOrderByBuyerId(buyer.getBuyerId(),pageUtils);
				
				if(orderBuyerList!=null){
					request.setAttribute("orderBuyerList",orderBuyerList);
					request.getSession().setAttribute("BuyerPageUtils", pageUtils);
				}
				
				request.getRequestDispatcher("/jsp/buyer/buyer_record.jsp").forward(request, response); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			request.setAttribute("msg", "没有权限访问");  
            request.getRequestDispatcher("/404.jsp").forward(request, response);  
            return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
