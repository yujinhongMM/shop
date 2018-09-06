package com.shop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.Seller;
import com.shop.service.ManagerService;
import com.shop.service.Impl.ManagerServiceImpl;
import com.shop.utils.PageUtils;

/**
 * 
 * Description 全部卖家列表
 * @author YJH
 * @date 2018年6月28日  
 *
 */
@WebServlet("/AdminSellerListServlet")
public class AdminSellerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminSellerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role =(String)request.getSession().getAttribute("role");
		String nowPage = request.getParameter("nowPage");
		System.out.println(role);
		if(role.equals("9")){
			if (nowPage == null)	// 未得到请求的页数，默认为1
				nowPage = 1 + "";
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("AdminSellerPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(5);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			ManagerService managerService=new ManagerServiceImpl();
			List<Seller> sellerlist=new ArrayList<Seller>();
			try {
				sellerlist = managerService.lookAllSeller(pageUtils);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sellerlist!=null){
				request.getSession().setAttribute("sellerlist",sellerlist);
				request.getSession().setAttribute("AdminSellerPageUtils", pageUtils);
			}
			request.getRequestDispatcher("/jsp/admin/index.jsp").forward(request, response); 
		}else{
			request.setAttribute("msg", "没有权限访问");  
            request.getRequestDispatcher("/404.jsp").forward(request, response);  
            return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
