package com.myapp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	// This is used to find if user already exist or not
	public boolean validateUser(HttpServletRequest request, String userName, String password) throws Exception {
		System.out.println("LoginServlet: Validate user");

		boolean found = false;

		ServletContext context = request.getServletContext();
		String driverName = context.getInitParameter("driverName");
		String dbUrl = context.getInitParameter("dburl");
		String dbUsername = context.getInitParameter("username");
		String dbPassword = context.getInitParameter("password");

		try {
			// Copy mysql-connector.jar to AppMainFolder -> WebContent -> WEB-INF -> lib
			// This step is required to connect to database.
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			Statement st = con.createStatement();
			String query = "select * from user where username='" + userName + "' and password='" + password + "'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				System.out.println("LoginServlet: User found in db");
				found = true;
			} else {
				System.out.println("LoginServlet: User not found in db");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return found;
	}

	// this method called if user do GET /loginServlet calls
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet: doGet() called");

		// Retrieve context params
		ServletContext context = request.getServletContext();
		System.out.println("LoginServlet: DriverName: " + context.getInitParameter("driverName"));
		System.out.println("LoginServlet: DBURL: " + context.getInitParameter("dburl"));
		System.out.println("LoginServlet: Username: " + context.getInitParameter("username"));
		System.out.println("LoginServlet: Password: " + context.getInitParameter("password"));

		// To set new context attribute
		// This attributes will be globally available for application for all users and
		// all requests
		context.setAttribute("message", "New Attribute");

		// Retrieve the parameters from login page
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		System.out.println("LoginServlet: Username: " + userName + ", Password: " + password);

		HttpSession session = request.getSession();
		// user information will be available to all requests in that particular browser
		// sessions

		// This method will check if the user exist in database or not.
		boolean found = false;
		try {
			found = validateUser(request, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String result = "";
		if (found == true) {
			session.setAttribute("userLoggedIn", "true");
			result = "displayProducts.jsp";

			// will create a cookie and store the logged in information in the cookie

			Cookie userNameCookie = new Cookie("username", userName);
			Cookie passwordCookie = new Cookie("password", password);

			userNameCookie.setMaxAge(30 * 60); // seconds

			// Add cookies to respose, so that browser will save the cookie
			response.addCookie(userNameCookie);
			response.addCookie(passwordCookie);

		} else {
			String message = "Invalid Login!!! Please try Again.";
			request.setAttribute("loginErrorMessage", message);
			result = "login.jsp";
		}

		// Redirect of pages will happen here

		RequestDispatcher rd = request.getRequestDispatcher(result);
		rd.forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet: doPost() called");
		doGet(request, response);
	}

}
