package com.mindtree.shoppingcart.service;

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
import com.mindtree.shoppingcart.model.User;
import com.mindtree.shoppingcart.repository.UserRepository;

@Service
@Transactional(rollbackFor = ResourceNotFoundException.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserServiceImpl implements UserService {
	
	private UserRepository userrepository;
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(EntityManagerFactory ef) {
		if(ef.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("Not a proper Hibernate Factory!");
		}
		this.sessionFactory = ef.unwrap(SessionFactory.class);
	}

	public UserServiceImpl(UserRepository userrepository) {
		this.userrepository = userrepository;
	}



	@Override
	public @NotNull Iterable<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.userrepository.findAll();
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return this.userrepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No User Exists"));
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return this.userrepository.save(user);
	}

	@Override
	public User getUserName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<User> query = session.createNamedQuery("User_GetUserByName", User.class);
		query.setParameter("userName", name);
		User result = null;
		try {
			result = query.getSingleResult();
			session.getTransaction().commit();
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			throw new ResourceNotFoundException("No User Available By This Name!");
		}
		
		return result;
	}

}
