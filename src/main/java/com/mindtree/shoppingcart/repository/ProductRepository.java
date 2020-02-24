package com.mindtree.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.shoppingcart.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
