package com.shop.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.shop.entity.Goods;
import com.shop.entity.Seller;
import com.shop.service.GoodsService;
import com.shop.service.Impl.GoodsServiceImpl;

/**
 * 
 * Description
 * @author 陈浩雨
 * @date 2018年6月28日  
 *
 */
@WebServlet("/AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddGoodsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		String role =(String)session.getAttribute("role");
		if(!role.equals("2")){
			request.setAttribute("msg", "没有权限访问");  
            request.getRequestDispatcher("/404.jsp").forward(request, response);  
            return;
		}
		Seller seller = (Seller) session.getAttribute("seller");
		String sellerid = seller.getSellerId();
		
		//String sellerid = "1111";// 测试设置的id，实际用上的时候用上面注释了的
		GoodsService goodsService = new GoodsServiceImpl();
		Goods goods = addGoods(request);//获取表单数据
		String action = request.getParameter("action");

		
		if ("add".equals(action)) {
			try {
				goodsService.AddGoods(sellerid, goods);
				response.getWriter().write("添加成功。1秒后跳转");
				response.setHeader("refresh", "1;url=" + request.getServletContext().getContextPath() + "/QueryGoodsServlet?action=find");// 重定向到商品全部列表
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if("update".equals(action)) {
			try {
				int id = Integer.valueOf(request.getParameter("id"));
				System.out.println(id);
				if(goodsService.UpdateGoods(goods,id)) {
					response.getWriter().write("更新成功。1秒后跳转");
					response.setHeader("refresh", "1;url=" + request.getServletContext().getContextPath() + "/QueryGoodsServlet?action=find");// 重定向到商品全部列表
				}
				else {
					response.getWriter().write("修改失败。1秒后跳转");
					response.setHeader("refresh", "1;url=" + request.getServletContext().getContextPath() + "/QueryGoodsServlet?action=find");// 重定向到商品全部列表
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public Goods addGoods(HttpServletRequest request) throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload load = new ServletFileUpload(factory);

		HashMap<String, Object> map = new HashMap<>();

		try {
			List<FileItem> list = load.parseRequest(request);
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");
					map.put(name, value);
				} else {
					String fileName = fileItem.getName();

					if (fileName != null && !"".equals(fileName)) {
						map.put("url",fileName);
						String path = this.getServletContext().getRealPath("img\\");
						OutputStream output = new FileOutputStream(path + File.separator + fileName);
						IOUtils.copy(fileItem.getInputStream(), output);
					}

				}

			}

		} catch (FileUploadException e) {
			e.printStackTrace();

		}
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return goods;

	}

}
