package com.yjh.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.model.Student;
import com.yjh.practice.service.StudentService;
import com.yjh.practice.service.impl.StudentServiceImpl;


@WebServlet("/QueryStudentServlet")
public class QueryStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8"); //设置POST请求编码
		response.setContentType("text/html;charset=UTF-8"); //设置响应内容类型
		
		
		String conValue = request.getParameter("conValue"); //获取条件值
		if(conValue==null)
			conValue="";		
		int index=0;
		List<Student> list=null;
		//Student stu=null;
		StudentService ss=new StudentServiceImpl();
		
			list=ss.findAll();
					
		//测试代码begin
		if(list!=null){
			for(Student st:list){
				System.out.println("No:"+st.getNo());
				System.out.println("Name:"+st.getName());				
			}
		}
		
		System.out.println("index="+index);
		System.out.println("value="+conValue);
		//测试代码end
		request.setAttribute("student", list);
		request.setAttribute("index", index);
		request.setAttribute("conValue", conValue);
		System.out.println(request.getAttribute("conValue"));
		request.getRequestDispatcher("/jsp/student-management.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

