package com.shop.service.Impl;



import java.util.ArrayList;
import java.util.List;

import com.shop.dao.BuyerDao;
import com.shop.dao.ManagerDao;
import com.shop.dao.SellerDao;
import com.shop.dao.ShopcartDao;
import com.shop.dao.Impl.BuyerDaoImpl;
import com.shop.dao.Impl.ManagerDaoImpl;
import com.shop.dao.Impl.SellerDaoImpl;
import com.shop.dao.Impl.ShopcartImpl;
import com.shop.entity.Buyer;
import com.shop.entity.Manager;
import com.shop.entity.Seller;
import com.shop.entity.Shopcart;
import com.shop.service.UserService;


/**
 * 
 * @author 
 * @category 用户业务逻辑处理接口实现
 */
public class UserServiceImpl implements UserService{
	BuyerDao buyerdao=new BuyerDaoImpl();
	SellerDao sellerdao=new SellerDaoImpl();
	ManagerDao managerdao=new ManagerDaoImpl();
	@Override
	public Buyer loginBuyer(String buyerId, String password) throws Exception {
		return buyerdao.findUserByNameAndPassword(buyerId, password);	
	}

	@Override
	public Seller loginSeller(String sellerId, String password) throws Exception {
		return sellerdao.findUserByNameAndPassword(sellerId, password);
	}

	@Override
	public Manager loginManager(String managerId, String password) throws Exception {
		// TODO Auto-generated method stub
		return managerdao.findUserByNameAndPassword(managerId, password);
	}

	@Override
	public Boolean sellerManagerWallet(String sellerId, int money, String type) throws Exception {
		SellerDao sellerDao=new SellerDaoImpl();
		if(type.equals("1")){
			if(sellerDao.addMoney(sellerId, money)){
				return true;
			}
		}else if(type.equals("2")){
			if(sellerDao.reduceMoney(sellerId, money)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean buyerManagerWallet(String buyerId, int money, String type) throws Exception {
		BuyerDao buyerDao=new BuyerDaoImpl();
		if(type.equals("1")){
			if(buyerDao.addMoney(buyerId, money)){
				return true;
			}
		}else if(type.equals("2")){
			if(buyerDao.reduceMoney(buyerId, money)){
				return true;
			}
			
		}
		return false;
	}
	/*
	 * 代梦勤
	 */
	
	@Override
	public Boolean modifyBuyer(Buyer buyer) throws Exception {
		BuyerDao buyerDao=new BuyerDaoImpl();
		
		return buyerDao.modifyBuyer(buyer);
	}

	@Override
	public Boolean modifySeller(Seller seller) throws Exception {
		SellerDao sellerDao=new SellerDaoImpl();
		// TODO Auto-generated method stub
		return sellerDao.modifySeller(seller);
	}

	@Override
	public Boolean addSeller(Seller seller) throws Exception {
		SellerDao sellerDao=new SellerDaoImpl();
		return sellerDao.addSeller(seller);
	}

	@Override
	public Boolean addBuyer(Buyer buyer) throws Exception {
		BuyerDao buyerDao=new BuyerDaoImpl();
		return buyerDao.addBuyer(buyer);
	}

	@Override
	public Boolean isSeller(Seller seller) throws Exception {
		SellerDao sellerDao=new SellerDaoImpl();
		return sellerDao.isSeller(seller);
	}

	@Override
	public Boolean isBuyer(Buyer buyer) throws Exception {
		BuyerDao buyerDao=new BuyerDaoImpl();
		return buyerDao.isBuyer(buyer);
	}
	
	/*
	 * 张梓桐
	 */
	//查看购物车 参数：buyerid
		@Override
		public List<Shopcart> seecart_service(String Buyerid) throws Exception {
			// TODO Auto-generated method stub
			ShopcartDao shopcartdao=new ShopcartImpl();
			List<Shopcart> shopcartlist=new ArrayList<Shopcart>();
			shopcartlist=shopcartdao.seecart(Buyerid);
			return shopcartlist;
		}

		//删除购物车  参数：1.商品ID数组  2.用户id
		@Override
		public boolean deleteCartGoods_service(Integer[] Goodsid, String Buyerid) throws Exception {
			// TODO Auto-generated method stub
			ShopcartDao shopcartdao=new ShopcartImpl();
			boolean state;
			state=shopcartdao.deleteCartGoods(Goodsid, Buyerid);
			return state;
		}

		//添加到购物车
		@Override
		public boolean addCartGoods_service(String Buyerid,Integer goodid,Integer number) throws Exception {
			// TODO Auto-generated method stub
			Shopcart shopcart=new Shopcart();
			shopcart.setBuyerid(Buyerid);
			shopcart.setGoodsid(goodid);
			shopcart.setNumber(number);
			ShopcartDao shopcartdao=new ShopcartImpl();
			return shopcartdao.addCartGoods(shopcart);
		}

	
}
