package com.myapp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in side doGet()");
		// retrieve the paramenters from login page

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		System.out.println(userName + " = " + password);

		HttpSession session = request.getSession();
		// user information will be available to all requests in that particular browser
		// sessions

		// We need to fire a database request to validate user exists in db or not..
		// business logic

		String result = "";
		if ("Admin".equals(userName) && "password".equals(password)) {
			session.setAttribute("userLoggedIn", "true");
			result = "displayProducts.html";
		} else {
			String message = "Invalid Login!!! Please try Again.";
			request.setAttribute("loginErrorMessage", message);
			result = "login.jsp";
		}

		// transfer control from one page to another

		RequestDispatcher rd = request.getRequestDispatcher(result);
		rd.forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in side doPost()");
		doGet(request, response);
	}

}
