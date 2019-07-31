package com.registration;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("TEST CONNECTION");
	String email=req.getParameter("mail");
	String	password=req.getParameter("pwd");
    String  mobile =req.getParameter("mob");
    String  city= req.getParameter("city");
    
   // jdbc logic starts from here
    
    
		
			 try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("class name  loaded");
			          
					java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","1000");
					System.out.println("connection retrived");
					
				PreparedStatement ps=connection.prepareStatement("insert into servlet_table values(?,?,?,?)");
				ps.setString(1, email);
				ps.setString(2, password);
				ps.setString(3, mobile);
				ps.setString(4, city);
				
				int result= ps.executeUpdate();
			  System.out.println("successfully registered");
			 }
					
			catch (Exception e) {
			e.printStackTrace();
			System.out.println("INSIDE OF CATCH BLOCK");
		}
		
			 System.out.println(email+"_ _"+password+"_ _"+mobile+"_ _"+city+"_ _");
			 req.getRequestDispatcher("Login.jsp").forward(req, resp);
			 }
}



