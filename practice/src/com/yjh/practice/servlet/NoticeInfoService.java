package com.yjh.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.practice.dao.impl.NoticeDaoImpl;
import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.model.NoticeCompany;



@WebServlet("/NoticeInfoService")
public class NoticeInfoService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInfoService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		String id=request.getParameter("id");
		if(type!=null&&id!=null){
			NoticeDaoImpl noticeDaoImpl=new NoticeDaoImpl();
			if(type.equals("1")){
				//学院公告
				NoticeAdmin noticeAdmin=noticeDaoImpl.queryNoticeAdminById(Integer.parseInt(id));
				request.setAttribute("noticeAdmin", noticeAdmin);
				request.setAttribute("notice", noticeAdmin);
			}else if(type.equals("2")){
				//企业公告
				NoticeCompany noticeCompany=noticeDaoImpl.queryNoticeById(Integer.parseInt(id));
				request.setAttribute("noticeCompany", noticeCompany);
				request.setAttribute("notice", noticeCompany);
			}
			request.getRequestDispatcher("/newDetails.jsp").forward(request, response);
		}else{
			//跳转到404页面,并打印错误信息
			String errorMessage = "请求异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

