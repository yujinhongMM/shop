package com.yjh.practice.service.impl;

import java.util.List;

import com.yjh.practice.dao.CompanyDao;
import com.yjh.practice.dao.impl.CompanyDaoImpl;
import com.yjh.practice.model.Company;
import com.yjh.practice.service.CompanyService;
import com.yjh.practice.utils.MdPwdUtil;

/**
 * 
 * Description 企业信息管理操作
 * @author YJH
 * @date 2018年6月5日  
 *
 */

public class CompanyServiceImpl implements CompanyService{
	private CompanyDao companyDao = new CompanyDaoImpl();
	/**
	 * 处理公司注册的业务逻辑
	 */
	public boolean registerCompanyInfo(String username,String companyName,String mailbox,String password,String yzm,String yzm0) {
		try {
				if(yzm == null || !yzm0.equals(yzm.toUpperCase())){
					return false;
				}
				String mdPwd = MdPwdUtil.MD5Password(password);
				Company company = new Company();
				company.setUsername(username);
				company.setCompanyName(companyName);
				company.setMailbox(mailbox);
				company.setPassword(mdPwd);
				company.setContacts("");
				company.setPhone("");
				return this.companyDao.registerCompanyInfo(company);
			
		}catch(Exception e) {
			return false;
		}
			
	}

	/**
	 * 
	 * <p>Title: updateCompanyInfo</p>
	 * <p>Description: 更新企业信息</p>
	 * @param company 
	 * @return boolean
	 */
	public boolean updateCompanyInfo(Company company) {
			try {
				if (company != null ) {
				return this.companyDao.updateCompanyInfo(company);
				}
				return false;
 			} catch(Exception exception) {
 				return false;
 			}
	}

	/**
	 * 执行更新密码操作
	 */
	public boolean updateCompanyPassword(String companyUserName, String newPassword) {
		try {
			//如果用户名和password都不为空的话，那么就调用dao层方法，否则返回false
			if (companyUserName != null && !"".equals(companyUserName)&&
					newPassword!= null && !"".equals(newPassword)) {
				return this.companyDao.updateCompanyPassword(companyUserName, newPassword);
			}
			return false;
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * <p>Title: queryViryFyCompanys</p>
	 * <p>Description: 查询企业</p>
	 * @param condition 查询条件
	 * @return 返回查询到的企业的集合
	 */
	public List<Company> queryCompanys(String condition) {
		/**
		 * 首先对condition进行判断，如果不为空，则判断其值，选取对应的查询方法，如果为空，直接返回null
		 */
		try {
			if (condition != null) {
				if ("未审核".equals(condition)) {
					return this.companyDao.queryNotVirefyCompanys();
				}
				if ("审核".equals(condition)) {
					return this.companyDao.queryViryFyCompanys();
				}
				if ("all".equals(condition)) {
					return this.companyDao.queryAllCompanys();
				}
				return null ;
			}
			return null ;
		} catch(Exception e) {
			return null ;
		}
	}

	/**
	 * <p>Title: deleteCompany</p>
	 * <p>Description: 删除企业信息</p>
	 * @param companyUsername 企业注册时的用户名
	 * @return 返回删除的结果，即true/false
	 */
	public boolean deleteCompany(String companyUsername) {
		try {
			if (companyUsername != null && !"".equals(companyUsername)) {
				return this.companyDao.deleteCompany(companyUsername);
			}
			return false;
		} catch(Exception e) {
			return false ;
		}
	}

	/**
	 * <p>Title: checkCompany</p>
	 * <p>Description:该接口方法主要处理管理员审核企业信息</p>
	 * @param company Company实体类引用对象
	 * @return 返回一个检查完的标志,审核通过返回true,审核不通过返回false
	 */
	
	public boolean checkCompany(Company company) {
		
		try {
			if (company != null) {
				if (company.getUsername() != null && !"".equals(company.getUsername())) {
					System.out.println("条件不为空");
					return this.companyDao.checkCompany(company);
				}
			}
			return false;
		} catch(Exception e) {
			return false ;
		}
	}

	/**
	 * 根据用户名查询公司
	 */
	public Company queryByUserName(String account) {
		try {
			if (account != null && !"".equals(account)) {
				return this.companyDao.queryByUserName(account);
			}
			return null;
		} catch(Exception e) {
			return null ;
		}
	}

	@Override
	public boolean backReview(Company company) {
		try {
			if (company != null) {
				if (company.getUsername() != null && !"".equals(company.getUsername())) {
					return this.companyDao.backReview(company);
				}
			}
			return false;
		} catch(Exception e) {
			return false ;
		}
	}



}
