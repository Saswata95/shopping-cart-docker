package com.mindtree.shoppingcart.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cart")
public class CartProduct implements Serializable, Comparator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8614193979716976029L;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="CART_ID")
	private Cart cart;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((cart.getCartId() == null)
          ? 0
          : cart
            .getCartId().hashCode());
        result = prime * result + ((product.getProductId() == null)
          ? 0
          : product
            .getProductId()
            .hashCode());

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
        CartProduct other = (CartProduct) obj;
        if (cart == null) {
            if (other.cart != null) {
                return false;
            }
        } else if (!cart.equals(other.cart)) {
            return false;
        }

        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }

        return true;
    }

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
	

}
