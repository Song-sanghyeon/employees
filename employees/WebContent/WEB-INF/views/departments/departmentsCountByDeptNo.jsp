<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>departmentsCountByDeptNo</title>
</head>
<body>
	<h1>현재 부서별 인원 수</h1>
	<div>
		<a href="${pageContext.request.contextPath}/">홈으로</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>부서 번호</th>
				<th>부서 명</th>
				<th>현재 인원 수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map" items="${list}">
				<tr>
					<td>${map.deptNo}</td>
					<td>${map.deptName}</td>
					<td>${map.cnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>