package com.mindtree.shoppingcart.service;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.model.Apparel;
import com.mindtree.shoppingcart.model.Book;
import com.mindtree.shoppingcart.model.Product;
import com.mindtree.shoppingcart.repository.ProductRepository;

@Service
@Transactional(rollbackFor = ResourceNotFoundException.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productrepository;
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(EntityManagerFactory ef) {
		if(ef.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("Not a proper Hibernate Factory!");
		}
		this.sessionFactory = ef.unwrap(SessionFactory.class);
	}
	
	public ProductServiceImpl(ProductRepository productrepository) {
        this.productrepository = productrepository;
    }

	@Override
	public @NotNull Iterable<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productrepository.findAll();
	}
	
	@Override
	public Product getProductName(String prodName) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Product> query = session.createNamedQuery("Product_findByName", Product.class);
		query.setParameter("productName", prodName);
		Product result;
		try {
			result = query.getSingleResult();
			session.getTransaction().commit();
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			throw new ResourceNotFoundException("No Product Available By This Name!");
		}
		/*
		 * if (null == result) { throw new
		 * ResourceNotFoundException("No Product Available By This Name!"); }
		 */
		return result;
	}

	@Override
	public @NotNull Iterable<Product> getCategoryProducts(String category) {
		// TODO Auto-generated method stub
		ArrayList<Product> result = new ArrayList<Product>();
		for(Product pr : productrepository.findAll()) {
			if (category.equalsIgnoreCase("Book")) {
				if(pr instanceof Book) {
					result.add(pr);
				}
			}
			else if (category.equalsIgnoreCase("Apparel")) {
				if(pr instanceof Apparel) {
					result.add(pr);
				}
			}
			
			
			else {
				throw new ResourceNotFoundException("Wrong Product Category Entered!");
			}
		}
		
		return result;
	}

	@Override
	public Product getProduct(long id) {
		// TODO Auto-generated method stub
		return productrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Is Not Present!"));
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productrepository.save(product);
	}

}
