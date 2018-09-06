package com.shop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.dao.ManagerDao;
import com.shop.entity.Manager;
import com.shop.utils.DBUtils;
import com.shop.utils.MdPwdUtil;

/**
 * 
 * @author 
 * @category 管理员数据库访问接口实现类
 */
public class ManagerDaoImpl implements ManagerDao{

	@Override
	public Manager findUserByNameAndPassword(String managerId, String password) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Manager manager=new Manager();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from manager where managerId=? and password=?");
			//设置参数
			ps.setString(1, managerId);
			ps.setString(2, MdPwdUtil.MD5Password(password));
			
			rs=ps.executeQuery();
			if(rs.next()){
				
				manager.setManagerId(rs.getString("managerId"));
				manager.setPassword(rs.getString("password"));
				
			}else{
				manager=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(manager);
		return manager;
	}

}
