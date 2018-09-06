package com.yjh.practice.dao;

import java.util.List;

import com.yjh.practice.model.Company;
/**
 * 
 * Description 企业信息管理操作接口
 * @author YJH
 * @date 2018年6月3日  
 *
 */
public interface CompanyDao {
	/**
	 * <p>Title: registerCompanyInfo</p>
	 * <p>Description: 该接口方法主要处理公司注册</p>
	 * @param company Company实体类的对象引用
	 * @return 返回注册成功与否的标志，成功返回true，失败返回false
	 */
	boolean registerCompanyInfo(Company company);
	
	/**
	 * <p>Title: updateCompanyInfo</p>
	 * <p>Description:该接口方法主要处理企业修改注册页面信息 </p>
	 * @param company Company实体类的对象引用
	 * @return 返回修改成功与否的标志，成功返回true，失败返回false
	 */
	boolean updateCompanyInfo(Company company);
	
	/**
	 * <p>Title: queryCompanyInfo</p>
	 * <p>Description: 该接口方法主要处理企业查询信息 </p>
	 * @param companyName 企业注册的用户名
	 * @return 根据传入用户名，返回Company实体类
	 */
	Company queryCompanyInfo(String companyUserName);
	
	/**
	 * <p>Title: updatePassword</p>
	 * <p>Description: 该接口方法主要处理修改密码</p>
	 * @param companyName 企业注册的用户名
	 * @param newPassword 企业登录密码
	 * @return 返回修改成功与否的标志，成功返回true，失败返回false
	 */
	boolean updateCompanyPassword(String companyUserName,String newPassword);
	
	/**
	 * <p>Title: queryCompanys</p>
	 * <p>Description: 该接口方法主要处理根据条件,查询已经审核的企业</p>
	 * @param condition 查询条件
	 * @return 返回List<Company>集合
	 */
	List<Company> queryViryFyCompanys();
	
	/**
	 * 
	 * <p>Title: queryNotVirefyCompanys</p>
	 * <p>Description: 该接口方法主要处理根据条件,查询未审核的企业</p>
	 * @return 返回List<Company>集合
	 */
	List<Company> queryNotVirefyCompanys();
	
	/**
	 * 
	 * <p>Title: queryAllCompanys</p>
	 * <p>Description:该接口方法主要处理根据条件,查询所有企业 </p>
	 * @return 返回List<Company>集合
	 */
	List<Company> queryAllCompanys();
	
	/**
	 * <p>Title: deleteCompany</p>
	 * <p>Description: 删除企业信息</p>
	 * @param companyUsername 企业注册时的用户名
	 * @return 返回删除的结果，即true/false
	 */
	boolean deleteCompany(String companyUsername);
	/**
	 * <p>Title: checkCompany</p>
	 * <p>Description:该接口方法主要处理管理员审核企业信息</p>
	 * @param company Company实体类引用对象
	 * @return 返回一个检查完的标志,审核通过返回true,审核不通过返回false
	 */
	boolean checkCompany(Company company);
	
	/**
	 * <p>Title: queryByUserName</p>
	 * <p>Description: 根据注册的用户名查询企业</p>
	 * @param account 注册用户名
	 * @return 公司
	 */
	Company queryByUserName(String account);
	/**
	 * <p>Title: backReview</p>
	 * <p>Description: 退审</p>
	 * @param companyUsername 用户名
	 * @return 返回退审成功与否的标志
	 */
	boolean backReview(Company company);
	
}
