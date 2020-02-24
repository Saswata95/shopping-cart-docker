package com.mindtree.shoppingcart.repository;

import com.mindtree.shoppingcart.model.Cart;
import org.springframework.data.repository.CrudRepository;


public interface CartRepository extends CrudRepository<Cart, Long> {

}
