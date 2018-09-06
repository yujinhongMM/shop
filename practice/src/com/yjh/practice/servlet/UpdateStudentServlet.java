package com.yjh.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.Student;
import com.yjh.practice.service.StudentService;
import com.yjh.practice.service.impl.StudentServiceImpl;
import com.yjh.practice.utils.MdPwdUtil;



@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //设置POST请求编码
		response.setContentType("text/html;charset=UTF-8"); //设置响应内容类型
		
		HttpSession session = request.getSession();
		String account=(String)session.getAttribute("account");
		Boolean hasChange=false;
		
		
		//获得学科背景
		String background = request.getParameter("background");
		//获得学习经历
		String experience = request.getParameter("experience");
		//获得研究方向
		String direction = request.getParameter("direction");
		//获得输入的旧密码
		String oldpwd = request.getParameter("oldpwd");
		//获得输入的新密码
		String newpwd = request.getParameter("newpwd");

		
		
		Student stu=null;
		StudentService ss=new StudentServiceImpl();
		stu=ss.findById(account);
		
		if(stu!=null){			
		
			if(background!=null){ 
				stu.setSubjectBackground(background);
				stu.setLearningExperience(experience);
				stu.setResearchDirection(direction);
				hasChange=true;
			}
			if(hasChange) //如果有修改，就更新数据库
				ss.update(stu);
		}
		//修改密码
		if(oldpwd!=null && newpwd!=null){
			if(oldpwd.trim()!=""){ 
				System.out.println("old:"+oldpwd);
				oldpwd=MdPwdUtil.MD5Password(oldpwd);//MD5加密
				if(oldpwd.equals(stu.getPassword())){
					System.out.println("oldMd5:"+oldpwd);
					stu.setPassword(MdPwdUtil.MD5Password(newpwd));
					ss.update(stu);
					PrintWriter out = response.getWriter();
					out.print("<script> alert(\"修改密码成功!\"); </script>");
				}else{
					response.getWriter().print("<script> alert('旧密码输入不正确！');</script>");
				}
			}
		}
		request.setAttribute("student", stu);
		request.getRequestDispatcher("/jsp/studentInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
