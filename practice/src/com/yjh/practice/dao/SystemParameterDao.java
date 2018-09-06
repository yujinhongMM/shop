package com.yjh.practice.dao;

import com.yjh.practice.model.SystemParameter;

/**
 * 
 * Description
 * @author YJH
 * @date 2018年6月4日  
 *
 */

public interface SystemParameterDao {
	/**
	 * <p>Title: setSystemConfig</p>
	 * <p>Description: 设置系统参数</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 新增成功返回true，设置失败返回false
	 */
	boolean setSystemConfig(SystemParameter systemConfig);
	
	/**
	 * <p>Title: updateSystemConfig</p>
	 * <p>Description: 更新系统参数表</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 设置成功返回true，设置失败返回false
	 */
	boolean updateSystemConfig(SystemParameter systemConfig,String username);
	
	/**
	 * <p>Title: updateSystemConfig</p>
	 * <p>Description: 更新系统参数表</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 设置成功返回true，设置失败返回false
	 */
	boolean updateSystemConfigNoPwd(SystemParameter systemConfig,String username);
	
	/**
	 * <p>Title: queryByAccount</p>
	 * <p>Description: 根据用户名查询</p>
	 * @param accountName 系统参数表的用户名
	 * @return 系统设置实体类的引用
	 */
	SystemParameter queryByAccount(String accountName);
}

