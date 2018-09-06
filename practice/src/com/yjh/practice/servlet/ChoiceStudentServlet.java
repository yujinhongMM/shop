package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.service.impl.ProjectServiceImpl;
import com.yjh.practice.utils.PageUtils;


@WebServlet("/ChoiceStudentServlet")
public class ChoiceStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChoiceStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 对单个学生进行选择、退选
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String stu_no = request.getParameter("stu_no");
		String p_no = request.getParameter("p_no");
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		ProjectServiceImpl projectServiceImpl=new ProjectServiceImpl();
		System.out.println("选择、退选     "+role+"    "+company_username +"   "+projectServiceImpl.findProjectBelongToUserByPNo(company_username, p_no));
		if(role.equals("1")&&projectServiceImpl.findProjectBelongToUserByPNo(company_username, p_no)){
			//角色为企业并对该方案拥有权限
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("choiceProjectInfoPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
			}
			if (type.equals("1")) {
				// 选择学生
				boolean b = projectDaoImpl.chooseStudent(stu_no, p_no);
				//返回之前页面
				request.getRequestDispatcher("ChoicePracticeInfoServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
			} else if (type.equals("2")) {
				// 退选学生
				boolean b=projectDaoImpl.unChooseStudent(new String[]{stu_no}, p_no);
				//返回之前页面
				request.getRequestDispatcher("ChoicePracticeInfoServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
			} else {
				// 访问无效
				//跳转到404页面,并打印错误信息
				String errorMessage = "访问时附带系统指定参数异常！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}else{
			//角色身份不匹配
			//跳转到404页面,并打印错误信息
			String errorMessage = "当前用户无权访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
		
	}

	/**
	 * 批量选择学生
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

