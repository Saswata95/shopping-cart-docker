package com.mindtree.shoppingcart.dto;

import java.util.List;


public class CartForm {
	
	private List<CartOrderDto> cartOrder;

	public List<CartOrderDto> getCartOrder() {
		return cartOrder;
	}

	public void setCartOrder(List<CartOrderDto> cartOrder) {
		this.cartOrder = cartOrder;
	}

}
