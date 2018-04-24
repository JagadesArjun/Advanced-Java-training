package com.myapp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AuthenticationFilter class is to check if the user is logged in or not. if
 * the user not logged in redirect to the login page.
 */
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		// To check if the user is signed in or not
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		HttpSession session = request.getSession();

		// To get the request URL
		String uri = request.getRequestURI();
		System.out.println("AuthenticationFilter:Url: " + uri);

		// Check if session object is true || url equals to login.jsp or lofinServlet,
		// else redirect to login page
		if (session.getAttribute("userLoggedIn") != null || (uri.contains("login.jsp")) || uri.contains("loginServlet")
				|| uri.contains("registerServlet") || (uri.contains("register.html"))) {
			chain.doFilter(arg0, arg1);
			return;
		} else {
			// User not logged in, so redirect to the login page
			// You can use the request dispatcher also to transfer the control to login
			// page.
			response.sendRedirect("login.jsp");
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
