package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.NoticeCompany;
import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;
import com.yjh.practice.utils.PageUtils;


/**
 * Servlet implementation class ShowNoticeListServlet
 */
@WebServlet("/ShowNoticeListServlet")
public class ShowNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowNoticeListServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeService noticeService = new NoticeServiceImpl();
		String pageNows = request.getParameter("pageNow");
		if (pageNows == null) {
			pageNows = "1";
		}
		int pageNow = Integer.parseInt(pageNows);
		int pageSize = 5;
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		try {
			int totalSize = noticeService.queryAllByName(account);
			PageUtils pager = new PageUtils(pageNow, totalSize);
			pager.setPageSize(pageSize);
			List<NoticeCompany> list = noticeService.queryNoticeByCompanyName(account,pageNow,pageSize);
			session.setAttribute("notices", list);
			session.setAttribute("pager", pager);
			System.out.println(list.size());
			request.getRequestDispatcher("/jsp/make-announcements.jsp").forward(request, response);
		}catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}

