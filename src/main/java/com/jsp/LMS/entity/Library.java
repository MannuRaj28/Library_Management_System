package com.jsp.LMS.entity;

import java.time.LocalDate;
import java.util.List;

public class Library {
	private int id;
	private String libraryLocation;
	private String libraryName;
	private String email;
	private long phone;
	private String librarianName;
	private List<Book> books;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibraryLocation() {
		return libraryLocation;
	}
	public void setLibraryLocation(String libraryLocation) {
		this.libraryLocation = libraryLocation;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getLibrarianName() {
		return librarianName;
	}
	public void setLibrarianName(String librarianName) {
		this.librarianName = librarianName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
