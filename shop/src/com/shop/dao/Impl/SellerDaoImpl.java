package com.shop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shop.dao.GoodsDao;
import com.shop.dao.SellerDao;
import com.shop.entity.Seller;
import com.shop.utils.DBUtils;
import com.shop.utils.MdPwdUtil;
import com.shop.utils.PageUtils;
/**
 * 
 * @author YJH
 * @category 卖家数据库访问接口实现类
 */
public class SellerDaoImpl implements SellerDao{

	@Override
	public Seller findUserByNameAndPassword(String sellerId, String password) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Seller seller=new Seller();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from seller where sellerId=? and password=?");
			//设置参数
			ps.setString(1, sellerId);
			ps.setString(2, MdPwdUtil.MD5Password(password));
			
			rs=ps.executeQuery();
			if(rs.next()){
				
				seller.setSellerId(rs.getString("sellerId"));
				seller.setPassword(rs.getString("password"));
				seller.setEmail(rs.getString("email"));
				seller.setBirthday(rs.getDate("birthday"));
				seller.setShopname(rs.getString("shopname"));
				seller.setWallet(rs.getInt("wallet"));
				seller.setPower(rs.getInt("power"));
				
				
			}else{
				seller=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(seller);
		return seller;
	}

	@Override
	public Boolean addMoney(String sellerId,int money) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
	
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("UPDATE seller SET wallet=wallet+? where sellerId=?");
			ps.setInt(1, money);
			ps.setString(2, sellerId);
			int i=ps.executeUpdate();
			if(i>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);
		return flag;
	}

	@Override
	public Boolean reduceMoney(String sellerId,int money) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
	
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("UPDATE seller SET wallet=wallet-? where sellerId=?");
			ps.setInt(1, money);
			ps.setString(2, sellerId);
			int i=ps.executeUpdate();
			if(i>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);
		return flag;
	}

	@Override
	public List<Seller> findAllSellerInfo(PageUtils pageUtils) throws Exception {
		
		int totalSize = 0;
		totalSize=findAllSellerCount();
		pageUtils.setTotalSize(totalSize);
		if (totalSize <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Seller> sellerlist=new ArrayList<Seller>();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from seller LIMIT ?,?");
			ps.setInt(1, start);
			ps.setInt(2, size);
			rs=ps.executeQuery();
			while(rs.next()){
				
				Seller seller=new Seller();
				seller.setSellerId(rs.getString("sellerId"));
				seller.setPassword(rs.getString("seller.password"));
				seller.setEmail(rs.getString("seller.email"));
				seller.setBirthday(rs.getDate("seller.birthday"));
				seller.setShopname(rs.getString("shopname"));
				seller.setWallet(rs.getInt("seller.wallet"));
				seller.setPower(rs.getInt("power"));
				sellerlist.add(seller);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(sellerlist);
		return sellerlist;
	}

	@Override
	public int findAllSellerCount() throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int m=0;
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select COUNT(*) m from seller");
			rs=ps.executeQuery();
		
				
			if (rs.next()) {
				m = rs.getInt("m");
			}
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(m);
		return m;
	}

	@Override
	public Boolean toExamine(String sellerId, int power) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("UPDATE seller SET power=? where sellerId=?");
			ps.setInt(1, power);
			ps.setString(2, sellerId);
			int i=ps.executeUpdate();
			if(i>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);
		return flag;
	}

	@Override
	public Boolean delectSeller(String sellerId) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("DELETE FROM seller where sellerId=?");
			ps.setString(1, sellerId);
			int i=ps.executeUpdate();
			System.out.println("DELETE: " + i);
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);
		return flag;
	}
	
	/*
	 * 方琦
	 */
	@Override
	public Seller infoGoodSeller(int goodId) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Seller seller=new Seller();
		GoodsDao goodsDao=new GoodsDaoImpl();
		if(goodsDao.infoGood(goodId)!=null) {
			seller.setSellerId(goodsDao.infoGood(goodId).getSellerId());
		}
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from seller where sellerId=?");
			ps.setString(1,seller.getSellerId());
			rs=ps.executeQuery();
			if(rs.next()){
				seller.setSellerId(rs.getString("sellerId"));
				seller.setPassword(rs.getString("password"));
				seller.setShopname(rs.getString("shopname"));
				seller.setEmail(rs.getString("email"));
				seller.setBirthday(rs.getDate("birthday"));
				seller.setWallet(rs.getInt("wallet"));
				seller.setPower(rs.getInt("power"));
			}else {
				seller=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(seller);
		return seller;
	}

	
	
	/*
	 * 代梦勤
	 */
	@Override
	public Boolean modifySeller(Seller seller) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		
		try {
			String sql = "UPDATE seller SET password=?,shopname=?,email=?,birthday=? WHERE sellerId=?";
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, seller.getPassword());
			ps.setString(2, seller.getShopname());
			ps.setString(3, seller.getEmail());
			//时间转化
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String date=sf.format(seller.getBirthday());
			ps.setString(4, date);
			ps.setString(5, seller.getSellerId());
			int i=ps.executeUpdate();
			System.out.println(i);
				flag=true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);
		return flag;
	}

	@Override
	public Boolean addSeller(Seller seller) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		try {
			String sql = "insert into seller(password,shopname,email,birthday,sellerId) values (?,?,?,?,?)";
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, seller.getPassword());
			ps.setString(2, seller.getShopname());
			ps.setString(3, seller.getEmail());
			//时间转化
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String date=sf.format(seller.getBirthday());
			ps.setString(4, date);
			ps.setString(5, seller.getSellerId());
			int i=ps.executeUpdate();
			if(i>0){
				System.out.println("success");
				flag=true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);

		return flag;
	}

	@Override
	public Boolean isSeller(Seller seller) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from seller where sellerId=?");
			//设置参数
			ps.setString(1, seller.getSellerId());
		
			
			rs=ps.executeQuery();
			if(rs.next()){
				flag=true;
				System.out.println("用户已经存在");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);
		return flag;
	}		
	
		
	
}
