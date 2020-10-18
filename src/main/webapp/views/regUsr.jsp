<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login page</title>
<meta charset="utf-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#emailId").blur(function() {
			$("#errMsg").text("");
			$.ajax({
				type : "GET",
				url : "isEmailIUnique?email=" + $(emailId).val(),
				success : function(data) {
					if (data == "duplicate") {
						$("#errMsg").text("DuplicateEmail");
						$("#sbmtBtn").prop("disabled", true);
					} else {
						$("#sbmtBtn").prop("disabled", false);
					}
				}
			});

		});

		$("#cntryId").change(function() {
			$('#stateId').find('option:not(:first)').remove();
			$('#cityId').find('option:not(:first)').remove();
			$.ajax({
				type : "GET",
				url : "cntryChngeEvnt?cntryId=" + $(cntryId).val(),
				success : function(data) {
				 $.each(data,function(stateId,stateNm) {
					 $('#stateId').append($('<option>').text(stateNm).attr('value',stateId));
				 })
				}
			});
		});
		
		$("#stateId").change(function() {
			$('#cityId').find('option:not(:first)').remove();
			$.ajax({
				type : "GET",
				url : "stateChngeEvnt?stateId=" + $(stateId).val(),
				success : function(data) {
				 $.each(data,function(cityId,cityNm) {
					 $('#cityId').append($('<option>').text(cityNm).attr('value',cityId));
				 })
				}
			});
		});
	});
</script>

</head>
<body>
	<div align="center">
		<h3>Registration Page</h3>
		<font color='red'>${failMsg}</font><font color='red'>${succMsg}</font>
		<form:form action="saveUsr" modelAttribute="UsrAccMdl" method="POST">
			<table>
				<tr>
					<td>First Name::</td>
					<td><form:input path="frstNm" /></td>
				</tr>

				<tr>
					<td>Last Name::</td>
					<td><form:input path="lstNm" /></td>
				</tr>

				<tr>
					<td>Email::</td>
					<td><form:input path="email" id="emailId" /> <font
						color='red'><span id="errMsg"></span></font></td>
				</tr>


				<tr>
					<td>Pnhno::</td>
					<td><form:input path="num" /></td>
				</tr>

				<tr>
					<td>Dob</td>
					<td><form:input path="dob" /></td>
				</tr>

				<tr>
					<td>Gender</td>
					<td><form:radiobutton path="gndr" value="M" />Male <form:radiobutton
							path="gndr" value="F" />Fe-Male</td>
				</tr>

				<tr>
					<td>Select country</td>
					<td><form:select path="cntryD" id="cntryId">
							<form:option value="">-select-</form:option>
							<form:options items="${countries}"></form:options>
						</form:select></td>
				</tr>

				<tr>
					<td>Select State</td>
					<td><form:select path="stateD" id="stateId">
							<form:option value="">-select-</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Select city</td>
					<td><form:select path="cityD" id="cityId">
							<form:option value="">-select-</form:option>
						</form:select></td>
				</tr>

				<tr>
					<td><input type="submit" value="Register" id="sbmtBtn"></td>
					<td><input type="reset" value="Restet"></td>
					<td></td>
				</tr>


			</table>
		</form:form>

	</div>
</body>

</html>