<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login page</title>
<meta charset="utf-8">

</head>
<body>
	<div align="center">
	<h3>sign in</h3>
	  <font color='red'>${invalid}</font><font color='red'>${locked}</font>
		<form action="loginUsr" method="POST">
			<table>
				<tr>
					<td>Email   :</td>
					<td><input type="text" id="email" name="email"/></td>
				</tr>
				
				<tr>
					<td>password  :</td>
					<td><input type="password" id="pswd" name="pswd"/></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="submit" /></td>
				</tr>
				
				<tr>
					<td><a href="frgtPswd" style="margin:20px">forgot password</a>&nbsp;&nbsp;&nbsp;</td>
					<td><a href="regUsr">signin</a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>