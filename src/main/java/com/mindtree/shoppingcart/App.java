package com.mindtree.shoppingcart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mindtree.shoppingcart.model.Apparel;
import com.mindtree.shoppingcart.model.Book;
import com.mindtree.shoppingcart.service.ProductService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableSwagger2
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    CommandLineRunner runner(ProductService productservice) {
    	return args -> {
    		productservice.save(new Apparel(1L, "Jeans", 1200f, "Trousers", "Levis", "New"));
    		productservice.save(new Apparel(2L, "Shirt", 800f, "T-Shirts", "Arrow", "Old"));
    		productservice.save(new Book(3L, "Harry Potter", 600f, "Fantasy", "J.K Rowling", "Bloomsburry Publishing"));
    		productservice.save(new Book(4L, "The Alchemist", 500f, "Drama", "Paulo Coelho", "Harper Collins"));
    	};
    }
    
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
