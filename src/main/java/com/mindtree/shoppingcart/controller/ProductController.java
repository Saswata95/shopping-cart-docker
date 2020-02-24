package com.mindtree.shoppingcart.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.model.Product;
import com.mindtree.shoppingcart.service.ProductService;

@RestController
@RequestMapping("/api/shopping/product")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	ProductService productservice;

	public ProductController(ProductService productservice) {
		this.productservice = productservice;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Product> listAllProduct() {
		logger.info("Executing List all Product Method");
		return this.productservice.getAllProducts();
	}
	
	@GetMapping("/category")
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Product> listCategoryProduct(@RequestParam String cat) {
		logger.info("Executing List Only Category Products Method");
		return this.productservice.getCategoryProducts(cat);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProductById(@PathVariable long id) {
		logger.info("Executing List Only By Product Id Method");
		return this.productservice.getProduct(id);
	}
	
	@GetMapping("/name/{name}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProductByName(@PathVariable String name) {
		logger.info("Executing List Product By Name Method");
		return this.productservice.getProductName(name);
	}
	
	
}
