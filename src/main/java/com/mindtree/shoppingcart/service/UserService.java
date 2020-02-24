package com.mindtree.shoppingcart.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.mindtree.shoppingcart.model.User;

@Validated
public interface UserService {
	
	@NotNull Iterable<User> getAllUser();
	
	User getUser(@Min(value = 1L, message = "Invalid cart ID.") long id);
	
	User getUserName(String name);
	
	User save(User user);

}
