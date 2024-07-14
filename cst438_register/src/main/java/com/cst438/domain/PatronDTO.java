package com.cst438.domain;

import java.sql.Date;
import java.util.List;


public class PatronDTO {
	
	public static class BookDTO {

		public int bookId;
		public String title;
		public String author;
		public int checkout_patron_id;
		public Date checkoutDate;
		
		}
	
	
	public int patronId;
	public String name;
	public List<BookDTO> books;
	
	}
