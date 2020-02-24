package com.mindtree.shoppingcart.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.mindtree.shoppingcart.model.Product;

@Validated
public interface ProductService {
	
	@NotNull Iterable<Product> getAllProducts();
	
	@NotNull Iterable<Product> getCategoryProducts(String category);
	
	Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);
	
	Product getProductName(@NotNull(message = "Invalid Product Name") String prodName);

    Product save(Product product);

}
