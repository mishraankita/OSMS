<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DPD registration</title>
</head>
<body background="welcome.jpg">
<form action="./enroll" method=POST>
<h1 align="center"><font color="blue">DPD Information Form</font></h1>
<table align="center">      
	<tr>
		<th></th>
	</tr>
	<tr>
		<td>User ID :</td>
		<td><input type="text" name=userID></input></td>
	</tr>
	<tr>
		<td>First Name :</td>
		<td><input type="text" name="firstname"></input></td>
	</tr>
	<tr>
		<td>Last Name :</td>
		<td><input type="text" name="lastname"></input></td>
	</tr>
	<tr>
		<td>Address :</td>
		<td><input type="text" name="address"></input></td>
	</tr>
	<tr>
		<td>Email :</td>
		<td><input type="text" name="email"></input></td>
	</tr>
	<tr>
		<td>Password :</td>
		<td><input type="text" name="password"></input></td>
	</tr>
	<tr>
		<td>securityQuestion :</td>
		<td><input type="text" name="securityQuestion"></input></td>
	</tr>
	<tr>
		<td>answer :</td>
		<td><input type="text" name="answer"></input></td>
	</tr>
	<tr>
		<td>Account Type :</td>
		<td><input type="text" name="accountType"></input></td>
	</tr>
	<tr>
		<td>Status</td>
		<td><input type="text" name="status"></input></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>Curriculum :</td> -->
<!-- 		<td> -->
<!-- 		<select name="Curriculum"> -->
<!-- 		  <option value="CBSC">CBSC</option> -->
<!-- 		  <option value="ICSC">ICSC</option> -->
<!-- 		</select> -->
<!-- 	</td> -->
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>Grade :</td> -->
<!-- 		<td><select name="Grade"> -->
<!-- 		  <option value="1">Class 1</option> -->
<!-- 		  <option value="2">Class 2</option> -->
<!-- 		  <option value="3">Class 3</option> -->
<!-- 		  <option value="4">Class 4</option> -->
<!-- 		  <option value="5">Class 5</option> -->
<!-- 		  <option value="6">Class 6</option> -->
<!-- 		  <option value="7">Class 7</option> -->
<!-- 		  <option value="8">Class 8</option> -->
<!-- 		  <option value="9">Class 9</option> -->
<!-- 		  <option value="10">Class 10</option> -->
<!-- 		</select></td> -->
<!-- 	</tr> -->
</table>
	<pre >
	<input type="submit" value="SUBMIT"/ >
	</pre>
		
</form>

 <h3 align="center"><a href="./adminsuccess.html">   Go to Home</a></h3>
</body>
</html>