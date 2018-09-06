package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.model.NoticeCompany;
import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;



@WebServlet("/ShowIndexNoticeListServlet")
public class ShowIndexNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowIndexNoticeListServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入Servlet");
		NoticeService noticeService = new NoticeServiceImpl();
		String pageNows = request.getParameter("pageNow");
		if (pageNows == null) {
			pageNows = "1";
		}
		int pageNow = Integer.parseInt(pageNows);
		int pageSize = 10;
		HttpSession session = request.getSession();
		try {
			List<NoticeCompany> list = noticeService.queryAllCompanyNoticeOrderByDate(pageNow, pageSize);
			List<NoticeAdmin> list2 = noticeService.queryAllAdminNoticeOrderByDate(pageNow, pageSize);
			session.setAttribute("companyNotices", list);
			session.setAttribute("adminyNotices", list2);
			session.setAttribute("info", "1");
			System.out.println(list.size());
			System.out.println(list2.size());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}
