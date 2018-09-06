package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.shop.entity.Goods;
import com.shop.entity.OrderView;

/**
 * 
 * @author 
 * @category 商品逻辑处理接口
 */
public interface GoodsService {
	/**
	 * @author 陈浩雨
	 */
	public List<Goods> findAllGoods(String id)throws Exception;
	public List<Goods> findAllGoods(String id,int pageNow,HttpSession session,String sellerId)throws Exception;
	boolean AddGoods(String id,Goods goods)throws Exception;
	public Goods findGoodById(Integer id) throws Exception;
	boolean UpdateGoods(Goods goods,Integer id)throws Exception;
	boolean DeleteGoods(Integer id)throws Exception;
	public List<Goods> findGoodByName(String name, int pageNow, HttpSession session,String sellerId) throws Exception;
	/**
	 * @author 方琦
	 */
	/**
	 * 货物列表
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Goods> listGood() throws Exception;
	/**
	 * 通过传入商品id获得商品信息和卖家信息
	 */
	public OrderView findByGoodId(int goodId) throws Exception;
}
