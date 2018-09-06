package com.shop.dao;

import java.util.List;

import com.shop.entity.Seller;
import com.shop.utils.PageUtils;

/**
 * 
 * @author YJH
 * @category 卖家数据库访问接口
 */
public interface SellerDao {
	/**
	 *  用户名和密码根据查找卖家
	 */
	public Seller findUserByNameAndPassword(String sellerId, String password) throws Exception;
	/**
	 *  为钱包充值
	 */
	public Boolean addMoney(String sellerId,int money) throws Exception;
	/**
	 *  为现金提现
	 */
	public Boolean reduceMoney(String sellerId,int money) throws Exception;
	/**
	 * 获取所有的商家信息
	 */
	public List<Seller> findAllSellerInfo(PageUtils pageUtils) throws Exception;
	/**
	 * 获取所有的商家信息的条数
	 */
	public int findAllSellerCount() throws Exception;
	/**
	 * 修改卖家权限
	 */ 
	public Boolean toExamine(String sellerId,int power)throws Exception;
	/**
	 * 删除卖家
	 */
	public Boolean delectSeller(String sellerId)throws Exception;
	
	/*
	 * 方琦
	 */
	/**
	 * 	通过传入商品ID获得卖家信息
	 */
	public Seller infoGoodSeller(int goodId) throws Exception;
	
	/**
	 * 代梦勤
	 * @param buyerId
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
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
	 * 判断是否有卖家
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public Boolean isSeller(Seller seller)throws Exception;
}
