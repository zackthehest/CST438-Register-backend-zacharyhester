package com.cst438.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Books {
	
	@Id
	private int book_id;
	private String title;
	private String author;
	private Integer checkout_patron_id;
	private Date checkout_date;

	
	public Books() {
		super();
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCheckOut_Patron_id() {
		return checkout_patron_id;
	}

	public void setCheckOut_Patron_id(Integer checkout_patron_id) {
		this.checkout_patron_id = checkout_patron_id;
	}
	
	public Date getCheckOut_Date() {
		return checkout_date;
	}

	public void setgetCheckOut_Date(Date checkout_date) {
		this.checkout_date = checkout_date;
	}
	

}
