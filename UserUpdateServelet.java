package com.sowji.user.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/updateServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		  connection=DriverManager.getConnection("jdbc:mysql://localhost/mydb2","root","Sowji@3954");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("doPost()");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	try {
		java.sql.Statement statement = connection.createStatement();
		int result = statement.executeUpdate("update user set password='"+password+"'where email='"+email+"'");
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.print("<H1>password updated</H1>");
		} else{
			out.println("<H2>Error creating the user</H1>");
		}
		}catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

}
}



