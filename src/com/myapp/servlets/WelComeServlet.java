package com.myapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class WelComeServlet implements Servlet{

	private ServletConfig config = null;
	public WelComeServlet(){
		System.out.println("WelComeServlet: Constructor method called");
	}
	
	//Each Servlet will have its own ServletConfig instance
	//ServletConfig is used to retrieve the initialization parameters
	public ServletConfig getServletConfig() {
		return config;
	}
	
	// Purpose: some initialization logic like "connect to database"
	public void init(ServletConfig config) throws ServletException {
		System.out.println("WelComeServlet: init() method called");
		this.config = config;
	}

	// Purpose: Business logic for a servlet will come here for each servlet
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("WelComeServlet: Service() method called");
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<h1>Welcome Servlet</h1>");
		pw.println("<br/>");
		
		Enumeration<String> initParams = this.getServletConfig().getInitParameterNames();
		while(initParams.hasMoreElements()){
			String paramName = initParams.nextElement();
			String paramValue = this.getServletConfig().getInitParameter(paramName);
			pw.println("Param Name: " + paramName + " Param Value:" + paramValue + " <br/>");
		}
		
		pw.println("<a href='index.html'>Go to Main Page</a>");
		pw.close();
	}
	
	// Purpose: will be called by tomcat before restarting the server or before destruction
	// we can perform some pre destroy operation like unlink the "connection to database"
	public void destroy() {
		System.out.println("WelComeServlet: Destroy() method called");
	}

	

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
