package com.shop.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.shop.dao.GoodsDao;
import com.shop.dao.SellerDao;
import com.shop.dao.Impl.GoodsDaoImpl;
import com.shop.dao.Impl.SellerDaoImpl;
import com.shop.entity.Goods;
import com.shop.entity.OrderView;
import com.shop.entity.Seller;
import com.shop.service.GoodsService;
import com.shop.utils.PageUtils;

/**
 * 
 * @author 
 * @category 商品逻辑处理接口实现
 */
public class GoodsServiceImpl implements GoodsService{
	
	/**
	 * 
	 * @author 陈浩雨
	 */
	@Override
	public List<Goods> findAllGoods(String id) throws Exception {
		
		
		GoodsDao goodsDao = new GoodsDaoImpl();
		
		List<Goods> goods = goodsDao.findAllGoods(id);
		
		if(!goods.isEmpty()&&goods.size()>0) {
			return goods;
		}else {
			throw new RuntimeException("沒有商品");
		}
		
	}

	@Override
	public boolean AddGoods(String id, Goods goods) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		return goodsDao.AddGoods(id, goods);
	}

	@Override
	public Goods findGoodById(Integer id) throws Exception {
		GoodsDao goodsDao =new GoodsDaoImpl();
		Goods goods =goodsDao.findGoodById(id);
		return goods;
	}

	@Override
	public boolean UpdateGoods(Goods goods,Integer id) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		return goodsDao.UpdateGoods(goods,id);
	}

	@Override
	public boolean DeleteGoods(Integer id) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		return goodsDao.DeleteGoods(id);
	}

	@Override
	public List<Goods> findAllGoods(String id, int pageNow, HttpSession session,String sellerId) throws Exception {
		
		GoodsDao goodsDao = new GoodsDaoImpl();
		int totalSize = 0;
		totalSize = goodsDao.countProject(sellerId);
		int pageSize = 1;
		PageUtils pageUtils = new PageUtils(pageNow, totalSize);
		int totalPage = totalSize / pageSize + 1;
		pageUtils.setTotalPage(totalPage);
		pageUtils.setPageSize(pageSize);
		if (pageNow == 1){
			pageUtils.setHasPre(false);
		}
		if (pageNow == totalPage){
			pageUtils.setHasNext(false);
		}
		session.setAttribute("pageUtils", pageUtils);
		List<Goods> goods = goodsDao.findAllGoods(id,pageUtils);
		
		if(!goods.isEmpty()&&goods.size()>0) {
			return goods;
		}else {
			throw new RuntimeException("沒有商品");
		}
	}

	@Override
	public List<Goods> findGoodByName(String name, int pageNow, HttpSession session,String sellerId) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		int totalSize = 0;
		totalSize = goodsDao.countProject(sellerId);
		int pageSize = 1;
		PageUtils pageUtils = new PageUtils(pageNow, totalSize);
		int totalPage = totalSize / pageSize + 1;
		pageUtils.setTotalPage(totalPage);
		pageUtils.setPageSize(pageSize);
		if (pageNow == 1){
			pageUtils.setHasPre(false);
		}
		if (pageNow == totalPage){
			pageUtils.setHasNext(false);
		}
		session.setAttribute("pageUtils", pageUtils);
		List<Goods> goods = goodsDao.findGoodByName(name,pageUtils);
		
		if(!goods.isEmpty()&&goods.size()>0) {
			return goods;
		}else {
			throw new RuntimeException("沒有商品");
		}
	}
	
	
	/**
	 * 方琦
	 */
	@Override
	
	//商品列表
	public ArrayList<Goods> listGood() throws Exception {
		GoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.findAll();
		// TODO Auto-generated method stub
	}
	
	//商品详情
	@Override
	public OrderView findByGoodId(int goodId) throws Exception {
		OrderView orderView=new OrderView();
		GoodsDao goodsDao=new GoodsDaoImpl();
		SellerDao sellerDao=new SellerDaoImpl();
		Goods goods=goodsDao.infoGood(goodId);
		Seller seller=sellerDao.infoGoodSeller(goodId);
		orderView.setGoods(goods);
		orderView.setSeller(seller);
		return orderView;
	}




}
