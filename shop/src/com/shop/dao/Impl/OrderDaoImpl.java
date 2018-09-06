package com.shop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.shop.dao.OrderDao;
import com.shop.entity.Buyer;
import com.shop.entity.Goods;
import com.shop.entity.Orders;
import com.shop.entity.OrderView;
import com.shop.entity.Seller;
import com.shop.entity.Shopcart;
import com.shop.utils.DBUtils;
import com.shop.utils.PageUtils;

/**
 * 
 * @author 
 * @category 订单数据库访问接口实现类
 */
public class OrderDaoImpl implements OrderDao{

	

	@Override
	public List<OrderView> findOrderBySellerId(String sellerId,PageUtils pageUtils) throws Exception {
		
		int totalSize = 0;
		totalSize=OrderSellerIdCount(sellerId);
		pageUtils.setTotalSize(totalSize);
		if (totalSize <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<OrderView> orderViewList=new ArrayList<OrderView>();
		
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("SELECT * FROM goods,orders,buyer,seller WHERE orders.goodId=goods.goodId AND goods.sellerId=? And orders.buyerId=buyer.buyerId And seller.sellerId=? LIMIT ?,?");
			//ps=conn.prepareStatement("SELECT * FROM goods,orders,buyer,seller WHERE orders.goodId=goods.goodId AND goods.sellerId=? And orders.buyerId=buyer.buyerId And seller.sellerId=?");
			//设置参数
			ps.setString(1, sellerId);
			ps.setString(2, sellerId);
			ps.setInt(3, start);
			ps.setInt(4, size);
			rs=ps.executeQuery();
			
				while(rs.next()){
					
					OrderView orderView=new OrderView();
					Buyer buyer=new Buyer();
					Seller seller=new Seller();
					Orders order=new Orders();
					Goods goods=new Goods();
					
					order.setOrderid(rs.getInt("orderid"));
					order.setAllmoney(rs.getInt("allmoney"));
					order.setBuyerid(rs.getString("buyerid"));
					order.setCreatedate(rs.getString("createdate"));
					order.setNumber(rs.getInt("orders.number"));
					order.setStatus(rs.getString("status"));
					order.setGoodid(rs.getInt("goodid"));
					
					orderView.setOrder(order);
					
					buyer.setBuyerId(rs.getString("buyerId"));
					buyer.setPassword(rs.getString("buyer.password"));
					buyer.setEmail(rs.getString("buyer.email"));
					buyer.setBirthday(rs.getDate("buyer.birthday"));
					buyer.setAddress(rs.getString("address"));
					buyer.setNickname(rs.getString("nickname"));
					buyer.setWallet(rs.getInt("buyer.wallet"));
					
					orderView.setBuyer(buyer);
					
					goods.setGoodId(rs.getInt("goodId"));
					goods.setName(rs.getString("name"));
					goods.setCity(rs.getString("city"));
					goods.setPrice(rs.getInt("price"));
					goods.setNumber(rs.getInt("goods.number"));
					goods.setUrl(rs.getString("url"));
					goods.setSellerId(rs.getString("sellerId"));
					
					orderView.setGoods(goods);
	
					seller.setSellerId(rs.getString("sellerId"));
					seller.setPassword(rs.getString("seller.password"));
					seller.setEmail(rs.getString("seller.email"));
					seller.setBirthday(rs.getDate("seller.birthday"));
					seller.setShopname(rs.getString("shopname"));
					seller.setWallet(rs.getInt("seller.wallet"));
					seller.setPower(rs.getInt("power"));
					
					orderView.setSeller(seller);
					
					orderViewList.add(orderView);	
			}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(orderViewList);
		return orderViewList;
	}

	@Override
	public List<OrderView> findOrderByBuyerId(String buyerId,PageUtils pageUtils) throws Exception {
		
		
		int totalSize = 0;
		totalSize=OrderBuyerIdCount(buyerId);
		pageUtils.setTotalSize(totalSize);
		if (totalSize <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<OrderView> orderViewList=new ArrayList<OrderView>();
		
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("SELECT * FROM goods,orders,buyer,seller WHERE orders.goodId=goods.goodId AND buyer.buyerId=? And orders.buyerId=? And seller.sellerId=goods.sellerId LIMIT ?,?");
			//ps=conn.prepareStatement("SELECT * FROM goods,orders,buyer,seller WHERE orders.goodId=goods.goodId AND buyer.buyerId=? And orders.buyerId=? And seller.sellerId=goods.sellerId");
			//设置参数
			ps.setString(1, buyerId);
			ps.setString(2, buyerId);
			ps.setInt(3, start);
			ps.setInt(4, size);
			rs=ps.executeQuery();
			
				while(rs.next()){
					
					OrderView orderView=new OrderView();
					Buyer buyer=new Buyer();
					Seller seller=new Seller();
					Orders order=new Orders();
					Goods goods=new Goods();
					
					order.setOrderid(rs.getInt("orderid"));
					order.setAllmoney(rs.getInt("allmoney"));
					order.setBuyerid(rs.getString("buyerid"));
					order.setCreatedate(rs.getString("createdate"));
					order.setNumber(rs.getInt("orders.number"));
					order.setStatus(rs.getString("status"));
					order.setGoodid(rs.getInt("goodid"));
					
					orderView.setOrder(order);
					
					buyer.setBuyerId(rs.getString("buyerId"));
					buyer.setPassword(rs.getString("buyer.password"));
					buyer.setEmail(rs.getString("buyer.email"));
					buyer.setBirthday(rs.getDate("buyer.birthday"));
					buyer.setAddress(rs.getString("address"));
					buyer.setNickname(rs.getString("nickname"));
					buyer.setWallet(rs.getInt("buyer.wallet"));
					
					orderView.setBuyer(buyer);
					
					goods.setGoodId(rs.getInt("goodId"));
					goods.setName(rs.getString("name"));
					goods.setCity(rs.getString("city"));
					goods.setPrice(rs.getInt("price"));
					goods.setNumber(rs.getInt("goods.number"));
					goods.setUrl(rs.getString("url"));
					goods.setSellerId(rs.getString("sellerId"));
					
					orderView.setGoods(goods);
	
					seller.setSellerId(rs.getString("sellerId"));
					seller.setPassword(rs.getString("seller.password"));
					seller.setEmail(rs.getString("seller.email"));
					seller.setBirthday(rs.getDate("seller.birthday"));
					seller.setShopname(rs.getString("shopname"));
					seller.setWallet(rs.getInt("seller.wallet"));
					seller.setPower(rs.getInt("power"));
					
					orderView.setSeller(seller);
					
					orderViewList.add(orderView);	
			}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(orderViewList);
		return orderViewList;
	}

	@Override
	public int OrderSellerIdCount(String sellerId) throws Exception {
		// 查询总数的sql语句
		String sql = "SELECT COUNT(*) m FROM goods,orders,buyer,seller WHERE orders.goodId=goods.goodId AND goods.sellerId=? And orders.buyerId=buyer.buyerId And seller.sellerId=?";
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int m = 0;
		try {
			conn=DBUtils.getConnection();
			
			// 查询方案总数
			ps = conn.prepareStatement(sql);
			//设置参数
			ps.setString(1, sellerId);
			ps.setString(2, sellerId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				m = rs.getInt("m");
			}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(m);
		return m;
	}

	@Override
	public int OrderBuyerIdCount(String buyerId) throws Exception {
		//查询总数的sql语句
		String sql = "SELECT COUNT(*) m FROM goods,orders,buyer,seller WHERE orders.goodId=goods.goodId AND buyer.buyerId=? And orders.buyerId=? And seller.sellerId=goods.sellerId";
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int m = 0;
		try {
			conn=DBUtils.getConnection();
			
			// 查询方案总数
			ps = conn.prepareStatement(sql);
			//设置参数
			ps.setString(1, buyerId);
			ps.setString(2, buyerId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				m = rs.getInt("m");
			}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(m);
		return m;
	}
	
	
	/*
	 * 张梓桐
	 */
	// 生成订单
		@Override
		public List<Orders> createOrder(String Date, List<Shopcart> Shopcart) throws Exception {
			// TODO Auto-generated method stub
			// 接受数据

			List<Shopcart> shopcart = new ArrayList<Shopcart>();
			List<Orders> orders = new ArrayList<Orders>();
			shopcart = Shopcart;

			Orders aorder = null;
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String date=df.format(new Date());
			//java.sql.Date date = Date;
			String status = "未支付";
			Connection conn = DBUtils.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs=null;
			for (Shopcart shopcart2 : shopcart) {
				aorder = new Orders();
				aorder.setCreatedate(Date);
				aorder.setStatus(status);
				aorder.setBuyerid(shopcart2.getBuyerid());
				aorder.setGoodid(shopcart2.getGoodsid());
				aorder.setNumber(shopcart2.getNumber());
				System.out.println(shopcart2.getNumber() +"||"+ shopcart2.getPrice());
				aorder.setAllmoney(shopcart2.getNumber() * shopcart2.getPrice());
				System.out.println("数量，单价"+shopcart2.getNumber() + shopcart2.getPrice());
	System.out.println("总价"+aorder.getAllmoney());
				orders.add(aorder);
			}
			for (Orders orders2 : orders) {

				String sql = "insert into orders(createdate,status,buyerId,goodId,number,allmoney) values('"
						+ orders2.getCreatedate() + "','" + orders2.getStatus() + "','" + orders2.getBuyerid() + "',"
						+ orders2.getGoodid() + "," + orders2.getNumber() + "," + orders2.getAllmoney() + ")";
				st.executeUpdate(sql);
				String sql2 = "select MAX(orderId) from orders";
				rs=st.executeQuery(sql2);
				if(rs.next()){
					orders2.setOrderid(rs.getInt(1));
				}
			}
			DBUtils.closeAll(null, st, conn);
			return orders;
		}

		// 支付订单
		@Override
		public boolean payOrder(String Buyerid, int Wallet, Integer[] Orderid) throws Exception {
			// TODO Auto-generated method stub
			List<Orders> orders = new ArrayList<Orders>();
			Connection conn = DBUtils.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = null;
			Orders order = null;
			int Allmoney = 0;

			// 接受数据
			Integer[] orderid = Orderid;
			String buyerid = Buyerid;
			int wallet = Wallet;
			String status="已支付";
			for (Integer orderids : orderid) {

				String sql = "select * from orders where orderId='" + orderids + "'";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					order = new Orders();
					order.setOrderid(rs.getInt(1));
					order.setCreatedate(rs.getString(2));
					order.setStatus(rs.getString(3));
					order.setBuyerid(rs.getString(4));
					order.setGoodid(rs.getInt(5));
					order.setNumber(rs.getInt(6));
					order.setAllmoney(rs.getInt(7));
					orders.add(order);	
				}
				
				
				 //改变订单状态
				 String sql3="update orders set status='" + status + "' where orderId=" + orderids + "";
				 st.executeUpdate(sql3);
				
			}
			for (Orders aorder : orders) {
				System.out.println(aorder.getAllmoney());
				Allmoney = Allmoney + aorder.getAllmoney();
				
			}
			System.out.println("总金额"+Allmoney);
			// 执行扣钱
			String sql2 = "update buyer set wallet=" + (wallet-Allmoney) + " where buyerId='" + buyerid + "'";
			 st.executeUpdate(sql2);
			 

			 
			DBUtils.closeAll(rs, st, conn);
			return true;
		}

		// 查看所有订单
		@Override
		public List<Orders> showOrders(String buyerid) throws Exception {
			// TODO Auto-generated method stub
			Connection conn = DBUtils.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = null;
			Orders order = null;
			List<Orders> ordersall = new ArrayList<Orders>();
			String sql = "select * from orders where buyerId='" + buyerid + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				order = new Orders();
				order.setOrderid(rs.getInt(1));
				order.setCreatedate(rs.getString(2));
				order.setStatus(rs.getString(3));
				order.setBuyerid(rs.getString(4));
				order.setGoodid(rs.getInt(5));
				order.setNumber(rs.getInt(6));
				order.setAllmoney(rs.getInt(7));
				ordersall.add(order);
			}
			DBUtils.closeAll(rs, st, conn);
			return ordersall;
		}

		// 查看订单详情
		@Override
		public OrderView seeorder(Integer Orderid, Integer Goodsid, String Buyerid) throws Exception {
			// TODO Auto-generated method stub
			OrderView orderView = null;
			Orders order = null;
			Goods goods = null;
			Buyer buyer = null;
			Seller seller = null;

			Connection conn = DBUtils.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = null;

			// 接受数据
			Integer orderid = Orderid;
			Integer goodsid = Goodsid;
			String buyerid = Buyerid;

			// 进行设值
			//获取订单实体
			String sql = "select * from orders where orderId=" + orderid + "";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				order=new Orders();
				System.out.println("1");
				System.out.println("shuchu："+rs.getInt(1));
				order.setOrderid(rs.getInt(1));
				System.out.println(rs.getString(2));
				order.setCreatedate(rs.getString(2));
				System.out.println("2");
				order.setStatus(rs.getString(3));
				order.setBuyerid(rs.getString(4));
				order.setGoodid(rs.getInt(5));
				order.setNumber(rs.getInt(6));
				order.setAllmoney(rs.getInt(7));
			}
			//获取商品实体
			System.out.println("goodsid"+goodsid);
			String sql2 = "select * from goods where goodId=" + goodsid + "";
			rs = st.executeQuery(sql2);
			System.out.println("3");
			while (rs.next()) {
				goods=new Goods();
				goods.setGoodId(rs.getInt(1));
				goods.setName(rs.getString(2));
				goods.setCity(rs.getString(3));
				goods.setPrice(rs.getInt(4));
				goods.setNumber(rs.getInt(5));
				goods.setSellerId(rs.getString(6));
				goods.setUrl(rs.getString(7));
			}
			//获取买家实体
			String sql3 = "select * from buyer where buyerId='" + buyerid + "'";
			rs = st.executeQuery(sql3);
			while(rs.next()){
				buyer=new Buyer();
				buyer.setBuyerId(rs.getString(1));
				buyer.setPassword(rs.getString(2));
				buyer.setNickname(rs.getString(3));
				buyer.setEmail(rs.getString(4));
				buyer.setBirthday(rs.getDate(5));
				buyer.setWallet(rs.getInt(6));
				buyer.setAddress(rs.getString(7));
			}
			//获取卖家实体
			String sql4 = "select * from seller where sellerId='" + goods.getSellerId() + "'";
			rs = st.executeQuery(sql4);
			while(rs.next()){
				seller=new Seller();
				seller.setSellerId(rs.getString(1));
				seller.setPassword(rs.getString(2));
				seller.setShopname(rs.getString(3));
				seller.setEmail(rs.getString(4));
				seller.setBirthday(rs.getDate(5));
				seller.setPower(rs.getInt(6));
				seller.setWallet(rs.getInt(7));
			}
			orderView=new OrderView(order, goods, buyer, seller);
			System.out.println("所有对象设值成功");
			return orderView;
		}

		
		
}
