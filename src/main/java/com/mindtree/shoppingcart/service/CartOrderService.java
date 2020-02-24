package com.mindtree.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.mindtree.shoppingcart.model.CartOrder;
import com.mindtree.shoppingcart.model.CartProduct;

@Validated
public interface CartOrderService {
	
	CartOrder create(@NotNull(message = "The Produc Order Cannot be Null") @Valid CartOrder cartOrder);
	
	Optional<CartOrder> getCartOrder(CartProduct cp);
	
	void delCartOrder(CartProduct cp);
	
	void deleteAll(@NotNull(message = "The Produc Order Cannot be Null") @Valid List<CartOrder> cartOrder);

}
