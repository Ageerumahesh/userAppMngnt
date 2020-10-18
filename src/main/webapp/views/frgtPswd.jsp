<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div align="center">
	
	
	<form:form action="rcvryEmail" modelAttribute="UnlockAcc" method="POST">
	<div style="margin-top: 100px; width: 565px">
			<h3>Forgot Password</h3>
		</div>
		<div style="margin-top: 3px">
			Email <span style="color: #ff3300">*</span>
			<footer></footer>
			<section>
				<form:input path="email" />
			</section>
		</div>
        <div style="margin-top: 5px; width: 565px">
				<button type="submit">Submit</button>
		</div>
        <div style="margin-top: 3px">
			Status <span style="color: #ff3300"></span>
			<footer></footer>
			<section></section>
			<section></section>
			<section></section>
		</div>
		
		
		
		
	</form:form>
	</div>
</body>
</html>