package com.yjh.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.StudentDaoImpl;



/**
 * 
 * Description 用户登录后的信息
 * @author YJH
 * @date 2018年6月3日  
 *
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// 获得session中用户的角色
		String role =(String)session.getAttribute("role");
		// 获得session中用户的账户
		String account =(String) session.getAttribute("account");
		if(role.equals("1")){
			CompanyDaoImpl company=new CompanyDaoImpl();
			session.setAttribute("company", company.queryByUserName(account));
			
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}else if(role.equals("2")){
			StudentDaoImpl student=new StudentDaoImpl();
			// 将学生信息放入student
			session.setAttribute("student", student.findById(account));
			//response.sendRedirect("StudentSelectPracticeServlet");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}else if(role.equals("9")){
			
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}
	}

}
