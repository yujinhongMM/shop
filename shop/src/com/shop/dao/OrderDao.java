package com.shop.dao;

import java.util.List;

import com.shop.entity.OrderView;
import com.shop.entity.Orders;
import com.shop.entity.Shopcart;
import com.shop.utils.PageUtils;

/**
 * 
 * @author 
 * @category 订单数据库访问接口
 */
public interface OrderDao {
	
	/**
	 *  通过商家Id查找订单详细信息
	 */
	public List<OrderView> findOrderBySellerId(String sellerId,PageUtils pageUtils) throws Exception;
	
	/**
	 *  通过买家Id查找订单详细信息
	 */
	public List<OrderView> findOrderByBuyerId(String buyerId,PageUtils pageUtils) throws Exception;
	/**
	 *  查询卖家订单数量
	 */
	public int OrderSellerIdCount(String sellerId) throws Exception;
	/**
	 *  查询买家订单数量
	 */
	public int OrderBuyerIdCount(String buyerId) throws Exception;
	/**
	 * 张梓桐
	 */
	

	//生成当订单 返回订单Orders集合
	public List<Orders> createOrder(String Date,List<Shopcart> Shopcart) throws Exception;
	//支付订单 
	public boolean payOrder(String Buyerid,int Wallet,Integer[] orderid) throws Exception;
	//查看所有订单 返回订单Orders集合
	public List<Orders> showOrders(String buyerid) throws Exception;
	//查看订单详情  
	public OrderView seeorder(Integer orderid,Integer goodsid,String buyerid) throws Exception;
}
