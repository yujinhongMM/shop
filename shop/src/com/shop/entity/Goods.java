package com.shop.entity;



/**
 * 
 * @author zhang
 * @category 商品表
 */
public class Goods {

	private Integer goodId;
	private String name;
	private String city;
	private Integer price;
	private Integer number;
	private String url;
	private String sellerId;
	
	
	


	public Goods(Integer goodId, String name, String city, Integer price, Integer number, String url, String sellerId) {
		super();
		this.goodId = goodId;
		this.name = name;
		this.city = city;
		this.price = price;
		this.number = number;
		this.url = url;
		this.sellerId = sellerId;
	}





	public Integer getGoodId() {
		return goodId;
	}





	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getCity() {
		return city;
	}





	public void setCity(String city) {
		this.city = city;
	}





	public Integer getPrice() {
		return price;
	}





	public void setPrice(Integer price) {
		this.price = price;
	}





	public Integer getNumber() {
		return number;
	}





	public void setNumber(Integer number) {
		this.number = number;
	}





	public String getUrl() {
		return url;
	}





	public void setUrl(String url) {
		this.url = url;
	}





	public String getSellerId() {
		return sellerId;
	}





	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}





	@Override
	public String toString() {
		return "Goods [goodId=" + goodId + ", name=" + name + ", city=" + city + ", price=" + price + ", number="
				+ number + ", url=" + url + ", sellerId=" + sellerId + "]";
	}





	public Goods() {
		super();
	}
	
	
}
