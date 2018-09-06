package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.CompanyDao;
import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.model.Project;
import com.yjh.practice.service.impl.ProjectServiceImpl;
import com.yjh.practice.utils.PageUtils;


@WebServlet("/SelectPracticeServlet")
public class SelectPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过session里的登录对象的身份(企业、管理员)获取对应方案信息(分页查询)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company_username = (String) request.getSession().getAttribute("account");//账号
		String role = (String) request.getSession().getAttribute("role");//角色
		// role=9+"";
		if (role == null) {
			System.out.println("role 为空");
			//跳转到404页面,并打印错误信息
			String errorMessage = "当前用户权限不足,role 为空！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else if (role.equals("1") || role.equals("9")) {
			String nowPage = request.getParameter("nowPage");//获取当前页数
			if (nowPage == null)
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("selectProjectPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			ArrayList<Project> projects = null;
			
			projects = projectDaoImpl.findAllProject(Integer.parseInt(role), company_username, pageUtils);
			

			// 通过方案号保存方案所属企业对象
			HashMap<String, Company> companyInfo = new HashMap<>();
			CompanyDao companyDaoImpl = new CompanyDaoImpl();
			if (projects != null) {
				for (int i = 0; i < projects.size(); i++) {
					Company company = companyDaoImpl.queryByUserName(projects.get(i).getCompanyUsername());
					companyInfo.put(projects.get(i).getNo(), company);
				}
			}
			
			//管理员是否开启企业添加方案
			ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
			request.setAttribute("AddPracticeIsUnderWay", projectServiceImpl.findAddPracticeIsUnderWay());
			
			request.setAttribute("companyInfo", companyInfo);
			request.getSession().setAttribute("selectProjectPageUtils", pageUtils);

			request.setAttribute("selectProjects", projects);
			request.setAttribute("selectProjectsRole", role);
			request.getRequestDispatcher("/jsp/programManagement.jsp").forward(request, response);
		} else {
			// 学生无法看到
			//跳转到404页面,并打印错误信息
			String errorMessage = "当前用户权限不足！";
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

