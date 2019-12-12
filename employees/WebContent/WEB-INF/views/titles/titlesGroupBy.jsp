<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>titles distinct list</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>현재 근무중인 사원들의 직책 목록</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">홈으로</a>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/titles/getTitlesList">뒤로가기</a>
		</div>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>직책</th>
					<th>사원 수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="titles" items="${list}">
					<tr>
						<td>${titles.title}</td>
						<td>${titles.count}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>