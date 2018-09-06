package com.shop.entity;



/**
 * 
 * @author zhang
 * @category 订单数据表
 */
public class Orders {

	private Integer orderid;
	private String createdate;
	private String status;
	private String buyerid;
	private Integer goodid;
	private Integer number;
	private Integer allmoney;
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public Integer getGoodid() {
		return goodid;
	}
	public void setGoodid(Integer goodid) {
		this.goodid = goodid;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getAllmoney() {
		return allmoney;
	}
	public void setAllmoney(Integer allmoney) {
		this.allmoney = allmoney;
	}
	public Orders(Integer orderid, String createdate, String status, String buyerid, Integer goodid, Integer number,
			Integer allmoney) {
		super();
		this.orderid = orderid;
		this.createdate = createdate;
		this.status = status;
		this.buyerid = buyerid;
		this.goodid = goodid;
		this.number = number;
		this.allmoney = allmoney;
	}
	public Orders() {
		super();
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", createdate=" + createdate + ", status=" + status + ", buyerid="
				+ buyerid + ", goodid=" + goodid + ", number=" + number + ", allmoney=" + allmoney + "]";
	}
	
	
}
