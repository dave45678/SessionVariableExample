<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>
<p>The session contains the record count: There are: <%= session.getAttribute("recordcount") %> records.</p>
<p>you can also use expression language: There are ${recordcount} records</p>
<p>I can also view the session from another <a href="output2.jsp">page</a></p>


</body>
</html>