package com.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("test login button connection!!");
		String userName = req.getParameter("userName");
		String password = req.getParameter("pwd");
		
		System.out.println(userName  + "---" + password);
		//database logic here
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class name  loaded");
	          
			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","1000");
			System.out.println("connection retrived");
			
		PreparedStatement ps=connection.prepareStatement("select * from servlet_table where email=? and password=?");
		ps.setString(1, userName);
		ps.setString(2, password);
		
		
			ResultSet rs=ps.executeQuery();	
			
			//if rs have data should forwarded to profile page
			if(rs.next()) {
				req.setAttribute("userName",userName);
				req.getRequestDispatcher("Profile.jsp").forward(req , resp);
			} else {
				req.setAttribute("message", "Invalid Credentials entered!! Please check your credentials!!");
				req.getRequestDispatcher("Login.jsp").forward(req, resp);
			}
			// else forward back to login page with invalid credentials message.
			
			System.out.println("Successfully login User!!");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("inside catch block");
		}
		//database logic ends here
	}
}



