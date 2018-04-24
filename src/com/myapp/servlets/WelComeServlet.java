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
		System.out.println("WelComeServlet Constructor..");
	}
	
	//Each Servlet will have its own ServletConfig instance
	//ServletConfig is used to retrieve the initialization parameters
	
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return config;
	}
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init method calling");
		System.out.println("purpose: some initialization logic like database");
		this.config = config;
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("Service method.. business logic for each request");
		//Byusiness logic....
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<h1>Welcome Servlet</h1>");
		pw.println("<br/>");
		
		Enumeration<String> initParams = this.getServletConfig().getInitParameterNames();
		while(initParams.hasMoreElements()){
			String paramName = initParams.nextElement();
			String paramValue = this.getServletConfig().getInitParameter(paramName);
			pw.println("Param Name:" + paramName + " Param Value:" + paramValue + " <br/>");
		}
		
		pw.println("<a href='index.html'>Go to Main Page</a>");
		pw.close();
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Welcome Servlet Destroy method..");
	}

	

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
