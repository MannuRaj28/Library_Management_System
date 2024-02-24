package com.jsp.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BookTable {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://localhost:5432/library_management_system";
			String user="postgres";
			String password="root";
			Connection connection=DriverManager.getConnection(url, user, password);
			Statement statement=connection.createStatement();
			statement.execute("create table book(id int primary key,title varchar(30),author varchar(30),price numeric(10,2),published_date date,issued_date date,status varchar(10),library_id int references library(Library_id))");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
