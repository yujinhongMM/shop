package com.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.manager.Sendsms;


/**
 * 
 * Description 发送手机验证码
 * @author YJH
 * @date 2018年6月25日  
 *
 */
@WebServlet("/SendMsServlet")
public class SendMsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SendMsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String phone=request.getParameter("username");
		int type=Integer.valueOf(request.getParameter("type"));
		System.out.println(type);
		Sendsms phonems=new Sendsms();
		int num= phonems.send(phone);
		System.out.println("num="+num);
		request.getSession().setAttribute("phonems",num);
		//request.getSession().setAttribute("phonems",1111);
		request.getSession().setAttribute("phone",phone);
		if(type==1){
			request.getRequestDispatcher("RegisterBuyer.jsp").forward(request, response); 
		}else{
			request.getRequestDispatcher("RegisterSeller.jsp").forward(request, response); 
		}
		
		
	}

	
}
