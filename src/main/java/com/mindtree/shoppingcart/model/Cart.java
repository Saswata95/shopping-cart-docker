package com.mindtree.shoppingcart.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "CART")
public class Cart implements Comparator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "CART_ID")
	private Long cartId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateCreated;
	
	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartOrder> getCartOrder() {
		return cartOrder;
	}

	public void setCartOrder(List<CartOrder> cartOrder) {
		this.cartOrder = cartOrder;
	}



	@OneToOne(cascade = CascadeType.ALL, mappedBy = "cart")
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cp.cart", fetch = FetchType.EAGER)
	private List<CartOrder> cartOrder = new ArrayList<>();
	
	@Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<CartOrder> cartOrder = getCartOrder();
        for (CartOrder co : cartOrder) {
            sum += co.getTotalPrice();
        }

        return sum;
    }
	
	@Transient
	public Integer getTotalProducts() {
		return this.cartOrder.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((cartOrder == null) ? 0 : cartOrder.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (cartOrder == null) {
			if (other.cartOrder != null)
				return false;
		} else if (!cartOrder.equals(other.cartOrder))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
	
	
	

}
