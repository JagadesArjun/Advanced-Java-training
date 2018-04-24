package com.myapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHeadersServlet extends HttpServlet{


	public void doGet(HttpServletRequest request,
			HttpServletResponse response) 
				throws ServletException, IOException{
	
		PrintWriter pw = response.getWriter();
		Enumeration<String> headerNames = 
						request.getHeaderNames();
		
		while (headerNames.hasMoreElements()){
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName + " = " + headerValue);
			pw.println(headerName + " = " + headerValue );
		}
		pw.close();

	}
	

	public void doPost(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException{
		doGet(request,response);
	}
}
