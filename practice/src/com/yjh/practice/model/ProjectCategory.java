package com.yjh.practice.model;
/**
 * 
 * Description 项目类别
 * @author YJH
 * @date 2018年6月2日  
 *
 */

public class ProjectCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String category;
	private Integer orderno;

	// Constructors

	/** default constructor */
	public ProjectCategory() {
	}

	/** full constructor */
	public ProjectCategory(String category, Integer orderno) {
		this.category = category;
		this.orderno = orderno;
	}

	// Property accessors

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

}