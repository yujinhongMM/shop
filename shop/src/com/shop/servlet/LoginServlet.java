package com.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Buyer;
import com.shop.entity.Manager;
import com.shop.entity.Seller;
import com.shop.service.UserService;
import com.shop.service.Impl.UserServiceImpl;
/**
 * 
 * Description
 * @author 丁洁琳
 * @date 2018年6月28日  
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String rmb=request.getParameter("remember");
		String verifyc  = request.getParameter("verifycode");
		String role = request.getParameter("role");
        String svc =(String) request.getSession().getAttribute("sessionverify");  
        
        if(!svc.equalsIgnoreCase(verifyc)){  
        	request.setAttribute("msg", "验证码错误！");  
            request.getRequestDispatcher("/login.jsp").forward(request, response);  
            return;
        }
        Cookie cookie=new Cookie("user", username+"#"+password);
		cookie.setPath("/");// 实现共享
		if(rmb!=null){
			cookie.setMaxAge(600);// 单位是秒
		}else{
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
        UserService userService=new UserServiceImpl();
		// 接收数据
		try {
			// 调用业务逻辑
			if(role.equals("1")){
				Buyer buyer=userService.loginBuyer(username, password);
				
				
				if(buyer!=null){
					request.getSession().setAttribute("buyer", buyer);
					request.getSession().setAttribute("role",role);
					response.getWriter().write("登录成功，1秒后跳转");
					response.setHeader("refresh", "1;url="+request.getContextPath()+"/goodsList.jsp");
				}else{
					request.setAttribute("msg", "用户名或密码错误！");  
		            request.getRequestDispatcher("/login.jsp").forward(request, response);  
		            return;
				}
				
				
			}else if(role.equals("2")){
				Seller seller=userService.loginSeller(username, password);;
				if(seller!=null){
					request.getSession().setAttribute("seller", seller);
					request.getSession().setAttribute("role",role);
					response.getWriter().write("登录成功，1秒后跳转");
					response.setHeader("refresh", "1;url="+request.getContextPath()+"/jsp/seller/seller_mo_info.jsp");
				}else{
					request.setAttribute("msg", "用户名或密码错误！");  
		            request.getRequestDispatcher("/login.jsp").forward(request, response);  
		            return;
				}
			}else if(role.equals("9")){
				Manager manager=userService.loginManager(username, password);;
				if(manager!=null){
					request.getSession().setAttribute("manager", manager);
					request.getSession().setAttribute("role",role);
					response.getWriter().write("登录成功，1秒后跳转");
					request.getRequestDispatcher("AdminSellerListServlet").forward(request, response); 
				}else{
					request.setAttribute("msg", "用户名或密码错误！");  
		            request.getRequestDispatcher("/login.jsp").forward(request, response);  
		            return;
				}
			}else{
				request.setAttribute("msg", "404错误");  
	            request.getRequestDispatcher("/404.jsp").forward(request, response);  
	            return;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
