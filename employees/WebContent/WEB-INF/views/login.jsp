<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="${pageContext.request.contextPath}/login">
		<table>
			<tr>
				<td>first_name : </td>
				<td><input type="text" value="Georgi" name="firstName"></td>
			</tr>
			<tr>
				<td>last_name : </td>
				<td><input type="text" value="Facello" name="lastName"></td>
			</tr>
			<tr>
				<td>emp_no : </td>
				<td><input type="text" value="10001" name="empNo"></td>
			</tr>
		</table>
		<button type="submit">login</button>
	</form>
</body>
</html>