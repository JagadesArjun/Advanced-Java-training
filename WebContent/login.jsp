<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<!-- Display the information in jsp using below tags -->
<!-- JSP expressions -->


<%= request.getAttribute("loginErrorMessage")==null?"":request.getAttribute("loginErrorMessage") %>
<br/>
<%= request.getParameter("userName")==null?"":request.getParameter("userName")%>
<br/>

<%= request.getParameter("password")==null?"":request.getParameter("password")%>
<br/>

<!--  Business logic tags - sciptlet tags -->
<!--  out is same as PrintWriter object -->
<% 
	String userLoggedIn = (String) session.getAttribute("userLoggedIn");
	if("true".equals(userLoggedIn)) {
		out.println("User already logged in........");
%>

	<a href="signoutservlet">Sign out</a>
<%
	} else {
%>

<form name="loginForm" action="loginServlet" method="post">
	<h1>Login</h1>
	User name <input type="text" name="userName"/> <br/>
	Password <input type="password" name="password"/> <br/>
	
	<input type="submit" value="Login"/>

</form>
<% } %>

</body>
</html>