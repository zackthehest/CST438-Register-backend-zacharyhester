package com.cst438.domain;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BooksRepository extends CrudRepository <Books, Integer> {
	
	/*@Query("select b from books b")
	public List<Books> findAll();*/
	
	@Query(value = "SELECT * FROM books b where b.checkout_patron_id=:patronId", nativeQuery = true)
	List<Books> findAllNative(@Param("patronId") int patronId);
	
}