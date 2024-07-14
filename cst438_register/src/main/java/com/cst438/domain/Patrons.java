package com.cst438.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patrons {
	
	@Id
	private int patron_id;
	private String name;

	
	public Patrons() {
		super();
	}

	public int getPatron_id() {
		return patron_id;
	}

	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
