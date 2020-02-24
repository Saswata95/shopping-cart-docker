package com.mindtree.shoppingcart.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.model.CartOrder;
import com.mindtree.shoppingcart.model.CartProduct;
import com.mindtree.shoppingcart.repository.CartOrderRepository;

@Service
@Transactional(rollbackFor = ResourceNotFoundException.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CartOrderServiceImpl implements CartOrderService {

	private CartOrderRepository cartOrderRepository;
	
	public CartOrderServiceImpl(CartOrderRepository cartOrderRepository) {
		this.cartOrderRepository = cartOrderRepository;
	}

	@Override
	public CartOrder create(CartOrder cartOrder) {
		// TODO Auto-generated method stub
		return cartOrderRepository.save(cartOrder);
	}

	@Override
	public void deleteAll(List<CartOrder> cartOrder) {
		// TODO Auto-generated method stub
		cartOrderRepository.deleteAll(cartOrder);
	}

	@Override
	public Optional<CartOrder> getCartOrder(CartProduct cp) {
		// TODO Auto-generated method stub
		return cartOrderRepository.findById(cp);
	}

	@Override
	public void delCartOrder(CartProduct cp) {
		// TODO Auto-generated method stub
		cartOrderRepository.deleteById(cp);
	}
	
	
	
	

}
