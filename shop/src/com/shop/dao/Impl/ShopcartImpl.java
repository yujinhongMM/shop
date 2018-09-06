package com.shop.dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.dao.ShopcartDao;
import com.shop.entity.Goods;
import com.shop.entity.Shopcart;
import com.shop.service.Impl.GoodsServiceImpl;
import com.shop.utils.DBUtils;

/**
 * 
 * @author  张梓桐
 * @category 购物车数据库访问接口实现类
 */
public class ShopcartImpl implements ShopcartDao{
	// 删除购物车的商品
		@Override
		public boolean deleteCartGoods(Integer[] Goodsid, String Buyerid) throws Exception {
			// TODO Auto-generated method stub
			// 接受参数
			Integer[] goodsid = Goodsid;
			String buyerid = Buyerid;
			Connection conn = DBUtils.getConnection();
			Statement st = conn.createStatement();
			// ResultSet rs=null;

			for (Integer goodsidlist : goodsid) {
				String sql = "delete from shopcart where buyerId='" + buyerid + "'and goodId=" + goodsidlist + "";
				st.execute(sql);

			}
			return true;
		}

		// 查看购物车
		@SuppressWarnings("null")
		@Override
		public List<Shopcart> seecart(String Buyerid) throws Exception {
			// TODO Auto-generated method stub
			//接受参数
			String buyerid= Buyerid;
			Shopcart shopcart=null;
			Goods goods=null;
			Connection conn=DBUtils.getConnection();
			Statement st =conn.createStatement();
			ResultSet rs=null;
			List<Shopcart> shopcartlist=new ArrayList<Shopcart>();
			//获取购物车信息
			String sql="select * from shopcart where buyerId='"+buyerid+"'";
			rs=st.executeQuery(sql);
			while(rs.next()){
				shopcart=new Shopcart();
				shopcart.setBuyerid(rs.getString(1));
				shopcart.setGoodsid(rs.getInt(2));
				shopcart.setNumber(rs.getInt(3));
				
				//获取商品详细信息
				//调用接口
				goods=new GoodsServiceImpl().findGoodById(shopcart.getGoodsid());
				shopcart.setGoods(goods);
				shopcartlist.add(shopcart);
			}

			DBUtils.closeAll(rs, st, conn);
			return shopcartlist;
		}

		// 添加到购物车
		@Override
		public boolean addCartGoods(Shopcart Shopcart) throws Exception {
			// TODO Auto-generated method stub
			Connection conn = DBUtils.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = null;
			// 接受参数
			Shopcart shopcart = Shopcart;
			String sql = "insert into shopcart (buyerId,goodId,number) values('" + shopcart.getBuyerid() + "',"
					+ shopcart.getGoodsid() + "," + shopcart.getNumber() + ")";
			st.execute(sql);

			return true;
		}

		
}
