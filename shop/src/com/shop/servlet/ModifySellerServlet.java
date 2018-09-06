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

import com.shop.entity.Seller;
import com.shop.service.UserService;
import com.shop.service.Impl.UserServiceImpl;
import com.shop.utils.MdPwdUtil;

/**
 * Servlet implementation class ModifySellerServlet
 */
@WebServlet("/ModifySellerServlet")
public class ModifySellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifySellerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String role =(String)session.getAttribute("role");
		UserService userService=new UserServiceImpl();
		if(!role.equals("2")){
			request.setAttribute("msg", "没有权限访问");  
            request.getRequestDispatcher("/404.jsp").forward(request, response);  
            return;
		}
		Seller seller =(Seller)session.getAttribute("seller");
		String type = request.getParameter("type");
		System.out.println(type.equals("1"));
		if(type.equals("1")){
			String birthday=request.getParameter("birthday");
			//字符串转日期
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
			try {
				System.out.println(sdf1.parse(birthday));
				seller.setBirthday(sdf1.parse(birthday));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			seller.setShopname(request.getParameter("shopname"));
			seller.setEmail(request.getParameter("email"));
			try {
				if(userService.modifySeller(seller)){
					
					session.setAttribute("buyer", seller);
					request.getRequestDispatcher("jsp/seller/seller_mo_info.jsp").forward(request, response);  
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
			if(seller.getPassword().equals(MdPwdUtil.MD5Password(oldpassword))){
				seller.setPassword(MdPwdUtil.MD5Password(newpassword));
				try {
					if(userService.modifySeller(seller)){
						session.setAttribute("seller", seller);
						request.getRequestDispatcher("jsp/seller/seller_mo_info.jsp").forward(request, response);  
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
