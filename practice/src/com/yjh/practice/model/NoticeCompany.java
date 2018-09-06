package com.yjh.practice.model;

import java.sql.Date;

public class NoticeCompany implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private String title;
	private Integer id;
	private String companyUsername;
	private Date releaseDate;
	private Date auditDate;
	private String content;

	// Constructors

	/** default constructor */
	public NoticeCompany() {
	}

	/** minimal constructor */
	public NoticeCompany(String companyUsername, Date releaseDate,
			String content) {
		this.companyUsername = companyUsername;
		this.releaseDate = releaseDate;
		this.content = content;
	}

	/** full constructor */
	public NoticeCompany(String companyUsername, Date releaseDate,
			Date auditDate, String content) {
		this.companyUsername = companyUsername;
		this.releaseDate = releaseDate;
		this.auditDate = auditDate;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyUsername() {
		return this.companyUsername;
	}

	public void setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
