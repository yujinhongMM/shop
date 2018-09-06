package com.shop.entity;

import java.util.Date;

/**
 * 
 * @author YJH
 * @category 卖家表
 */
public class Seller {
	private String sellerId;//用户Id
	private String password;//密码
	private String shopname;//昵称
	private String email;//邮箱
	private Date birthday;//生日
	private Integer wallet;//钱包金额
	private Integer power;//权限
	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", password=" + password + ", shopname=" + shopname + ", email=" + email
				+ ", birthday=" + birthday + ", wallet=" + wallet + ", power=" + power + "]";
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getWallet() {
		return wallet;
	}
	public void setWallet(Integer wallet) {
		this.wallet = wallet;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Seller(String sellerId, String password, String shopname, String email, Date birthday, Integer wallet,
			Integer power) {
		super();
		this.sellerId = sellerId;
		this.password = password;
		this.shopname = shopname;
		this.email = email;
		this.birthday = birthday;
		this.wallet = wallet;
		this.power = power;
	}
	public Seller() {
		super();
	}
	
}
