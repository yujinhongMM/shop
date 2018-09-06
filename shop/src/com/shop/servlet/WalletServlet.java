package com.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Buyer;
import com.shop.entity.Seller;
import com.shop.service.UserService;
import com.shop.service.Impl.UserServiceImpl;

/**
 * 
 * Description 钱包
 * @author YJH
 * @date 2018年6月28日  
 *
 */
@WebServlet("/WalletServlet")
public class WalletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public WalletServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String role =(String)request.getSession().getAttribute("role");
		String type=request.getParameter("type");
		int money=Integer.valueOf(request.getParameter("money"));
		UserService userService=new UserServiceImpl();
		if(role.equals("1")){
			Buyer buyer =(Buyer)request.getSession().getAttribute("buyer");
			try {
				if (userService.buyerManagerWallet(buyer.getBuyerId(), money, type)); {
					if(type.equals("1")){
						buyer.setWallet(buyer.getWallet()+money);
					}else{
						if(money>buyer.getWallet()){
							request.setAttribute("msg", "余额不足");  
				            request.getRequestDispatcher("/404.jsp").forward(request, response);  
				            return;
						}
						buyer.setWallet(buyer.getWallet()-money);
					}
					request.setAttribute("buyer", buyer);
					request.getRequestDispatcher("SellerRecord").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(role.equals("2")){
			Seller seller =(Seller)request.getSession().getAttribute("seller");
			try {
				if(userService.sellerManagerWallet(seller.getSellerId(), money, type)){
					if(type.equals("1")){
						seller.setWallet(seller.getWallet()+money);
					}else{
						seller.setWallet(seller.getWallet()-money);
					}
					request.getSession().setAttribute("seller", seller);
					request.getRequestDispatcher("SellerRecord").forward(request, response);
				}
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
