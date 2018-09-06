package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.Company;
import com.yjh.practice.service.CompanyService;
import com.yjh.practice.service.impl.CompanyServiceImpl;



/**
 * Servlet implementation class ShowCompanyServlet
 */
@WebServlet("/ShowCompanyServlet")
public class ShowCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post解码
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		CompanyService companyService = new CompanyServiceImpl();
		Company company = null ;
		try {
			company = companyService.queryByUserName(account);
			session.setAttribute("company", company);
			request.getRequestDispatcher("/jsp/enterprise-information-maintenance.jsp").forward(request, response);
		}catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}

