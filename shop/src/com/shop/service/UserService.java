package com.shop.service;



import java.util.List;

import com.shop.entity.Buyer;
import com.shop.entity.Manager;
import com.shop.entity.Seller;
import com.shop.entity.Shopcart;

/**
 * 
 * @author 
 * @category 用户业务逻辑处理接口
 */
public interface UserService {
	/**
	 *  买家登录
	 */
	public Buyer loginBuyer(String buyerId,String password) throws Exception;
	/**
	 *  卖家登录
	 */
	public Seller loginSeller(String sellerId,String password) throws Exception;
	/**
	 *  管理员登录
	 */
	public Manager loginManager(String managerId,String password) throws Exception;
	/**
	 *  卖家钱包管理
	 *  如果type=1则就是增加，type=2则就是减少
	 */
	public Boolean sellerManagerWallet(String sellerId,int money,String type)throws Exception;
	/**
	 *  买家钱包管理
	 *  如果type=1则就是增加，type=2则就是减少
	 */
	public Boolean buyerManagerWallet(String buyerId,int money,String type)throws Exception;
	
	
	/*
	 * 代梦勤
	 */
	/**
	 * 修改买家信息
	 */
	public Boolean modifyBuyer(Buyer buyer)throws Exception;
	/**
	 * 修改卖家信息
	 */
	public Boolean modifySeller(Seller seller)throws Exception;
	
	/**
	 * 丁洁琳
	 */
	/**
	 * 增加卖家
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public Boolean addSeller(Seller seller)throws Exception;
	/**
	 * 增加买家
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public Boolean addBuyer(Buyer buyer)throws Exception;
	/**
	 * 判断是否有卖家
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public Boolean isSeller(Seller seller)throws Exception;
	/**
	 * 判断是否有买家
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public Boolean isBuyer(Buyer buyer)throws Exception;
	
	/*
	 * 张梓桐
	 */
	//查看购物车 参数： 1.用户id
	public List<Shopcart> seecart_service(String Buyerid) throws Exception;
	//删除商品 参数 1.商品id数组 2.用户id
	public boolean deleteCartGoods_service(Integer[] Goodsid,String Buyerid) throws Exception;
	//加入购物车 参数： 1.用户id 2.商品id 3.购买数量
	public boolean addCartGoods_service(String Buyerid,Integer goodid,Integer number) throws Exception;
}
