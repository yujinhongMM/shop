package com.shop.service;

import java.util.List;

import com.shop.entity.OrderView;
import com.shop.entity.Orders;
import com.shop.utils.PageUtils;

/**
 * 
 * @author 
 * @category 订单逻辑处理接口
 */
public interface OrderService {
	
	/**
	 *  商家查看交易记录
	 */
	public List<OrderView> findOrderBySellerId(String sellerId,PageUtils pageUtils) throws Exception;
	/**
	 *  买家查看交易记录
	 */
	public List<OrderView> findOrderByBuyerId(String buyerId,PageUtils pageUtils) throws Exception;
	
	/*
	 * 张梓桐
	 */
	//生成当订单 参数：1.商品id数组 2.用户id 
	public List<Orders> createOrder_service(Integer[] Goodsid,String Buyerid) throws Exception;
	//支付订单 参数： 1.用户id 2.用户钱包数 3.订单id数组
	public boolean payOrder_service(String Buyerid,int Wallet,Integer[] orderid) throws Exception;
	//查看所有订单 参数： 1.用户id
	public List<Orders> showOrders_service(String buyerid) throws Exception;
	//查看订单详情 参数 1.订单id 2.商品id 3.用户id
	public OrderView seeorder_service(Integer orderid,Integer goodsid,String buyerid) throws Exception;
}
