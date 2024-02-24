package com.jsp.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jsp.LMS.entity.Book;
import com.jsp.LMS.entity.Library;
import com.jsp.LMS.entity.Status;
import com.jsp.dao.Dao;
import com.jsp.dao.DaoImplementation;

public class View {
	public static void main(String[] args) {
		Dao dao=new DaoImplementation();
		boolean stop=true;
		Scanner scanner=new Scanner(System.in);
		while(stop) {
		System.out.println("Welcome to library management system");
		System.out.println("1.To save library");
		System.out.println("2.Add book to library");
		System.out.println("3.Find book id");
		System.out.println("4.Find all books by Library id");
		System.out.println("5.Find book by Author Name");
		System.out.println("6.Update Details");
		System.out.println("Enter Choice");
		int choice=scanner.nextInt();
		switch(choice) {
		
		case 1:
			Library library=new Library();
			//System.out.println("enter library_id");
			//library.setId(scanner.nextInt());
			
			System.out.println("enter libraryName");
			scanner.nextLine();
			library.setLibraryName(scanner.nextLine());

			System.out.println("enter libraryLocation");
			library.setLibraryLocation(scanner.next());
			
			System.out.println("enter email");
			library.setEmail(scanner.next());
			
			System.out.println("enter phone");
			library.setPhone(scanner.nextLong());
			
			System.out.println("enter librarian name");
			scanner.nextLine();
			library.setLibrarianName(scanner.nextLine());
			library.setId(dao.generateLibraryID());
			if(dao.saveLibrary(library)) {
				System.out.println("Library added successfully");
			}
			
			//dao.saveLibrary(library);
			
			break;
		
			
		case 2:
			System.out.println("How many book you want to keep");
			int count=scanner.nextInt();
			while(count>0) {
			System.out.println("In which library id you want to keep book");
			int id=scanner.nextInt();
			
			if(dao.isLibraryIdPresent(id)) {
			Book book=new Book();
			book.setId(dao.generateBookID());
					
			System.out.println("enter book title");
			scanner.nextLine();
			book.setTitle(scanner.nextLine());
			
			System.out.println("enter author name");
			
			book.setAuthor(scanner.nextLine());
			
			System.out.println("enter price");
			book.setPrice(scanner.nextDouble());
			
			System.out.println("enter published date yyyy-mm-dd");
			book.setPublishedDate(LocalDate.parse(scanner.next()));
			
			System.out.println("enter issued date yyyy-mm--dd");
			book.setIssuedDate(LocalDate.parse(scanner.next()));
		
			
			
			System.out.println("select the status \n1.LOST \n2ISSUED \n3AVAILABLE;");
			int option=scanner.nextInt();
			
			switch(option) {
			case 1:{
				book.setStatus(Status.LOST);
				break;
			}
				
			case 2:{
				book.setStatus(Status.ISSUED);
				break;
			}
				
			case 3:{
				book.setStatus(Status.AVAILABLE);
				break;
			}
			default:
				break;
			}
			book.setLibrary_id(id);
			
			if(dao.addBook(book)) {
				System.out.println("Book added successfully");
			}
			count--;
			}
			else {
				System.out.println("Library is not build yet,select another");
			}
		
		}
			break;
		case 3:
			System.out.println("Enter book id");
			int bookId=scanner.nextInt();
			Book foundBook=dao.findBookByID(bookId);
			if(foundBook!=null) {
				System.out.println("Found Book: " +foundBook);
			}
			else {
				System.out.println("Book not found");
			}
			break;
			
		case 4:
			System.out.println("Enter Library Id");
			int libraryIdForBooks=scanner.nextInt();
			List<Book> booksInLibrary=dao.findAllBooksInLibrary(libraryIdForBooks);
			System.out.println("Books in the library");
			for(Book b: booksInLibrary) {
				System.out.println(b);
			}
			break;
			
		case 5:
			System.out.println("Enter author name");
			String author=scanner.next();
			List<Book> booksByAuthor=dao.findBooksByAuthor(author);
			System.out.println("Books by the author");
			for(Book b :booksByAuthor) {
				System.out.println(b);
			}
			break;
		case 6:{
			System.out.println("Where you want to update, Choose \n1. For Library \n2. For Book");
			int choice2=scanner.nextInt();
			switch(choice2) {
			case 1:{
				System.out.println("Which Library ID you want to update");
				int choice3=scanner.nextInt();
				if(dao.isLibraryIdPresent(choice3)) {
					System.out.println("In Library What you want to change? \n1. Name of Library \n2. Location of Library \n3. Email Id of Library \n4. Phone number of library \n5. Librarian Name of library");
					int choice4=scanner.nextInt();
					if(dao.updateLibraryTable(choice3, choice4)>0) {
						System.out.println("Updated Successfully");
					}
				}
				else {
					System.out.println("No Library present at this library id");
				}
				break;
			}
			case 2:{
				System.out.println("Which book ID you want to update?");
				int choice3=scanner.nextInt();
				if(dao.isLibraryIdPresent(choice3)) {
					System.out.println("In Book What you want to change? \n1. Title \n2. Author \n3. Price \n4. Published Date \n5. Issued Date \n6. Status \n7. Library ID");
					int choice4=scanner.nextInt();
					if(dao.updateBookTable(choice3, choice4)>0) {
						System.out.println("Updated Successfully");
					}
				}else {
					System.out.println("No Book present at this book id");
				}
				break;
			}
			default:{
				break;
			}
			}
		}
		default: break;
		}
		
		System.out.println("Do you want to continue yes or no");
		if(scanner.next().equals("no")) {
			stop=false;
		}
	}
	
		
	}
}


