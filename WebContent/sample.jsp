<!-- JSP page directives -->
<!-- To handle JSP exception, add error variable in JSP directives -->
<%@ page language="java" import="java.util.List, java.util.ArrayList"
	errorPage="exceptionPage.jsp"%>

<%@ taglib uri="tlds/mytags" prefix="mytags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSPExamples</title>
</head>
<body>

	<h1>JSP Examples</h1>
	<!-- HTML Comment -->

	<%-- JSP Comment --%>

	<%-- Example of declaration --%>

	<%!int a = 10;
	int b = 20;

	public int Sum(int i, int j) {
		return i + j;
	}%>

	<%=Sum(a, b)%>

	<%-- Scriptlets section -- business login --%>

	<%
		int val = Sum(a, b);
		out.println(val);
	%>

	<!-- JSP's are always converted into servlet by we servers -->
	<!-- Lifecycle methods of JSP -> jspInit(), _jspService(), jspDestroy() in the converted servlet -->

	<!-- JSP Directives - page, include and taglib -->
	<!-- Directives can be used to import packages -->
	<%--
		List<Integer> newErrList = null;
		out.println(newErrList.size());
	--%>

	<!-- to include any file into your curent page -->
	<%@ include file="index.html"%>

	<br />
	<br />
	<!-- JSP Actions -->
	<!-- Create instance to set and get properties -->

	<jsp:useBean id="p" class="com.myapp.utils.Product" />
	<%-- to set property --%>
	<jsp:setProperty property="description" name="p"
		value="mobile product description" />
	<jsp:setProperty property="price" name="p" value="2000" />

	<%-- to get property --%>
	<jsp:getProperty property="description" name="p" />
	<jsp:getProperty property="price" name="p" />
	<br />
	<br />

	<!-- JSP Implicit Object -> request, response, session, application(ServletContext), config, out, exception, page(this), pageContext -->
	<strong>PageContext: </strong>
	<%=request.getParameter("id")%>

	<br />
	<br />
	<!-- Expression langauge -->
	<!-- param, requestScope, sessionScope, applicationScope, header -->
	<strong>Expression Language: </strong>
	<br /> ${param['id'] } -> request.getParameter
	<br /> ${requestScope['id'] } -> request.getAttribute
	<br /> sessionScope -> session.getAttribute
	<br /> application -> application.getAttribute or
	servletContext.getAttribute
	<br />
	<br />

	<!-- JSP Custom Tags (Using taglib directive) -->
	<!-- 
	Advantage: Avoid writing java code in JSP pages
	Reusability: use the tags in multiple pages reduces code
	 -->

	<strong>Custom tags: </strong>
	<mytags:formatNumber number="1982.12" format="#,###.00" />
	<br />


</body>
</html>