package com.shop.entity;
/**
 * 
 * @author zhang
 * @category 订单详细信息表
 */
public class OrderView {
	private Orders order;
	private Goods goods;
	private Buyer buyer;
	private Seller seller;
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public OrderView(Orders order, Goods goods, Buyer buyer, Seller seller) {
		super();
		this.order = order;
		this.goods = goods;
		this.buyer = buyer;
		this.seller = seller;
	}
	public OrderView() {
		super();
	}
	@Override
	public String toString() {
		return "OrderView [order=" + order + ", goods=" + goods + ", buyer=" + buyer + ", seller=" + seller + "]";
	}
	
}
