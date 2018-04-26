<!-- isErrorPage variable is require to make the page as exception page -->
<%@ page language="java" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exception</title>
</head>
<body>
	<h1>Exception occurred</h1>

	<strong>Error: </strong>
	<br />

	<%
		exception.getMessage();
	%>

</body>
</html>