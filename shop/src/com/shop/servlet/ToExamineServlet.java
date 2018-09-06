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
 * 
 * Description 
 * @author YJH
 * @date 2018年6月28日  
 * @category 管理员退审、审核
 */
@WebServlet("/ToExamineServlet")
public class ToExamineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ToExamineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role =(String)request.getSession().getAttribute("role");
		int power = Integer.valueOf(request.getParameter("power"));
		String sellerId=request.getParameter("sellerId");
		if(role.equals("9")){
			
			ManagerService managerService=new ManagerServiceImpl();
			try {
				if(managerService.modifyPower(sellerId, power)){
				
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
