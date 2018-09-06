package com.yjh.practice.model;

import java.sql.Date;

/**
 * 
 * Description
 * @author YJH
 * @date 2018年6月4日  
 *
 */

public class ProjectSelect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectSelectId id;//ProjectSelectId 包含了学生Id和项目Id
	private String selReason;//选题理由
	private Date companySelDate;//企业选择日期
	private String score;//成绩
	private String companyName;//公司名字

	// Constructors

	/** default constructor */
	public ProjectSelect() {
	}

	/** minimal constructor */
	public ProjectSelect(ProjectSelectId id, String selReason,
			String companyName) {
		this.id = id;
		this.selReason = selReason;
		this.companyName = companyName;
	}

	/** full constructor */
	public ProjectSelect(ProjectSelectId id, String selReason,
			Date companySelDate, String score, String companyName) {
		this.id = id;
		this.selReason = selReason;
		this.companySelDate = companySelDate;
		this.score = score;
		this.companyName = companyName;
	}

	// Property accessors

	public ProjectSelectId getId() {
		return this.id;
	}

	public void setId(ProjectSelectId id) {
		this.id = id;
	}

	public String getSelReason() {
		return this.selReason;
	}

	public void setSelReason(String selReason) {
		this.selReason = selReason;
	}

	public Date getCompanySelDate() {
		return this.companySelDate;
	}

	public void setCompanySelDate(Date companySelDate) {
		this.companySelDate = companySelDate;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}