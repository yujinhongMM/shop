package com.shop.dao;

import com.shop.entity.Buyer;



/**
 * 
 * @author 
 * @category 买家数据库访问接口
 */
public interface BuyerDao {
	/**
	 * 用户名和密码根据查找买家
	 * @param buyerId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Buyer findUserByNameAndPassword(String buyerId, String password) throws Exception;
	/**
	 *  为钱包充值
	 */
	public Boolean addMoney(String buyerId,int money) throws Exception;
	/**
	 *  为现金提现
	 */
	public Boolean reduceMoney(String buyerId,int money) throws Exception;
	
	
	
	/**
	 * 代梦勤
	 * @param buyerId
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	/**
	 * 修改买家信息
	 */
	public Boolean modifyBuyer(Buyer buyer)throws Exception;
	/**
	 * 丁洁琳
	 */
	/**
	 * 增加买家
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public Boolean addBuyer(Buyer buyer)throws Exception;
	/**
	 * 判断是否有买家
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public Boolean isBuyer(Buyer buyer)throws Exception;
	
}
