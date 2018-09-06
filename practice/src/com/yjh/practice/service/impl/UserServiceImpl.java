package com.yjh.practice.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yjh.practice.service.UserService;
import com.yjh.practice.utils.DbUtils;
import com.yjh.practice.utils.MdPwdUtil;

/**
 * 
 * Description 用户业务逻辑，定义与用户登录等功能相关的接口实现
 * @author YJH
 * @date 2018年6月2日  
 *
 */



public class UserServiceImpl implements UserService{
	
	@Override
	//用户登录时，在页面选择角色，然后输入需要的参数，如果验证码和session中的一致，则进行下一步验证
	//如果role=1，进企业表；如果role=2，进学生表；如果role=9，进系统参数表
	public boolean login(String account, String password, String Verification_Code,String role,String vchidden) {
		Connection con = (Connection) DbUtils.getConnection();
		String sql = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String account_type = "";
		System.out.println(Verification_Code + " "+ vchidden.toUpperCase());
		//如果验证码不正确或没有得到验证码，返回false
		if(Verification_Code == null || !vchidden.equals(Verification_Code.toUpperCase())){
			return false;
		}
		
		//如果用户角色没有选中，则直接返回false
		if(role == null){
			return false;
		}else{
			//根据不同的角色，生成不同的sql语句
			switch(role){
			case "1": 
				//"企业";
				sql = "select * from company where username=? and password = ?"; 
				break;
			case "2": 
				//"学生";
				sql = "select * from student where No=? and password = ?"; 
				break;
			case "9": 
				//"管理员";
				sql = "select * from system_parameter where admin_username=? and admin_password = ?";
			}
		}
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, MdPwdUtil.MD5Password(password));
			System.out.println(ps.toString());
			rs = ps.executeQuery();
//			System.out.println(ps.toString());
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtils.closeConnection(con, ps, rs);
		}
		return false;
	}

	
}
