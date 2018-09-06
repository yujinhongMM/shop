package com.shop.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.entity.Buyer;
import com.shop.service.UserService;
import com.shop.service.Impl.UserServiceImpl;
import com.shop.utils.MdPwdUtil;

/**
 * 代梦勤 修改买家信息
 */
@WebServlet("/ModifyBuyerServlet")
public class ModifyBuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyBuyerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String role =(String)session.getAttribute("role");
		UserService userService=new UserServiceImpl();
		if(!role.equals("1")){
			request.setAttribute("msg", "没有权限访问");  
            request.getRequestDispatcher("/404.jsp").forward(request, response);  
            return;
		}
		Buyer buyer =(Buyer)session.getAttribute("buyer");
		String type = request.getParameter("type");
		System.out.println(type.equals("1"));
		if(type.equals("1")){
			String birthday=request.getParameter("birthday");
			buyer.setAddress(request.getParameter("address"));
			//字符串转日期
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
			try {
				System.out.println(sdf1.parse(birthday));
				buyer.setBirthday(sdf1.parse(birthday));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			buyer.setNickname(request.getParameter("nickname"));
			buyer.setEmail(request.getParameter("email"));
			try {
				if(userService.modifyBuyer(buyer)){
					
					session.setAttribute("buyer", buyer);
					request.getRequestDispatcher("jsp/buyer/buyer_mo_info.jsp").forward(request, response);  
				}else{
					request.setAttribute("msg", "修改失败");  
		            request.getRequestDispatcher("/404.jsp").forward(request, response);  
		            return;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(type.equals("2")){
			String oldpassword=request.getParameter("oldpassword");
			String newpassword=request.getParameter("newpassword");
			if(buyer.getPassword().equals(MdPwdUtil.MD5Password(oldpassword))){
				buyer.setPassword( MdPwdUtil.MD5Password(newpassword));
				try {
					if(userService.modifyBuyer(buyer)){
						session.setAttribute("buyer", buyer);
						request.getRequestDispatcher("jsp/buyer/buyer_mo_info.jsp").forward(request, response);  
					}else{
						request.setAttribute("msg", "修改失败");  
			            request.getRequestDispatcher("/404.jsp").forward(request, response);  
			            return;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				request.setAttribute("msg", "旧密码错误");  
	            request.getRequestDispatcher("/404.jsp").forward(request, response);  
	            return;
			}
		}else{
			request.setAttribute("msg", "没有权限访问");  
            request.getRequestDispatcher("/404.jsp").forward(request, response);  
            return;
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
