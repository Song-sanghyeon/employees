<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesCountByGender</title>
</head>
<body>
	<h1>사원 목록 countByGender</h1>
	<div>
		<a href="${pageContext.request.contextPath}/">홈으로</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>사원 성별</th>
				<th>사원 수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map" items="${list}">
				<tr>
					<td>${map.gender}</td>
					<td>${map.cnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>