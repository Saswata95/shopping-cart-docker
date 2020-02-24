package com.mindtree.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.dto.CartForm;
import com.mindtree.shoppingcart.dto.CartOrderDto;
import com.mindtree.shoppingcart.dto.CreateUserDto;
import com.mindtree.shoppingcart.model.Cart;
import com.mindtree.shoppingcart.model.CartOrder;
import com.mindtree.shoppingcart.model.Product;
import com.mindtree.shoppingcart.model.User;
import com.mindtree.shoppingcart.service.CartOrderService;
import com.mindtree.shoppingcart.service.CartService;
import com.mindtree.shoppingcart.service.ProductService;
import com.mindtree.shoppingcart.service.UserService;

import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.exception.ValidationException;

@RestController
@RequestMapping("/api/shopping/user")
public class OrderController {

	ProductService productservice;
	CartService cartservice;
	UserService userservice;
	CartOrderService cartorderservice;
	
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	public OrderController(ProductService productservice, CartService cartservice, UserService userservice, CartOrderService cartorderservice) {
		this.productservice = productservice;
		this.cartservice = cartservice;
		this.userservice = userservice;
		this.cartorderservice = cartorderservice;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody CreateUserDto createuser) {
		logger.info("Starting User creation Method For User : " + createuser.getUsername());
		User user = new User();
		Cart cart = new Cart();
		cart = this.cartservice.create(cart);
		user.setName(createuser.getUsername());
		user.setCart(cart);
		logger.info("Ending User creation Method!");
		return this.userservice.save(user);
	}
	
	@PostMapping("/{username}/cart/addProducts")
	public ResponseEntity<Cart> createCart(@PathVariable String username, @RequestBody CartForm cartForm) {
		logger.info("Starting Adding or Updating Product/Products to the cart For User : " + username);
		List<CartOrderDto> forms = cartForm.getCartOrder();
		validateProducts(forms);
		User user = this.userservice.getUserName(username);
		Cart cart = user.getCart();
		List<CartOrder> cartorders = cart.getCartOrder();
		if ( null == cart.getCartOrder() || cart.getCartOrder().isEmpty()) {
			cartorders = new ArrayList<CartOrder>();
			for (CartOrderDto cartordersdt : forms) {
				logger.debug("Creating Order With Product: " + cartordersdt.getProduct().getProdName());
				if( cartordersdt.getQuantity() <= 0) {
					throw new ValidationException("Quantity cannot be 0 or Negative!");
				}
				cartorders.add(cartorderservice.create(new CartOrder(cart,
						productservice.getProduct(cartordersdt.getProduct().getProductId()), cartordersdt.getQuantity())));
			}
		}
		else {
			for (CartOrderDto cartordersdt : forms) {
				logger.debug("Updating Order With Product: " + cartordersdt.getProduct().getProdName());
				if( cartordersdt.getQuantity() < 0) {
					throw new ValidationException("Quantity cannot be 0 or Negative!");
				}
				CartOrder cartorderpres = cartorders.stream().filter( co -> cartordersdt.getProduct().getProductId().equals(co.getProduct().getProductId())).findAny().orElse(null);
				if (null == cartorderpres) {
					cartorders.add(cartorderservice.create(new CartOrder(cart,
							productservice.getProduct(cartordersdt.getProduct().getProductId()), cartordersdt.getQuantity())));
					continue;
				}
				else if (cartordersdt.getQuantity() == 0) {
					cartorderservice.delCartOrder(cartorderpres.getCp());
					continue;
				}
				else {
					cartorderpres.setQuantity(cartorderpres.getQuantity() + cartordersdt.getQuantity());
					cartorders.add(cartorderservice.create(cartorderpres));
				}
			}
		}
		
		
		cart.setCartOrder(cartorders);
		logger.info("Ending Adding or Updating Product/Products tot the cart!");
		return new ResponseEntity<Cart>(this.userservice.getUserName(username).getCart(), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{username}/cart/delAll")
	public ResponseEntity<Cart> delAll(@PathVariable String username) {
		logger.info("Starting Delete all Product For User: " + username);
		User user = this.userservice.getUserName(username);
		Cart cart = user.getCart();
		this.cartorderservice.deleteAll(cart.getCartOrder());
		logger.info("Ending Delete All Product Method");
		return new ResponseEntity<Cart>(this.cartservice.getCart(cart.getCartId()), HttpStatus.OK);
		
	}
	
	@GetMapping("/{username}/cart")
	public ResponseEntity<Cart> viewCart(@PathVariable String username) {
		logger.info("Starting View Cart For User: " + username);
		User user = this.userservice.getUserName(username);
		Cart cart = this.cartservice.getCart(user.getCart().getCartId());
		logger.info("Ending View Cart Method");
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{username}/cart/del")
	public ResponseEntity<Cart> delProduct(@PathVariable String username, @RequestBody Product prod) {
		logger.info("Starting Delete a particular Product For User: " + username);
		User user = this.userservice.getUserName(username);
		Cart cart = user.getCart();
		List<CartOrder> cartorders = cart.getCartOrder();
		CartOrder cartorderpres = cartorders.stream().filter( co -> prod.getProductId().equals(co.getProduct().getProductId())).findAny().orElse(null);
		this.cartorderservice.delCartOrder(cartorderpres.getCp());
		logger.info("Ending Delete a Particular Product Method");
		return new ResponseEntity<Cart>(this.cartservice.getCart(cart.getCartId()), HttpStatus.OK);
		
	}
	
	
	
	public void validateProducts(List<CartOrderDto> orderProducts) {
		logger.info("Inside Product Validation Method");
		List<CartOrderDto> list = orderProducts.stream()
				.filter(op -> Objects.isNull(productservice.getProduct(op.getProduct().getProductId())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product is Not Present in The Inventory!");
		}
	}
	
	
}
