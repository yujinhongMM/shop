package com.yjh.practice.model;

import java.sql.Date;

/**
 * 
 * Description 企业
 * @author YJH
 * @date 2018年6月2日  
 *
 */

public class Company implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String companyName;
	private String mailbox;
	private String password;
	private String contacts;
	private String phone;
	private String address;
	private String profile;
	private Date auditDate;

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(String username, String companyName, String mailbox,
			String password, String contacts, String phone,Date auditDate) {
		this.username = username;
		this.companyName = companyName;
		this.mailbox = mailbox;
		this.password = password;
		this.contacts = contacts;
		this.phone = phone;
		this.auditDate = auditDate;
	}

	/** full constructor */
	public Company(String username, String companyName, String mailbox,
			String password, String contacts, String phone, String address,
			String profile) {
		this.username = username;
		this.companyName = companyName;
		this.mailbox = mailbox;
		this.password = password;
		this.contacts = contacts;
		this.phone = phone;
		this.address = address;
		this.profile = profile;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMailbox() {
		return this.mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	

}