package com.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.ManagerService;
import com.shop.service.Impl.ManagerServiceImpl;

/**
 * Servlet implementation class DelectSellerServlet
 */
/**
 * 
 * Description
 * @author YJH
 * @date 2018年6月28日  
 *
 */
@WebServlet("/DelectSellerServlet")
public class DelectSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DelectSellerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role =(String)request.getSession().getAttribute("role");
		String sellerId=request.getParameter("sellerId");
		if(role.equals("9")){
			
			ManagerService managerService=new ManagerServiceImpl();
			try {
				if(managerService.delectSeller(sellerId)){
				
					request.getRequestDispatcher("AdminSellerListServlet").forward(request, response);
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
