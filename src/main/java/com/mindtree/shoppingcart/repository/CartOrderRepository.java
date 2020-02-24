package com.mindtree.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.shoppingcart.model.CartOrder;
import com.mindtree.shoppingcart.model.CartProduct;

public interface CartOrderRepository extends CrudRepository<CartOrder, CartProduct> {

}
