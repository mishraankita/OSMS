<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>student registration</title>
</head>
<body background="welcome.jpg">
<form action="./StudentServlet" method=POST>
<h1 align="center"><font color="blue">Student Information Form</font></h1>
<table align="center">      
	<tr>
		<th></th>
	</tr>
	<tr>
		<td>First Name :</td>
		<td><input type="text" name="firstname"></input></td>
	</tr>
	<tr>
		<td>Middle Name :</td>
		<td><input type="text" name="middlename"></input></td>
	</tr>
		<tr>
		<td>Last Name :</td>
		<td><input type="text" name="lastname"></input></td>
	</tr>
	<tr>
		<td>Father Name :</td>
		<td><input type="text" name="fname"></input></td>
	</tr>
	<tr>
		<td>Date of Birth :</td>
		<td><input type="text" name="dob"></input></td>
	</tr>
	<tr>
		<td>Address :</td>
		<td><input type="text" name="address"></input></td>
	</tr>
	
	<tr>
		<td>Curriculum :</td>
		<td>
		<select name="Curriculum">
		  <option value="CBSC">CBSC</option>
		  <option value="ICSC">ICSC</option>
		</select>
	</td>
	</tr>
	
	<tr>
		<td>Grade :</td>
		<td><select name="Grade">
		  <option value="1">Class 1</option>
		  <option value="2">Class 2</option>
		  <option value="3">Class 3</option>
		  <option value="4">Class 4</option>
		  <option value="5">Class 5</option>
		  <option value="6">Class 6</option>
		  <option value="7">Class 7</option>
		  <option value="8">Class 8</option>
		  <option value="9">Class 9</option>
		  <option value="10">Class 10</option>
		</select></td>
	</tr>
</table>
	<pre >
		                                                            <input type="submit" value="SUBMIT"/ >
	</pre>
		
</form>

 <h3 align="center"><a href="./adminsuccess.html">   Go to Home</a></h3>
</body>
</html>