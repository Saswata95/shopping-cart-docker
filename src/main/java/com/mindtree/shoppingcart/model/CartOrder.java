package com.mindtree.shoppingcart.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartOrder implements Comparator {
	
	@EmbeddedId
	@JsonIgnore
	private CartProduct cp;
	
	@Column(nullable = false)
	private Integer quantity;

	public CartOrder() {
		super();
	}
	
	public CartOrder(Cart cart, Product product, Integer quantity) {
		
		cp = new CartProduct();
		cp.setCart(cart);
		cp.setProduct(product);
		this.quantity = quantity;
	}

	public CartProduct getCp() {
		return cp;
	}

	public void setCp(CartProduct cp) {
		this.cp = cp;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Transient
	public Product getProduct( ) {
		return this.cp.getProduct();
	}
	
	@Transient
	public int getTotalPrice() {
		return (int) (getProduct().getPrice() * getQuantity());
	}
	
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cp == null) ? 0 : cp.hashCode());

        return result;
    }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CartOrder other = (CartOrder) obj;
        if (cp == null) {
            if (other.cp != null) {
                return false;
            }
        } else if (!cp.equals(other.cp)) {
            return false;
        }

        return true;
    }

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
	
	

}
