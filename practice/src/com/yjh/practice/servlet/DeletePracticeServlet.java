package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.service.impl.ProjectServiceImpl;

/**
 * 
 * Description
 * @author YJH
 * @date 2018年6月5日  
 *
 */


@WebServlet("/DeletePracticeServlet")
public class DeletePracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 删除实训方案
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");

		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if ((role.equals("1") && projectServiceImpl.findProjectBelongToUserByPNo(company_username, no))
				|| role.equals("9")) {
			// 角色为企业并对该方案拥有权限
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			//boolean b = projectDaoImpl.deleteProject(no);
			 projectDaoImpl.deleteProject(no);
			request.getRequestDispatcher("SelectPracticeServlet").forward(request, response);
		} else {
			// 如果登录不成功，跳转到404页面,并打印错误信息
			String errorMessage = "当前用户无权访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
