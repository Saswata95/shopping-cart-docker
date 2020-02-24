package com.mindtree.shoppingcart.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.mindtree.shoppingcart.model.Cart;

@Validated
public interface CartService {
	
	Cart getCart(@Min(value = 1L, message = "Invalid cart ID.") long id);
	
	Cart update(@NotNull(message = "The order cannot be null.") @Valid Cart cart);
	
	Cart create(@NotNull(message = "The order cannot be null.") @Valid Cart cart);

}
