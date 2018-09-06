package com.yjh.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.SystemParameter;
import com.yjh.practice.service.SystemParameterService;
import com.yjh.practice.service.impl.SystemParameterServiceImpl;



/**
 * Servlet implementation class SelectSystemConfigServlet
 */
@WebServlet("/SelectSystemConfigServlet")
public class SelectSystemConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectSystemConfigServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("进入查看系统配置的Servlet");
    	HttpSession session = request.getSession();
		String user = (String) session.getAttribute("account");
		try {
			//从数据库中获取SystemParameter对象并存进session
			SystemParameterService systemParameterService = new SystemParameterServiceImpl();
			SystemParameter systemParameter = systemParameterService.queryByAccount(user);
			session.setAttribute("sys", systemParameter);
			request.getRequestDispatcher("/jsp/system-parameter.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println("有错");
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return;
		}
	}
}
