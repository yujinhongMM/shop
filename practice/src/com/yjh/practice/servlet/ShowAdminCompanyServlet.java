package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.Company;
import com.yjh.practice.service.CompanyService;
import com.yjh.practice.service.impl.CompanyServiceImpl;


@WebServlet("/ShowAdminCompanyServlet")
public class ShowAdminCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowAdminCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String condition = request.getParameter("condition");
		if (condition == null || "".equals(condition)) {
			condition = "all";
		}
		CompanyService companyService = new CompanyServiceImpl();
		List<Company> list = null ;
		HttpSession session = request.getSession();
		try {
			list = companyService.queryCompanys(condition);
			session.setAttribute("companys", list);
			request.getRequestDispatcher("/jsp/enterprise-information-management.jsp").forward(request, response);
		} catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
	}

}
