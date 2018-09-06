package com.yjh.practice.model;

import java.sql.Date;

public class NoticeAdmin implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private String title;
	private Integer id;
	private Date releaseDate;
	private String content;

	// Constructors

	/** default constructor */
	public NoticeAdmin() {
	}

	/** full constructor */
	public NoticeAdmin(Date releaseDate, String content) {
		this.releaseDate = releaseDate;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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
