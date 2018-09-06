package com.yjh.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.practice.model.Company;

import com.yjh.practice.service.CompanyService;

import com.yjh.practice.service.impl.CompanyServiceImpl;

import com.yjh.practice.utils.MdPwdUtil;
import com.yjh.practice.utils.ValidateUtils;



@WebServlet("/UpdateCompanyInfo")
public class UpdateCompanyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateCompanyInfoServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post解码方式
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Company company = (Company) session.getAttribute("company");
		String profile = request.getParameter("profile");
		String contacts = request.getParameter("contacts");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		//获得输入的旧密码
		String oldpwd = request.getParameter("oldpwd");
		//获得输入的新密码
		String newpwd = request.getParameter("newpwd");
		
		CompanyService com=new CompanyServiceImpl();
		//修改密码
		if(oldpwd!=null && newpwd!=null){
			if(oldpwd.trim()!=""){ 
				System.out.println("old:"+oldpwd);
				System.out.println(company);
				
				
				if(type.equals("1")){
					
				}else{
					oldpwd=MdPwdUtil.MD5Password(oldpwd);//MD5加密
				}
				
				if(oldpwd.equals(company.getPassword())){
					System.out.println("oldMd5:"+oldpwd);
					company.setPassword(MdPwdUtil.MD5Password(newpwd));
					com.updateCompanyPassword(company.getUsername(),MdPwdUtil.MD5Password(newpwd));
					PrintWriter out = response.getWriter();
					out.print("<script> alert(\"修改密码成功!\"); </script>");
				}else{
					response.getWriter().print("<script> alert('旧密码输入不正确！');</script>");
				}
				
				if(type.equals("1")){
					request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("ShowCompanyServlet").forward(request, response);
				}
				
				return;
			}
		}
		
		if (ValidateUtils.validate(profile) || ValidateUtils.validate(contacts)
				|| ValidateUtils.validate(address) || ValidateUtils.validate(phone)) {
			System.out.println("有可疑参数");
			//跳转到404页面,并打印错误信息
			String errorMessage = "请求附带可疑字符，访问被拒绝！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		company.setAddress(address);
		company.setContacts(contacts);
		company.setPhone(phone);
		company.setProfile(profile);
		company.setAuditDate(null);
		CompanyService companyService = new CompanyServiceImpl();
		try {
			if (companyService.updateCompanyInfo(company)) {
				request.getRequestDispatcher("ShowCompanyServlet").forward(request, response);
				return;
			}
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}catch(Exception e) {
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
	}

}
