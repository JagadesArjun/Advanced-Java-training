<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List, com.myapp.utils.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>ProductList</title>
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

	<h1><%=request.getParameter("type").toUpperCase()%>
		List:
	</h1>
	<a href="displayProducts.jsp">back</a>
	<br />

	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
		</tr>

		<%
			List<Product> productList = (List<Product>) request.getAttribute("productList");
			for (Product product : productList) {
				out.println("<tr><td>" + product.getId() + "</td><td>" + product.getName() + "</td><td> "
						+ product.getDescription() + "</td><td>" + product.getPrice() + "</td></tr>" + "<br/>");
			}
		%>
	</table>

</body>
</html>