package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.dao.impl.ProjectDaoImpl;
import com.yjh.practice.dao.impl.StudentDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.model.Project;
import com.yjh.practice.model.ProjectSelect;
import com.yjh.practice.model.Student;
import com.yjh.practice.service.impl.ProjectServiceImpl;



@WebServlet("/StudentSelectPracticeServlet")
public class StudentSelectPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentSelectPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 学生查询可选方案
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stu_no = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");

		if (role.equals("2")) {
			//学生选择方案是否开启
			ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
			request.setAttribute("PracticeIsUnderWay", projectServiceImpl.findPracticeIsUnderWay());
			
			StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
			Student student = studentDaoImpl.findById(stu_no);

			String nowPage = request.getParameter("nowPage");
			if (nowPage == null)
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();

			ArrayList<Project> projects = projectDaoImpl.findAllProject(student.getGrade());

			// 查询学生已选方案
			ArrayList<Project> chosenProject = projectDaoImpl.findAllChosenProject(student.getNo());
			if (chosenProject == null) {
				// 查询学生已选方案失败，无法继续
				//跳转到404页面,并打印错误信息
				String errorMessage = "访问数据库出现异常，无法查询学生已选方案！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			} else {
				//通过方案号保存学生是否选择该方案  1-已选  0-未选
				HashMap<String, Integer> choiceState = new HashMap<>();
				//通过方案号保存方案所属企业对象
				HashMap<String, Company> companyInfo = new HashMap<>();
				CompanyDaoImpl companyDaoImpl=new CompanyDaoImpl();
				for (int i = 0; i < projects.size(); i++) {
					
					Company company=companyDaoImpl.queryByUserName(projects.get(0).getCompanyUsername());
					companyInfo.put(projects.get(i).getNo(), company);
					choiceState.put(projects.get(i).getNo(), 0);
					for (int j = 0; j < chosenProject.size(); j++) {
						if (projects.get(i).getNo().equals(chosenProject.get(j).getNo())) {
							choiceState.put(projects.get(i).getNo(), 1);
							break;
						} else {
							choiceState.put(projects.get(i).getNo(), 0);
						}
					}
				}
				ArrayList<ProjectSelect> projectSelects=projectDaoImpl.findStuProject(stu_no);
				if(projectSelects.size()>0){
					request.setAttribute("stuProjectNo", projectSelects.get(0).getId().getProjectNo().toString());
				}else{
					request.setAttribute("stuProjectNo", "0");
				}
			
				request.setAttribute("selectProjects", projects);
				request.setAttribute("choiceState", choiceState);
				request.setAttribute("companyInfo", companyInfo);
				
				request.getRequestDispatcher("/jsp/studentSelection.jsp").forward(request, response);
			}
		} else {
			// 角色不匹配
			//跳转到404页面,并打印错误信息
			String errorMessage = "用户权限不足！";
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
