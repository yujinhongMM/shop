package com.yjh.practice.service.impl;

import java.sql.Date;
import java.util.List;

import com.yjh.practice.dao.NoticeDao;
import com.yjh.practice.dao.impl.NoticeDaoImpl;
import com.yjh.practice.model.NoticeAdmin;
import com.yjh.practice.model.NoticeCompany;
import com.yjh.practice.service.NoticeService;



public class NoticeServiceImpl implements NoticeService {
	private NoticeDao noticeDao = new NoticeDaoImpl();
	/**
	 * 更新通知
	 */
	public boolean updateCompanyNotice(NoticeCompany companyNotice) {
		try {
			if (companyNotice != null ) {
				return this.noticeDao.updateCompanyNotice(companyNotice);
			}
			return false ;
			} catch(Exception exception) {
				return false;
			}
	}

	/**
	 * 根据通知Id删除通知
	 */
	public boolean deleteCompanyNotice(int companyNoticeId) {
		try {
			if (companyNoticeId != 0 ) {
				return this.noticeDao.deleteCompanyNotice(companyNoticeId);
			}
			return false ;
			} catch(Exception exception) {
				return false;
			}
	}

	/**
	 * 发布通知
	 */
	public void provideAnnouncement(NoticeCompany companyNotice) {
		try {
			if (companyNotice != null ) {
				this.noticeDao.provideAnnouncement(companyNotice);
			}
			} catch(Exception exception) {
			}
	}

	/**
	 * 管理员审核通知
	 */
	public boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate) {
		try {
			if (companyNoticeId != 0) {
				return this.noticeDao.reviewCompanyNotice(companyNoticeId, companyAuditDate);
			}
			return false ;
			} catch(Exception exception) {
				return false;
			}
	}

	public List<NoticeCompany> queryNoticeByCompanyName(String companyUserName,int pageNow,int pageSize) {
		try {
			System.out.println(companyUserName);
			if (companyUserName != null && !"".equals(companyUserName)) {
				List<NoticeCompany> list = this.noticeDao.queryNoticeByCompanyName(companyUserName,pageNow,pageSize);
				return list;
			}
			return null;
		} catch(Exception e) {
			return null ;
		}
	}

	
	public int queryAllByName(String companyUserName) {
		try {
			System.out.println(companyUserName);
			int count = 0 ;
			if (companyUserName != null && !"".equals(companyUserName)) {
				count = this.noticeDao.queryAllByName(companyUserName);
			}
			return count;
		} catch(Exception e) {
			return 0 ;
		}
	}

	@Override
	public NoticeCompany queryNoticeById(int companyNoticeId) {
		if (companyNoticeId == 0) {
			throw new NullPointerException("传入参数为空");
		}
		try {
			return this.noticeDao.queryNoticeById(companyNoticeId);
		}catch(Exception e) {
			return null ;
		}
	}

	
	public List<NoticeCompany> queryNoticeByAuditTime(int pageNow, int pageSize) {
		List<NoticeCompany> list = null ;
		try{
			list = this.noticeDao.queryNoticeByAuditTime(pageNow, pageSize);
			return list;
		} catch(Exception e) {
			return null;
		}
	}

	
	public int countNoAuditTimeNotice() {
		try {
			int count = this.noticeDao.countNoAuditTimeNotice();
			return count;
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public NoticeAdmin queryNoticeAdminById(int adminNoticeId) {
		if (adminNoticeId == 0) {
			throw new NullPointerException("传入参数为空");
		}
		try {
			return this.noticeDao.queryNoticeAdminById(adminNoticeId);
		}catch(Exception e) {
			return null ;
		}
	}

	@Override
	public int countAdminNotice() {
		return this.noticeDao.countAdminNotice();
	}

	@Override
	public List<NoticeAdmin> queryAdminNotice(int pageNow, int pageSize) {
		List<NoticeAdmin> list = null ;
		try{
			list = this.noticeDao.queryAdminNotice(pageNow, pageSize);
			return list;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateAdminNotic(NoticeAdmin noticeAdmin) {
		try {
			if (noticeAdmin != null ) {
				return this.noticeDao.updateAdminNotic(noticeAdmin);
			}
			return false ;
			} catch(Exception exception) {
				return false;
			}
	}

	@Override
	public boolean deleteAdminNotic(int adminNoticeId) {
		try {
			if (adminNoticeId != 0 ) {
				return this.noticeDao.deleteAdminNotic(adminNoticeId);
			}
			return false ;
			} catch(Exception exception) {
				return false;
			}
	}

	@Override
	public void provideAdminAnnouncement(NoticeAdmin noticeAdmin) {
		try {
			if (noticeAdmin != null ) {
				this.noticeDao.provideAdminAnnouncement(noticeAdmin);
			}
			} catch(Exception exception) {
			}
	}

	@Override
	public List<NoticeCompany> queryAllCompanyNoticeOrderByDate(int pageNow, int pageSize) {
		List<NoticeCompany> list = null ;
		try{
			list = this.noticeDao.queryAllCompanyNoticeOrderByDate(pageNow, pageSize);
			return list;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<NoticeAdmin> queryAllAdminNoticeOrderByDate(int pageNow, int pageSize) {
		List<NoticeAdmin> list = null ;
		try{
			list = this.noticeDao.queryAllAdminNoticeOrderByDate(pageNow, pageSize);
			return list;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public int countAllAuditCompanyNotice() {
		return this.noticeDao.countAllAuditCompanyNotice();
	}

}

