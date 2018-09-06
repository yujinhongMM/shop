package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.service.impl.UserServiceImpl;
/**
 * 
 * Description 登录
 * @author YJH
 * @date 2018年6月2日  
 *
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取页面传入的各种值
		HttpSession session = request.getSession();
		// 页面获得的用户输入的账号信息
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String Verification_Code = request.getParameter("verificationCode");
		//System.out.println(Verification_Code);
		String role = request.getParameter("role");
		//System.out.println(role);
		//System.out.println(account);
		// 页面获得的由后台产生的验证码
		String vchidden =(String) request.getSession().getAttribute("vchidden");
		//System.out.println(vchidden);
		UserServiceImpl usi = new UserServiceImpl();
		if (usi.login(account, password, Verification_Code, role, vchidden)) {
			// 将role放入到session中
			session.setAttribute("role", role);
			// 将用户名放入到session中
			session.setAttribute("account", account);
			// 如果登录成功，跳转到对应页面
			response.sendRedirect("UserInfoServlet");
		} else {
			// 如果登录不成功，跳转到login页面,并打印错误信息
			 request.setAttribute("msg", "登录失败请重新输入！");  
	         request.getRequestDispatcher("/jsp/login.jsp").forward(request, response); 
		}

	}

}
