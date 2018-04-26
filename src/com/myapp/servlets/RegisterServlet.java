package com.myapp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.utils.Product;
import com.myapp.utils.User;

public class RegisterServlet extends HttpServlet {

	public void insertIntoDB(HttpServletRequest request, String userName, String emailId, String password,
			String gender, String hobbiesStr) {

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
			String query = "insert into demo.user (username, password, emailid, gender, hobbies) values ('" + userName
					+ "', '" + password + "', '" + emailId + "', '" + gender + "', '" + hobbiesStr + "');";
			st.executeUpdate(query);

			System.out.println("RegisterServlet: User registered to db");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<User> getAllUsersFromDB(HttpServletRequest request) {

		List<User> userList = new ArrayList<User>();

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
			String query = "select * from user";
			ResultSet rt = st.executeQuery(query);

			while (rt.next()) {
				User u = new User();
				int id = rt.getInt("id");
				String name = rt.getString("username");
				String emailid = rt.getString("emailid");
				String gender = rt.getString("gender");
				String hobbies = rt.getString("hobbies");

				u.setId(id);
				u.setName(name);
				u.setEmailid(emailid);
				u.setGender(gender);
				u.setHobbies(hobbies);

				userList.add(u);
			}

			System.out.println("RegisterServlet: User registered to db");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet: doGet() called");

		// retrieve the paramenters from login page
		String userName = request.getParameter("userName");
		String emailId = request.getParameter("emailid");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobbies");

		String hobbiesStr = "";
		for (String str : hobbies) {
			if ("".equals(hobbiesStr))
				hobbiesStr = str;
			else
				hobbiesStr = hobbiesStr + " , " + str;
		}

		insertIntoDB(request, userName, emailId, password, gender, hobbiesStr);

		System.out.println(userName + " = " + password);
		System.out.println(emailId + " = " + gender);
		System.out.println(hobbiesStr);

		String result = "login.jsp";

		List<User> userList = getAllUsersFromDB(request);

		request.setAttribute("userList", userList);

		// transfer control from one page to another

		RequestDispatcher rd = request.getRequestDispatcher(result);
		rd.forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet: doPost() called");
		doGet(request, response);
	}

}
