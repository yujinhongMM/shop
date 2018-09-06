package com.shop.service.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shop.dao.OrderDao;
import com.shop.dao.Impl.OrderDaoImpl;
import com.shop.entity.OrderView;
import com.shop.entity.Orders;
import com.shop.entity.Shopcart;
import com.shop.service.OrderService;
import com.shop.utils.DBUtils;
import com.shop.utils.PageUtils;

/**
 * 
 * @author 
 * @category 订单逻辑处理接口实现
 */
public class OrderServiceImpl implements OrderService{

	@Override
	public List<OrderView> findOrderBySellerId(String sellerId,PageUtils pageUtils) throws Exception {
		OrderDao orderDao=new OrderDaoImpl();
		return orderDao.findOrderBySellerId(sellerId,pageUtils);
	}

	@Override
	public List<OrderView> findOrderByBuyerId(String buyerId,PageUtils pageUtils) throws Exception {
		OrderDao orderDao=new OrderDaoImpl();
		return orderDao.findOrderByBuyerId(buyerId,pageUtils);
	}
	
	/*
	 * 张梓桐
	 */
	
	//生成订单
		@Override
		public List<Orders> createOrder_service(Integer[] Goodsid,String Buyerid) throws Exception {
			// TODO Auto-generated method stub
			//数据接受与处理
			//1.时间的接受
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=df.format(new Date());
			//2.List<Shopcart> Shopcart的接受
			List<Shopcart> shopcartlist=new ArrayList<Shopcart>();
			Shopcart shopcart=null;
			String buyerid= Buyerid;
			Integer[] goodsid= Goodsid;
			Connection conn=DBUtils.getConnection();
			Statement st=conn.createStatement();
			ResultSet  rs=null;
			for (Integer goodsidlist : goodsid) {
				shopcart=new Shopcart();
				String sql="select number from shopcart where buyerId='"+buyerid+"' and goodId="+goodsidlist+"";
				rs=st.executeQuery(sql);
				if(rs.next()){
					shopcart.setBuyerid(buyerid);
					shopcart.setGoodsid(goodsidlist);
					shopcart.setNumber(rs.getInt(1));
				}
				String sql2="select price from goods where goodId="+goodsidlist+"";
				rs=st.executeQuery(sql2);
				if(rs.next()){
					shopcart.setPrice(rs.getInt(1));
				}
				shopcartlist.add(shopcart);
			}
			//调用DAO层方法操作操作
			OrderDao orderdao=new OrderDaoImpl();
			System.out.println("时间"+date);
			System.out.println(shopcartlist);
			List<Orders> orderlist =orderdao.createOrder(date, shopcartlist);
			for (Orders orders : orderlist) {
				System.out.println("输出订单号");
				System.out.println(orders.getOrderid());
			}
			return orderlist;
		}

		//支付订单
		@Override
		public boolean payOrder_service(String Buyerid, int Wallet, Integer[] orderid) throws Exception {
			// TODO Auto-generated method stub
			OrderDao orderdao=new OrderDaoImpl();
			return 	orderdao.payOrder(Buyerid, Wallet, orderid);
		}

		//查看所有订单
		@Override
		public List<Orders> showOrders_service(String buyerid) throws Exception {
			// TODO Auto-generated method stub
			OrderDao orderdao=new OrderDaoImpl();
			List<Orders> orderlist=orderdao.showOrders(buyerid);	
			return orderlist;
		}

		//查看订单详情
		@Override
		public OrderView seeorder_service(Integer orderid, Integer goodsid, String buyerid) throws Exception {
			// TODO Auto-generated method stub
			OrderDao orderdao=new OrderDaoImpl();
			OrderView orderView=orderdao.seeorder(orderid, goodsid, buyerid);
			return orderView;
		}

}
