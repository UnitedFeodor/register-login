<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> You've successfully registered!</h1>
	<p>Username: <%= request.getParameter("username") %></p>
	<p>Password: <%= request.getParameter("password") %></p>
	<a href="Login.jsp"> GO BACK TO LOGIN FORM</a>
</body>
</html>