package com.myapp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("in side doGet()");
		//retrieve the paramenters from login page
		
		String userName = request.getParameter("userName");
		String emailId = request.getParameter("emailid");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobbies");
		
		String hobbiesStr = "";
		for (String str: hobbies){
			if("".equals(hobbiesStr))
				hobbiesStr = str ;
			else
				hobbiesStr = hobbiesStr + " , " + str ;
		}
		
		System.out.println(userName + " = " + password);
		System.out.println(emailId + " = " + gender);
		System.out.println(hobbiesStr);
		
		
		String result = "login.html";
		
		
		//transfer control from one page to another
		
		RequestDispatcher rd = request.getRequestDispatcher(result);
		rd.forward(request, response);
		return;
	}
	
	public void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException{
		System.out.println("in side doPost()");
		doGet(request, response);
	}

}
