package com.shop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.utils.DBUtils;
import com.shop.dao.GoodsDao;
import com.shop.entity.Goods;
import com.shop.utils.PageUtils;

/**
 * 
 * @author chen's
 * @category 商品数据库访问接口实现类
 */

public class GoodsDaoImpl implements GoodsDao {

	/**
	 * 陈浩雨
	 */
	@Override
	public List<Goods> findAllGoods(String id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<>();

		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from goods where sellerId=?");

			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodId(rs.getInt("goodId"));
				goods.setName(rs.getString("name"));
				goods.setCity(rs.getString("city"));
				goods.setPrice(rs.getInt("price"));
				goods.setNumber(rs.getInt("number"));
				goods.setUrl(rs.getString("url"));
				list.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}

		return list;
	}

	@Override
	public boolean AddGoods(String id, Goods goods) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
//		ResultSet rs = null;
		boolean result = false;
		try {
			conn = DBUtils.getConnection();
			String sql = "INSERT INTO goods(name,city,price,number,sellerId,url) VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getName());
			ps.setString(2, goods.getCity());
			ps.setInt(3, goods.getPrice());
			ps.setInt(4, goods.getNumber());
			ps.setString(5, id);
			ps.setString(6, goods.getUrl());

			int rows = ps.executeUpdate();

			if (rows == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Goods findGoodById(Integer id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Goods goods = new Goods();
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from goods where goodId=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods.setGoodId(rs.getInt("goodId"));
				goods.setName(rs.getString("name"));
				goods.setCity(rs.getString("city"));
				goods.setPrice(rs.getInt("price"));
				goods.setNumber(rs.getInt("number"));
				// goods.setUrl(rs.getString("url"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}

		return goods;
	}

	@Override
	public boolean UpdateGoods(Goods goods, Integer id) throws Exception {
//		Connection conn = null;
		PreparedStatement ps = null;
//		ResultSet rs = null;
		String sql = "UPDATE goods SET name=?,city=?,price=?,number=?,url=? WHERE goodId=?";
		String sql2 = "UPDATE goods SET name=?,city=?,price=?,number=? WHERE goodId=?";
		Connection connection = DBUtils.getConnection();
		connection.setAutoCommit(false);
		try {
			if (goods.getUrl() != null) {
				ps = connection.prepareStatement(sql);
				ps.setString(1, goods.getName());
				ps.setString(2, goods.getCity());
				ps.setInt(3, goods.getPrice());
				ps.setInt(4, goods.getNumber());
				ps.setString(5, goods.getUrl());
				ps.setInt(6, id);
				ps.executeUpdate();
				connection.commit();
				return true;
			} else {
				ps = connection.prepareStatement(sql2);
				ps.setString(1, goods.getName());
				ps.setString(2, goods.getCity());
				ps.setInt(3, goods.getPrice());
				ps.setInt(4, goods.getNumber());
				ps.setInt(5, id);
				ps.executeUpdate();
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean DeleteGoods(Integer id) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "delete from goods where goodId=?";
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			// 执行sql语句
			ps.executeUpdate();
			// 事务提交
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtils.closeAll(rs, ps, connection);
		}
		return true;
	}

	@Override
	public List<Goods> findAllGoods(String id, PageUtils pageUtils) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<>();
		
		int totalSize = 0;
		totalSize=countProject(id);
		pageUtils.setTotalSize(totalSize);
		if (totalSize <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from goods where sellerId=? LIMIT ?,?");
			ps.setString(1, id);
			ps.setInt(2, start);
			ps.setInt(3, size);

			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodId(rs.getInt("goodId"));
				goods.setName(rs.getString("name"));
				goods.setCity(rs.getString("city"));
				goods.setPrice(rs.getInt("price"));
				goods.setNumber(rs.getInt("number"));
				goods.setUrl(rs.getString("url"));
				list.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}

		return list;
	}

	@Override
	public int countProject(String sellerId) throws Exception {
		String sql = "SELECT COUNT(*) m FROM goods where sellerId=?";

		Connection connection = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, sellerId);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next()) {
				m = rs.getInt("m");
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, connection);
		}
		return -1;
	}

	@Override
	public List<Goods> findGoodByName(String name, PageUtils pageUtils) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<>();
		if (pageUtils.getTotalSize() <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from goods where name=? LIMIT ?,?");
			ps.setString(1, name);
			ps.setInt(2, start);
			ps.setInt(3, size);

			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodId(rs.getInt("goodId"));
				goods.setName(rs.getString("name"));
				goods.setCity(rs.getString("city"));
				goods.setPrice(rs.getInt("price"));
				goods.setNumber(rs.getInt("number"));
				goods.setUrl(rs.getString("url"));
				list.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}

		return list;
	}
	
	
	
	@Override
	public ArrayList<Goods> findAll() throws Exception {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Goods> list=new ArrayList<Goods>();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from goods");
			rs=ps.executeQuery();
			while(rs.next()){
				Goods good=new Goods();
				good.setGoodId(rs.getInt("goodId"));
				good.setName(rs.getString("name"));
				good.setCity(rs.getString("city"));
				good.setPrice(rs.getInt("price"));
				good.setNumber(rs.getInt("number"));
				good.setUrl(rs.getString("url"));
				good.setSellerId(rs.getString("sellerId"));
				list.add(good);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, ps, conn);
		}	
		System.out.println(list);
		return list;
	}

	/*
	 * 方琦
	 */
	/**
	 * 商品详情
	 * @return
	 * @throws Exception
	 */
 
	@Override
	public Goods infoGood(int goodId) throws Exception{
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Goods good=new Goods();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from goods where goodId=?");
			ps.setInt(1,goodId);
			rs=ps.executeQuery();
			if(rs.next()){
				good.setGoodId(rs.getInt("goodId"));
				good.setName(rs.getString("name"));
				good.setCity(rs.getString("city"));
				good.setPrice(rs.getInt("price"));
				good.setNumber(rs.getInt("number"));
				good.setUrl(rs.getString("url"));
				good.setSellerId(rs.getString("sellerId"));
			}else {
				good=null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(good);
		return good;
	}

}
