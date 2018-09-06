package com.yjh.practice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yjh.practice.dao.SystemParameterDao;
import com.yjh.practice.model.SystemParameter;
import com.yjh.practice.utils.DbUtils;
/**
 * 
 * Description 系统设置
 * @author YJH
 * @date 2018年6月4日  
 *
 */

public class SystemParameterDaoImpl implements SystemParameterDao {

	/**
	 * 设置系统参数
	 */
	public boolean setSystemConfig(SystemParameter systemConfig) {
		//连接数据库
		Connection connection = DbUtils.getConnection();
		String configSql = "insert into system_parameter values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null ;
		try {
			//设置事务为手动提交
			connection.setAutoCommit(false);
			//获取PreparedStatement
			ps = connection.prepareStatement(configSql);
			//注入参数
			ps.setString(1, systemConfig.getAdminUsername());
			ps.setString(2, systemConfig.getAdminPassword());
			ps.setString(3, systemConfig.getInvitationCode());
			ps.setDate(4, systemConfig.getReleaseProjectStartDate());
			ps.setDate(5, systemConfig.getReleaseProjectEndDate());
			ps.setDate(6, systemConfig.getStudentSelStartDate());
			ps.setDate(7, systemConfig.getStudentSelEndDate());
			ps.setInt(8, systemConfig.getStudentSelMaxnum());
			//执行语句
			ps.execute();
			//提交事务
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	public boolean updateSystemConfig(SystemParameter systemConfig,String username) {
		Connection connection = DbUtils.getConnection();
		String configSql = "update system_parameter set admin_password = ?,invitation_code=?,release_project_start_date=?,"
				+ "release_project_end_date=?,student_sel_start_date=?,student_sel_end_date=?,"
				+ "student_sel_maxnum=? where admin_username = ?";
		PreparedStatement ps = null ;
		try {
			//设置事务为手动提交
			connection.setAutoCommit(false);
			//获取PreparedStatement
			ps = connection.prepareStatement(configSql);
			ps.setString(1, systemConfig.getAdminPassword());
			ps.setString(2, systemConfig.getInvitationCode());
			ps.setDate(3, systemConfig.getReleaseProjectStartDate());
			ps.setDate(4, systemConfig.getReleaseProjectEndDate());
			ps.setDate(5, systemConfig.getStudentSelStartDate());
			ps.setDate(6, systemConfig.getStudentSelEndDate());
			ps.setInt(7, systemConfig.getStudentSelMaxnum());
			ps.setString(8, username);//未更改前的用户名
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	@Override
	public SystemParameter queryByAccount(String accountName) {
		Connection connection = DbUtils.getConnection();
		String configSql = "select * from  system_parameter where admin_username = ?";
		PreparedStatement ps = null ;
		SystemParameter systemConfig = null ;
		ResultSet resultSet = null ;
		try {
			//设置事务为手动提交
			connection.setAutoCommit(false);
			//获取PreparedStatement
			ps = connection.prepareStatement(configSql);
			ps.setString(1, accountName);
			ps.executeQuery();
			connection.commit();
			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				systemConfig = new SystemParameter();
				systemConfig.setAdminUsername(resultSet.getString("admin_username"));
				systemConfig.setAdminPassword(resultSet.getString("admin_password"));
				systemConfig.setInvitationCode(resultSet.getString("invitation_code"));
				systemConfig.setReleaseProjectStartDate(resultSet.getDate("release_project_start_date"));
				systemConfig.setReleaseProjectEndDate(resultSet.getDate("release_project_end_date"));
				systemConfig.setStudentSelEndDate(resultSet.getDate("student_sel_end_date"));
				systemConfig.setStudentSelStartDate(resultSet.getDate("student_sel_start_date"));
				systemConfig.setStudentSelMaxnum(resultSet.getInt("student_sel_maxnum"));
			}
			return systemConfig ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			DbUtils.closeConnection(connection, ps,resultSet);
		}
	}

	@Override
	public boolean updateSystemConfigNoPwd(SystemParameter systemConfig, String username) {
		Connection connection = DbUtils.getConnection();
		String configSql = "update system_parameter set invitation_code=?,release_project_start_date=?,"
				+ "release_project_end_date=?,student_sel_start_date=?,student_sel_end_date=?,"
				+ "student_sel_maxnum=? where admin_username = ?";
		PreparedStatement ps = null ;
		try {
			//设置事务为手动提交
			connection.setAutoCommit(false);
			//获取PreparedStatement
			ps = connection.prepareStatement(configSql);
			ps.setString(1, systemConfig.getInvitationCode());
			ps.setDate(2, systemConfig.getReleaseProjectStartDate());
			ps.setDate(3, systemConfig.getReleaseProjectEndDate());
			ps.setDate(4, systemConfig.getStudentSelStartDate());
			ps.setDate(5, systemConfig.getStudentSelEndDate());
			ps.setInt(6, systemConfig.getStudentSelMaxnum());
			ps.setString(7, username);//未更改前的用户名
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

}

