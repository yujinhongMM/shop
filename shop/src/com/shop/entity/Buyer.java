package com.shop.entity;

import java.util.Date;

/**
 * 
 * @author YJH
 * @category 买家表
 */
public class Buyer {
	private String buyerId;//用户Id
	private String password;//密码
	private String nickname;//昵称
	private String email;//邮箱
	private Date birthday;//生日
	private String address;//收货地址
	private Integer wallet;//钱包金额
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getWallet() {
		return wallet;
	}
	public void setWallet(Integer wallet) {
		this.wallet = wallet;
	}
	public Buyer(String buyerId, String password, String nickname, String email, Date birthday, String address,
			Integer wallet) {
		super();
		this.buyerId = buyerId;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
		this.wallet = wallet;
	}
	public Buyer() {
		super();
	}
	@Override
	public String toString() {
		return "Buyer [buyerId=" + buyerId + ", password=" + password + ", nickname=" + nickname + ", email=" + email
				+ ", birthday=" + birthday + ", address=" + address + ", wallet=" + wallet + "]";
	}
}
