<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,java.util.ArrayList, com.myapp.utils.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
</style>
<body>

	<!-- Display the information in jsp using below tags -->
	<!-- JSP expressions -->

	<table>
		<tr>
			<th>Id</th>
			<th>Username</th>
			<th>EmailId</th>
			<th>Gender</th>
			<th>Hobbies</th>
		</tr>

		<%
			List<User> userList = (List<User>) request.getAttribute("userList");
			if (userList != null) {
				for (User user : userList) {
					out.println("<tr><td>" + user.getId() + "</td><td>" + user.getName() + "</td><td> "
							+ user.getEmailid() + "</td><td>" + user.getGender() + "</td><td>" + user.getHobbies()
							+ "</td></tr>" + "<br/>");
				}
			}
		%>
	</table>

	<!-- Retrieve the cookies from browser -->
	<%
		String userNameFromCookie = "";
		String passwordFromCookie = "";

		Cookie cookies[] = request.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();

			if ("username".equals(cookieName)) {
				userNameFromCookie = cookieValue;
			}

			if ("password".equals(cookieName)) {
				passwordFromCookie = cookieValue;
			}
		}
	%>


	<!--  Business logic tags - sciptlet tags -->
	<!--  out is same as PrintWriter object -->
	<%
		String userLoggedIn = (String) session.getAttribute("userLoggedIn");
		if ("true".equals(userLoggedIn)) {
			out.println("User already logged in........");
	%>
	<br />
	<a href="/Signoutservlet">Sign out</a>
	<%
		} else {
	%>

	<form name="loginForm" action="loginServlet" method="post">
		<h1>Login</h1>
		Username <input type="text" name="userName"
			value="<%=userNameFromCookie%>" /> <br /> Password <input
			type="password" name="password" value="<%=passwordFromCookie%>" /> <br />
		<input type="submit" value="Login" />

	</form>
	<%
		}
	%>

	<%=request.getAttribute("loginErrorMessage") == null ? "" : request.getAttribute("loginErrorMessage")%>
	<br />
	<%=request.getParameter("userName") == null ? "" : request.getParameter("userName")%>
	<br />
	<%=request.getParameter("password") == null ? "" : request.getParameter("password")%>
	<br />


	<!-- To get context params use application object -->
	<strong>Context params</strong>
	<br /> driverName:
	<%=application.getInitParameter("driverName") == null ? ""
					: application.getInitParameter("driverName")%>
	<br /> dburl:
	<%=application.getInitParameter("dburl") == null ? "" : application.getInitParameter("dburl")%>
	<br /> username:
	<%=application.getInitParameter("username") == null ? "" : application.getInitParameter("username")%>
	<br /> password:
	<%=application.getInitParameter("password") == null ? "" : application.getInitParameter("password")%>
	<br />

	<br />
	<!-- Print the cookies in browser -->
	<strong>Cookies in browser</strong>
	<br />
	<%
		Cookie cookiesPrint[] = request.getCookies();
		for (Cookie cookie : cookiesPrint) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();

			out.println("cookieName: " + cookieName + " cookieValue: " + cookieValue + "<br/>");
		}
	%>

</body>
</html>