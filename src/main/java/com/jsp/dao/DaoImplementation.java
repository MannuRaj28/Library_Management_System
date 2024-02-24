package com.jsp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.jsp.LMS.entity.Book;
import com.jsp.LMS.entity.Library;
import com.jsp.LMS.entity.Status;

public class DaoImplementation implements Dao {
	static Scanner sc=new Scanner(System.in);
	@Override
	public boolean saveLibrary(Library library) {
		Connection connection=CreateConnection.getconnection();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("insert into library values(?,?,?,?,?,?)");
			preparedStatement.setInt(1, library.getId());
			preparedStatement.setString(2, library.getLibraryLocation());
			preparedStatement.setString(3, library.getLibraryName());
			preparedStatement.setString(4, library.getEmail());
			preparedStatement.setLong(5, library.getPhone());
			preparedStatement.setString(6, library.getLibrarianName());
			int count=preparedStatement.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addBook(Book book) { 
		
		Connection connection=CreateConnection.getconnection();
		try {
			
			PreparedStatement preparedStatement=connection.prepareStatement("insert into book values(?,?,?,?,?,?,?,?);");
			preparedStatement.setInt(1, book.getId());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setDouble(4, book.getPrice());
			preparedStatement.setObject(5, Date.valueOf(book.getPublishedDate()));
			preparedStatement.setObject(6,Date.valueOf(book.getIssuedDate()));
			preparedStatement.setString(7, book.getStatus().toString());
			preparedStatement.setInt(8, book.getLibrary_id());
			int count=preparedStatement.executeUpdate();
			if(count>0) {
				return true;
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
				return false;
	}

	@Override
	public boolean isLibraryIdPresent(int library_id){
		// TODO Auto-generated method stub
		Connection connection=CreateConnection.getconnection();
		try {
			Statement statement=connection.createStatement();
			statement.execute("select library_id from library");
			ResultSet rs=statement.getResultSet();
			while(rs.next()) {
				if(library_id==rs.getInt(1)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int generateLibraryID() {
		// TODO Auto-generated method stub
		Connection connection=CreateConnection.getconnection();
		int id=1;
		try {
			Statement statement=connection.createStatement();
			ResultSet rs=statement.executeQuery("Select MAX(library_id) from library ");
			
			if(rs.next()) {
				id=rs.getInt(1)+1;
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public int generateBookID() {
		// TODO Auto-generated method stub
		Connection connection=CreateConnection.getconnection();
		int id=1;
		try {
			Statement statement=connection.createStatement();
			statement.execute("select MAX(id) from book;");
			ResultSet rs=statement.getResultSet();
			if(rs.next()) {
				id=rs.getInt(1)+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	@Override
	public Book findBookByID(int id) {
		Book book=new Book();
        try (Connection connection=CreateConnection.getconnection();
        		
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    book.setId(id);
                    book.setAuthor(resultSet.getString(3));
                    book.setIssuedDate(resultSet.getDate(6).toLocalDate());
                    book.setLibrary_id(resultSet.getInt(8));
                    book.setPrice(resultSet.getDouble(4));
                    book.setPublishedDate(resultSet.getDate(5).toLocalDate());
                    
                    book.setStatus(Status.valueOf(resultSet.getString(7)));
                    book.setTitle(resultSet.getString(2));
                    
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
	@Override
    public  List<Book> findAllBooksInLibrary(int lib_id) {
        LinkedList<Book> books=new LinkedList<Book>();
        try (Connection connection=CreateConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE library_id = ?")) {

            preparedStatement.setInt(1, lib_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                	Book book=new Book();
                	 book.setId(lib_id);
                     book.setAuthor(resultSet.getString(3));
                     book.setIssuedDate(resultSet.getDate(6).toLocalDate());
                     book.setLibrary_id(resultSet.getInt(8));
                     book.setPrice(resultSet.getDouble(4));
                     book.setPublishedDate(resultSet.getDate(5).toLocalDate());
                     
                     book.setStatus(Status.valueOf(resultSet.getString(7)));
                     book.setTitle(resultSet.getString(2));
                     books.add(book);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


@Override
public List<Book> findBooksByAuthor(String authorName) {
    //List<Book> books = new ArrayList<>();
	LinkedList<Book> books=new LinkedList<Book>();
    try (
    		Connection connection=CreateConnection.getconnection();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE author = ?")) {

        preparedStatement.setString(1, authorName);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	Book book=new Book();
            	book.setId(resultSet.getInt(1));
                book.setAuthor(authorName);
                book.setIssuedDate(resultSet.getDate(6).toLocalDate());
                book.setLibrary_id(resultSet.getInt(8));
                book.setPrice(resultSet.getDouble(4));
                book.setPublishedDate(resultSet.getDate(5).toLocalDate());
                
                book.setStatus(Status.valueOf(resultSet.getString(7)));
                book.setTitle(resultSet.getString(2));
                books.add(book);
            
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return books;
	}
@Override
public int updateLibraryTable(int libID, int choice) {
	// TODO Auto-generated method stub
	int count=0;
	try {
		boolean flagChoice=true;
		Connection connection=new CreateConnection().getconnection();
		while(flagChoice) {
			
			switch(choice) {
			case 1:{
				System.out.println("What is your new library name?");
				count=((Statement) connection).executeUpdate("update library set name='"+sc.nextLine()+"' where library_id="+libID);
				flagChoice=false;
				break;
			}
			case 2:{
				System.out.println("What is your new Location Name for library?");
				count=((Statement) connection).executeUpdate("update library set location='"+sc.nextLine()+"' where lib_id="+libID );
				flagChoice=false;
				break;
			}
			case 3:{
				System.out.println("What is your new Email ID for library");
				count=((Statement) connection).executeUpdate("update library set email='"+sc.nextLine()+"' where lib_id="+libID);
				flagChoice=false;
				break;
			}
			case 4:{
				System.out.println("What is your new phone number for library");
				count=((Statement) connection).executeUpdate("update library set phoneno="+sc.nextLong()+" where lib_id="+libID);
				flagChoice=false;
				break;
			}
			case 5:{
				System.out.println("What is your new Librarian Name");
				count=((Statement) connection).executeUpdate("update library set librarianname='"+sc.nextLine()+"' where lib_id="+libID);
				flagChoice=false;
				break;
			}
			default:{
				System.out.println("You have entered wrong choice, please choose correct option");
			}
		}
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
	}

@Override
public int updateBookTable(int id, int choice) {
	// TODO Auto-generated method stub
	int count=0;
	try {
		Statement connection=new CreateConnection().getconnection().createStatement();
		boolean flagChoice=true;
		while(flagChoice) {
			switch(choice) {
			case 1:{
				System.out.println("What Title you want to change?");
				
				count=((Statement) connection).executeUpdate("update book set title='"+sc.nextLine()+"' where id="+id);
				flagChoice=false;
				break;
			}
			case 2:{
				System.out.println("What Author Name you want?");
				
				count=((Statement) connection).executeUpdate("update book set author='"+sc.nextLine()+"' where id="+id);
				flagChoice=false;
				break;
			}
			case 3:{
				System.out.println("What is the new price you want");
				count=((Statement) connection).executeUpdate("update book set price="+sc.nextDouble()+" where id="+id);
				flagChoice=false;
				break;
			}
			case 4:{
				System.out.println("What is the new published Date in yyyy-mm-dd");
				
				count=((Statement) connection).executeUpdate("update book set publisheddate='"+Date.valueOf(LocalDate.parse(sc.nextLine()))+"' where bookid="+id);
				flagChoice=false;
				break;
			}
			case 5:{
				System.out.println("What is the new issued Date in yyyy-mm-dd");
			
				count=((Statement) connection).executeUpdate("update book set issueddate='"+Date.valueOf(LocalDate.parse(sc.nextLine()))+"' where bookid="+id);
				flagChoice=false;
				break;
			}
			case 6:{
				
				boolean flagStatus=true;
				while(flagStatus) {
					System.out.println("Choose the status you want to change \n1. Available \n2. Issued \n3. Lost");
					switch(sc.nextInt()) {
					case 1:{
						
						count=((Statement) connection).executeUpdate("update book set status='"+Status.AVAILABLE.toString()+"' where bookid="+id);
						flagStatus=false;
						flagChoice=false;
						break;
					}case 2:{
						
						count=((Statement) connection).executeUpdate("update book set status='"+Status.ISSUED.toString()+"' where bookid="+id);
						flagStatus=false;
						flagChoice=false;
						break;
					}
					case 3:{
						
						count=((Statement) connection).executeUpdate("update book set status='"+Status.LOST.toString()+"' where bookid="+id);
						flagStatus=false;
						flagChoice=false;
						break;
					}
					default:{
						System.out.println("Wrong Choice Entered, Please select from below only");
						
						
					}
				}
				}
				
			}
			case 7:{
				System.out.println("Which lib_id now you want to update");
				count=((Statement) connection).executeUpdate("update book set lib_id="+sc.nextInt()+" where bookid="+id);
				flagChoice=false;
				break;
			}
			
			default:{
				System.out.println("Wrong Choice Entered, Choose Correct Option");
			}
		}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
	}
@Override
public boolean isBookIdPresent(int bookId) {
	// TODO Auto-generated method stub
	Connection connection=CreateConnection.getconnection();
	try {
	Statement st=connection.createStatement();
	st.execute("select id from book;");
	ResultSet rs=st.getResultSet();
	while(rs.next()) {
		if(bookId==rs.getInt(1)) {
			return true;
			}
		}
	
} catch (SQLException e) {
	e.printStackTrace();
}

	return false;
	
	}
}


