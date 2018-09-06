package com.shop.entity;
/**
 * 
 * @author zhang
 * @category 购物车
 */
public class Shopcart {

	private String buyerid;
	private Goods goods;
	private Integer goodsid;
	private Integer number;
	private Integer price;
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Shopcart(String buyerid, Goods goods, Integer goodsid, Integer number, Integer price) {
		super();
		this.buyerid = buyerid;
		this.goods = goods;
		this.goodsid = goodsid;
		this.number = number;
		this.price = price;
	}
	public Shopcart() {
		super();
	}
	
	
	
	
}
