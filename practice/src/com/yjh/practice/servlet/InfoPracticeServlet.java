package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.model.Project;



@WebServlet("/InfoPracticeServlet")
public class InfoPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfoPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");//获取方案Id
		String company_username = (String) request.getSession().getAttribute("account");//获取账号
		String role = (String) request.getSession().getAttribute("role");//获取角色

		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		CompanyDaoImpl companyDaoImpl=new CompanyDaoImpl();
		Project project = projectDaoImpl.findProjectByNo(no);//通过方案id找出该项目
		if (project != null) {//如果该项目存在
			Company company=companyDaoImpl.queryByUserName(project.getCompanyUsername());
			if ((role.equals("1") && project.getCompanyUsername().equals(company_username))||role.equals("9")){
				// 设置当前用户对info的所有权
				request.setAttribute("InfoRole", 1);
			}
			else
				request.setAttribute("InfoRole", 0);
			request.setAttribute("infoProject", project);
			request.setAttribute("infoCompany", company);
			request.getRequestDispatcher("/jsp/infoPractice.jsp").forward(request, response);
		}else{
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
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
