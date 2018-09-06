package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;
import com.yjh.practice.utils.ValidateUtils;


@WebServlet("/ReviewNoticeServlet")
public class ReviewNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ReviewNoticeServlet() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post解码方式
		request.setCharacterEncoding("UTF-8");
		String Id = request.getParameter("Id");
		//参数校验，如果有非法字符，那么就跳转到404
		if (ValidateUtils.validate(Id) || Id == null) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "请求时附带非法字符，拒绝访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		try {
			Integer companyNoticeId = Integer.parseInt(Id);
			NoticeService noticeService = new NoticeServiceImpl();
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			if (noticeService.reviewCompanyNotice(companyNoticeId, sqlDate)) {
				request.getRequestDispatcher("AdminLookNoticesServlet").forward(request, response);	
			}
			else {
				//跳转到404页面,并打印错误信息
				String errorMessage = "访问数据库出现异常，请求无法完成";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}
