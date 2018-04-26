package com.myapp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Annotation
 * 
 * Instead of adding servlet in web.xml, using the @WebServlet annotation to create a servlet 
 */
@WebServlet
public class Signoutservlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Remove the session atrribute
		session.removeAttribute("userLoggedIn");

		// This will destroy the session
		session.invalidate();

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		return;
	}
}
