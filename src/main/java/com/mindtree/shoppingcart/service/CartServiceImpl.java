package com.mindtree.shoppingcart.service;

import java.time.LocalDate;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.model.Cart;
import com.mindtree.shoppingcart.repository.CartRepository;

@Service
@Transactional(rollbackFor = ResourceNotFoundException.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CartServiceImpl implements CartService {

	private CartRepository cartrepository;
	
	public CartServiceImpl(CartRepository cartrepository) {
		this.cartrepository = cartrepository;
	}

	@Override
	public Cart getCart(long id) {
		// TODO Auto-generated method stub
		return cartrepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cart Is Invalid!"));
	}
	
	

	@Override
	public Cart create(Cart cart) {
		// TODO Auto-generated method stub
		cart.setDateCreated(LocalDate.now());
		return cartrepository.save(cart);
	}

	@Override
	public Cart update(Cart cart) {
		// TODO Auto-generated method stub
		return cartrepository.save(cart);
	}

}
