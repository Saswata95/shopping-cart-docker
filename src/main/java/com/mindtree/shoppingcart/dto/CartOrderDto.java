package com.mindtree.shoppingcart.dto;

import com.mindtree.shoppingcart.model.Product;

public class CartOrderDto {
	
	//private User user;
	private Product product;
	//private Cart cart;
	private Integer quantity;
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/*
	 * public User getUser() { return user; } public void setUser(User user) {
	 * this.user = user; }
	 */
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	/*
	 * public Cart getCart() { return cart; } public void setCart(Cart cart) {
	 * this.cart = cart; }
	 */
	
	

}
