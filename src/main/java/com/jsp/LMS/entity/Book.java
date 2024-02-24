package com.jsp.LMS.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {
	private int id;
	private String title;
	 private String author;
	 private double price;
	private LocalDate publishedDate;
	private LocalDate issuedDate;
	private Status status;
	private int library_id;
	public Book(){
		
	}
	public Book(int id, String title, String author, double price, LocalDate publishedDate, LocalDate issuedDate,
			Status status, int library_id) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publishedDate = publishedDate;
		this.issuedDate = issuedDate;
		this.status = status;
		this.library_id = library_id;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", publishedDate="
				+ publishedDate + ", issuedDate=" + issuedDate + ", status=" + status + ", library_id=" + library_id
				+ "]";
	}
	public int getLibrary_id() {
		return library_id;
	}
	public void setLibrary_id(int library_id) {
		this.library_id = library_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
		
	
}
