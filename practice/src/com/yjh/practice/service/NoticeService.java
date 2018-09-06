package com.yjh.practice.service;

import java.sql.Date;
import java.util.List;

import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.model.NoticeCompany;



public interface NoticeService {
	/**
	 * <p>Title: updateCompanyNotice</p>
	 * <p>Description: 修改企业发布的通知公告</p>
	 * @param companyNotice 企业通知公告实体类的引用
	 * @return 更新成功返回true，更新失败返回false
	 */
	boolean updateCompanyNotice(NoticeCompany companyNotice);
	
	/**
	 * <p>Title: deleteCompanyNotice</p>
	 * <p>Description: 根据公告Id删除企业发布的公告</p>
	 * @param companyNoticeId 企业公告Id
	 * @return 删除成功返回true，删除失败返回false
	 */
	boolean deleteCompanyNotice(int companyNoticeId);
	
	/**
	 * <p>Title: provideAnnouncement</p>
	 * <p>Description:发布企业公告 </p>
	 * @param companyNotice 企业通知公告实体类的引用
	 */
	void provideAnnouncement(NoticeCompany companyNotice);
	
	/**
	 * <p>Title: reviewCompanyNotice</p>
	 * <p>Description: 管理员审核企业公告</p>
	 * @param companyNoticeId 企业公告Id
	 * @param companyNoticeDate 通知公告审核日期
	 * @return 设置审核日期成功返回true，删除失败返回false
	 */
	boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate);
	/**
	 * <p>Title: queryNoticeByCompanyName</p>
	 * <p>Description: 根据公司的账号名查询通知公告</p>
	 * @param companyUserName 公司的账号名
	 * @return 通知集合
	 */
	List<NoticeCompany>  queryNoticeByCompanyName(String companyUserName,int pageNow,int pageSize);
	/**
	 * <p>Title: queryAllByName</p>
	 * <p>Description: 根据公司名查询所有公告数量</p>
	 * @param companyUserName 公司名
	 * @return 公告的数量
	 */
	int queryAllByName(String companyUserName);
	
	/**
	 * <p>Title: queryNoticeById</p>
	 * <p>Description: 根据Id查询通知</p>
	 * @param companyNoticeId 通知Id
	 * @return NoticeCompany实体类
	 */
	NoticeCompany queryNoticeById(int companyNoticeId);
	
	/**
	 * <p>Title: queryNoticeByAuditTime</p>
	 * <p>Description: 分页查询没有审核的通知公告</p>
	 * @param pageNow 当前页码
	 * @param pageSize 每页数量
	 * @return List集合
	 */
	List<NoticeCompany> queryNoticeByAuditTime(int pageNow,int pageSize);
	
	/**
	 * <p>Title: countNoAuditTimeNotice</p>
	 * <p>Description: 统计没有审核的通知的数量</p>
	 * @return 数量
	 */
	int countNoAuditTimeNotice();
	NoticeAdmin queryNoticeAdminById(int adminNoticeId);
	
	
	/**
	 * <p>Title: countNoAuditTimeNotice</p>
	 * <p>Description: 统计学院通知公告数量</p>
	 * @return 数量
	 */
	int countAdminNotice();
	
	/**
	 * <p>Title: queryNoticeByAuditTime</p>
	 * <p>Description: 分页查询学院通知公告</p>
	 * @param pageNow 当前页码
	 * @param pageSize 每页数量
	 * @return List集合
	 */
	List<NoticeAdmin> queryAdminNotice(int pageNow,int pageSize);
	
	/**
	 * <p>Title: updateCompanyNotice</p>
	 * <p>Description: 修改企业发布的通知公告</p>
	 * @param companyNotice 企业通知公告实体类的引用
	 * @return 更新成功返回true，更新失败返回false
	 */
	boolean updateAdminNotic(NoticeAdmin noticeAdmin);
	
	/**
	 * <p>Title: deleteCompanyNotice</p>
	 * <p>Description: 根据公告Id删除企业发布的公告</p>
	 * @param companyNoticeId 企业公告Id
	 * @return 删除成功返回true，删除失败返回false
	 */
	boolean deleteAdminNotic(int adminNoticeId);
	
	/**
	 * <p>Title: provideAnnouncement</p>
	 * <p>Description:发布企业公告 </p>
	 * @param companyNotice 企业通知公告实体类的引用
	 */
	void provideAdminAnnouncement(NoticeAdmin noticeAdmin);
	
	/**
	 * <p>Title: queryAll</p>
	 * <p>Description: 查询出最新发布的十条企业信息公告</p>
	 * @return
	 */
	List<NoticeCompany> queryAllCompanyNoticeOrderByDate(int pageNow,int pageSize);
	
	/**
	 * <p>Title: queryAllAdminNoticeOrderByDate</p>
	 * <p>Description: 查询出最新发布的十条学院通知公告</p>
	 * @return
	 */
	List<NoticeAdmin> queryAllAdminNoticeOrderByDate(int pageNow,int pageSize);
	/**
	 * <p>Title: countAllAuditCompanyNotice</p>
	 * <p>Description: 统计已经被审核的企业通知公告</p>
	 * @return
	 */
	int countAllAuditCompanyNotice();
}

