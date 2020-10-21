<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login page</title>
<meta charset="utf-8">
<body>
	<div align="center">
	<h3>unlock  accunt</h3>
	<font color='red'>${failMsg}</font> <font color='green'>${succMsg}</font>
		<form:form action="unLockAcc?email=${unlockAcc.email}" modelAttribute="unlockAcc" method="POST">
			<table>
			<tr>
			<td>Email Id:</td>
			<td>${unlockAcc.email}</td>
			</tr>
			
			<tr>
			<td>Temporary Pswd:</td>
			<td><form:input path="temPswd"/></td>
			</tr>
			
			<tr>
			<td>Choosse new  Pswd:</td>
			<td><form:input path="pswd"/></td>
			</tr>
			
			<tr>
			<td>Confirm  Pswd:</td>
			<td><form:input path="cnfrmPswd"/></td>
			</tr>
			
			<tr>
			<td><input type="submit" value="Submit"/></td>
			</tr>

			</table>

		</form:form>
	</div>
</body>
</head>
</html>