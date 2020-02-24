package com.mindtree.shoppingcart.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="APPAREL")
@PrimaryKeyJoinColumn(name="APPAREL_ID", referencedColumnName = "PRODUCT_ID")
public class Apparel extends Product implements Comparator{
	
	@Column(name = "TYPE")
	private String Type;
	
	@Column(name="BRAND")
	private String Brand;
	
	@Column(name = "DESIGN")
	private String Design;

	public String getType() {
		return Type;
	}
	
	

	public Apparel(long productId, String prodName, float price ,String type, String brand, String design) {
		super(productId, prodName, price);
		this.Type = type;
		this.Brand = brand;
		this.Design = design;
	}



	public Apparel() {
		super();
	}



	public void setType(String type) {
		Type = type;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getDesign() {
		return Design;
	}

	public void setDesign(String design) {
		Design = design;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Brand == null) ? 0 : Brand.hashCode());
		result = prime * result + ((Design == null) ? 0 : Design.hashCode());
		result = prime * result + ((Type == null) ? 0 : Type.hashCode());
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
		Apparel other = (Apparel) obj;
		if (Brand == null) {
			if (other.Brand != null)
				return false;
		} else if (!Brand.equals(other.Brand))
			return false;
		if (Design == null) {
			if (other.Design != null)
				return false;
		} else if (!Design.equals(other.Design))
			return false;
		if (Type == null) {
			if (other.Type != null)
				return false;
		} else if (!Type.equals(other.Type))
			return false;
		return true;
	}



	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
	

}
