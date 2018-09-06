package com.yjh.practice.service;

import com.yjh.practice.model.SystemParameter;

public interface SystemParameterService {
	/**
	 * <p>Title: setSystemConfig</p>
	 * <p>Description: 设置系统参数</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 设置成功返回true，设置失败返回false
	 */
	boolean setOrUpdateSystemConfig(String account,String pwd,String code,
			String releaseProjectStartDate,String releaseProjectEndDate,
			String studentSelStartDate,String studentSelEndDate,String studentSelMaxnum,
			String userName);
	/**
	 * <p>Title: queryByAccount</p>
	 * <p>Description: 根据用户名查询</p>
	 * @param accountName 系统参数表的用户名
	 * @return 系统设置实体类的引用
	 */
	SystemParameter queryByAccount(String accountName);
}
