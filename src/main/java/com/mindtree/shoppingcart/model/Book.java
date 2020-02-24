package com.mindtree.shoppingcart.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="BOOK")
@PrimaryKeyJoinColumn(name="BOOK_ID", referencedColumnName = "PRODUCT_ID")
public class Book extends Product implements Comparator{
	
	@Column(name = "GENRE")
	private String Genre;
	
	@Column(name="AUTHOR")
	private String Author;
	
	@Column(name = "PUBLICATIONS")
	private String Publications;
	

	public Book(long productId, String prodName, float price, String genre, String author, String publications) {
		super(productId, prodName, price);
		Genre = genre;
		Author = author;
		Publications = publications;
	}
	
	
	public Book() {
		super();
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPublications() {
		return Publications;
	}

	public void setPublications(String publications) {
		Publications = publications;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Author == null) ? 0 : Author.hashCode());
		result = prime * result + ((Genre == null) ? 0 : Genre.hashCode());
		result = prime * result + ((Publications == null) ? 0 : Publications.hashCode());
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
		Book other = (Book) obj;
		if (Author == null) {
			if (other.Author != null)
				return false;
		} else if (!Author.equals(other.Author))
			return false;
		if (Genre == null) {
			if (other.Genre != null)
				return false;
		} else if (!Genre.equals(other.Genre))
			return false;
		if (Publications == null) {
			if (other.Publications != null)
				return false;
		} else if (!Publications.equals(other.Publications))
			return false;
		return true;
	}


	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
	
	

}
