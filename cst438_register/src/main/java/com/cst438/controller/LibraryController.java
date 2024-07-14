package com.cst438.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.BooksRepository;
import com.cst438.domain.Course;
import com.cst438.domain.Enrollment;
import com.cst438.domain.PatronDTO;
import com.cst438.domain.Books;
import com.cst438.domain.Patrons;
import com.cst438.domain.PatronsRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;


@RestController
@CrossOrigin (origins = {"http://localhost:3000"})
public class LibraryController {
	
    LocalDate localDate = LocalDate.of(2023, 8, 7);
    Date date = Date.valueOf(localDate);
    
	@Autowired
	BooksRepository booksRepository;
	
	@Autowired	
	PatronsRepository patronsRepository;	
	

	
	@GetMapping("/book/{book_id}")
	public String getBook( @PathVariable int book_id  ) {
		
		Books books = booksRepository.findById(book_id).get();
		
		return books.getAuthor();
	}
	
	@GetMapping("/patron/{patron_id}")
	public PatronDTO getPatron( @PathVariable int patron_id  ) {
		
		Patrons patrons = patronsRepository.findById(patron_id).orElse(null);
		
		if (patrons == null) 
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "patronId invalid " );
		else {
		PatronDTO outputPatronDTO = createPatron(patron_id);
		
		
		
		return outputPatronDTO;
		}
	}
	
	
	
	@PutMapping("/book/{bookId}/checkout/{patronId}")
	@Transactional
	public void checkOutBook(@PathVariable String bookId, @PathVariable int patronId) {
		
	
	int intBook = Integer.parseInt(bookId);
		
	Books books = booksRepository.findById(intBook).orElse(null);
		
	
	if (books != null) {

		books.setCheckOut_Patron_id(patronId);
		books.setgetCheckOut_Date(date);
		booksRepository.save(books);
	} else {
		throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "bookId invalid " );
	}
	}

	@PutMapping("/book/{bookId}/return")
	@Transactional
	public void returnBook(@PathVariable String bookId) {
		
	int intBook = Integer.parseInt(bookId);
		
	Books books = booksRepository.findById(intBook).orElse(null);
		
	if (books != null) {

		books.setCheckOut_Patron_id(null);
		books.setgetCheckOut_Date(null);
		booksRepository.save(books);
	} else {
		throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid Number Input not found. " );
	}
	}
	
	
	
	private PatronDTO createPatron(int patronId) {
		
		PatronDTO resultPatron = new PatronDTO();
		resultPatron.patronId = patronId;
		
		Patrons patronNamer  = patronsRepository.findById(patronId).get();
		resultPatron.name = patronNamer.getName();
		
	
		ArrayList<PatronDTO.BookDTO> booksDTOarr = new ArrayList<>();
		
		List<Books> books = booksRepository.findAllNative(patronId);
		for (Books b : books) {
			PatronDTO.BookDTO bookDTO = createBookDTO(b);
			booksDTOarr.add(bookDTO);
		
		}
		
		resultPatron.books = booksDTOarr;
		return resultPatron;

	}
	
	
	private PatronDTO.BookDTO createBookDTO(Books book) {
		PatronDTO.BookDTO bookDTO = new PatronDTO.BookDTO();

		bookDTO.bookId = book.getBook_id();
		bookDTO.title = book.getTitle();
		bookDTO.author = book.getAuthor();
		bookDTO.checkout_patron_id = book.getCheckOut_Patron_id();		
		bookDTO.checkoutDate = book.getCheckOut_Date();
		
		return bookDTO;
	}
	
}