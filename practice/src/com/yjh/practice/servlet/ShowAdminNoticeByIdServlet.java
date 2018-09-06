package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.service.NoticeService;
import com.yjh.practice.service.impl.NoticeServiceImpl;
import com.yjh.practice.utils.ValidateUtils;


@WebServlet("/ShowAdminNoticeByIdServlet")
public class ShowAdminNoticeByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowAdminNoticeByIdServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post解码方式
		request.setCharacterEncoding("UTF-8");
		String Id = request.getParameter("Id");
		HttpSession session = request.getSession();
		//参数校验，如果有非法字符，那么就跳转到404
		if (ValidateUtils.validate(Id) || Id == null) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "请求时附带非法字符，访问被拒绝！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		try {
			Integer adminNoticeId = Integer.parseInt(Id);
			NoticeService noticeService = new NoticeServiceImpl();
			NoticeAdmin noticeCompany = noticeService.queryNoticeAdminById(adminNoticeId);
			session.setAttribute("adminTice", noticeCompany);
			request.getRequestDispatcher("/jsp/adminAnnuncement-modify.jsp").forward(request, response);
		}catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
		
	}

}
