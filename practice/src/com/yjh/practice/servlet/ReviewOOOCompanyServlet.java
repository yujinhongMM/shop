package com.yjh.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.model.Company;
import com.yjh.practice.service.CompanyService;
import com.yjh.practice.service.impl.CompanyServiceImpl;
import com.yjh.practice.utils.ValidateUtils;



/**
 * Servlet implementation class ReviewOOOCompanyServlet
 */
@WebServlet("/ReviewOOOCompanyServlet")
public class ReviewOOOCompanyServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    
    public ReviewOOOCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//得到传入的userName参数
		String userName = request.getParameter("userName");
		System.out.println(userName+"123");
		//校验
		if (ValidateUtils.validate(userName) || userName == null) {
			System.out.println("有过滤参数");
			//跳转到404页面,并打印错误信息
			String errorMessage = "请求时附带非法字符，拒绝访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		Company company = null ;
		CompanyService companyService = new CompanyServiceImpl();
		try {
			company = companyService.queryByUserName(userName);
			//根据用户名取出的Company对象为空
			if (company == null) {
				System.out.println("对象为空");
				//跳转到404页面,并打印错误信息
				String errorMessage = "访问数据库出现异常，无法获取指定数据！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return;
			}
			else {
				//如果审核日期不为空，那么意味着退审
				if (company.getAuditDate() != null) {
					company.setAuditDate(null);
					if (companyService.backReview(company)) {
						request.getRequestDispatcher("ShowAdminCompanyServlet").forward(request, response);
					}else {
						//跳转到404页面,并打印错误信息
						String errorMessage = "访问数据库出现异常，无法完成请求！";
						request.getSession().setAttribute("ErrorMessage", errorMessage);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
						return ;
					}
				}
				else {
					System.out.println(company.getCompanyName());
					//日期转换
					java.util.Date date = new java.util.Date();
					java.sql.Date sqlDate=new java.sql.Date(date.getTime());
					company.setAuditDate(sqlDate);
					//如果审核成功
					if (companyService.checkCompany(company)) {
						request.getRequestDispatcher("ShowAdminCompanyServlet").forward(request, response);
					}
					else {
						//跳转到404页面,并打印错误信息
						String errorMessage = "访问数据库出现异常，审核失败！";
						request.getSession().setAttribute("ErrorMessage", errorMessage);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
						return ;
					}
				}
			}
		}catch(Exception e) {
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
		}
	}

}
