<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>REGISTER</h1>
	<form action="controller" method="post" > 
	
		<input type="hidden" name="command" value="register_command" />
		<div>username</div>
		<input type="text" name="username" >
		
		<div>password</div>
		<input type="text" name="password" >
		
		<div></div>
		<input type="submit" value="submit">
	
	</form>
	
	<a href="Login.jsp"> GO TO LOGIN FORM</a>
</body>
</html>