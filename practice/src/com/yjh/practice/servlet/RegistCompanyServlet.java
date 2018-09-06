package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.service.CompanyService;
import com.yjh.practice.service.impl.CompanyServiceImpl;



@WebServlet("/RegistCompanyServlet")
public class RegistCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST解码
		request.setCharacterEncoding("UTF-8");
		String qyname = request.getParameter("qyname");
		String qyusername = request.getParameter("qyusername");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String yzm = request.getParameter("yzm");
		
		// 页面获得的由后台产生的验证码
		String vchidden =(String) request.getSession().getAttribute("vchidden");
		
		
		System.out.println("进入Servlet");
		try {
			CompanyService companyService = new CompanyServiceImpl();
			if (companyService.registerCompanyInfo(qyusername, qyname, email, password, yzm,vchidden)) {
				response.sendRedirect("/practice/jsp/login.jsp");
				return;
			}else {
				//跳转到404页面,并打印错误信息
				String errorMessage = "访问数据库出现异常！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return ;
			}
		} catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
	}

}
