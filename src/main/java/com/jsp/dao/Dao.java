package com.jsp.dao;

import java.time.LocalDate;
import java.util.List;

import com.jsp.LMS.entity.Book;
import com.jsp.LMS.entity.Library;

public interface Dao {
	
	public boolean saveLibrary(Library library);
	public boolean addBook(Book book);
	public boolean isLibraryIdPresent(int lib_id );
	public int generateLibraryID();
	int generateBookID();
	Book findBookByID(int id);
	List<Book> findAllBooksInLibrary(int lib_id);
	List<Book> findBooksByAuthor(String author);
	int updateLibraryTable(int library_id,int choice);
	int updateBookTable(int id,int choice);
	boolean isBookIdPresent(int bookId);

	
	

}
