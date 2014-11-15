<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#fffff">
<h1 align="center"><font color="red"><%=session.getAttribute("StudentName")%>Details</font></h1>
<hr/>
<table border="2" align="center">
	<tr>
		<td>StudentName</td>
		<td><%=session.getAttribute("StudentName")%></td>
	</tr>
	<tr>
		<td>FatherName</td>
		<td><%=session.getAttribute("FatherName")%></td>
	</tr>
	<tr>
		<td>StuClass</td>
		<td><%=session.getAttribute("StuClass")%></td>
	</tr>
		<tr>
		<td>Section</td>
		<td><%=session.getAttribute("Section")%></td>
	</tr>
</table>
</body>
</html>