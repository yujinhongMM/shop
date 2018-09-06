package com.shop.dao;


import com.shop.entity.Manager;

/**
 * 
 * @author 
 * @category 管理员数据库访问接口
 */
public interface ManagerDao {
	/** 
	 * 用户名和密码根据查找管理员
	 * @param managerId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Manager findUserByNameAndPassword(String managerId, String password) throws Exception;
}
