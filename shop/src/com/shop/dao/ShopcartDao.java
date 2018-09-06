package com.shop.dao;

import java.util.List;

import com.shop.entity.Shopcart;

/**
 * 
 * @author 张梓桐
 * @category 购物车数据库访问接口
 */
public interface ShopcartDao {
	//查看购物车
	public List<Shopcart> seecart(String Buyerid) throws Exception;
	//删除商品
	public boolean deleteCartGoods(Integer[] Goodsid,String Buyerid) throws Exception;
	//加入购物车
	public boolean addCartGoods(Shopcart Shopcart) throws Exception;
	//查看商品详情
	
	//查看物流
}
