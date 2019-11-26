<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>로그인</h1>
		<div>
			이름과 사원번호를 통해 로그인 할 수 있습니다.
		</div>
		<hr>
		<form method="post" action="${pageContext.request.contextPath}/login">
			<table class="table table-hover">
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
	</div>
</body>
</html>