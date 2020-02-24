package com.mindtree.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.shoppingcart.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
