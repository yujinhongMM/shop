package com.shop.entity;
/**
 * 
 * @author YJH
 * @category 管理员
 */
public class Manager {
	private String managerId;
	private String password;
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Manager(String managerId, String password) {
		super();
		this.managerId = managerId;
		this.password = password;
	}
	public Manager() {
		super();
	}
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", password=" + password + "]";
	}
	
}