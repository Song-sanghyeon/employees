<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>departmentsCountByDeptNo</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>현재 부서별 인원 수</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">홈으로</a>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/departments/getDepartmentsList">뒤로가기</a>
		</div>
	</div>
	<hr>
	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>부서 번호</th>
					<th>부서 이름</th>
				</tr>
			</thead>
			<tbody>
				<!-- java코드인 for문을 사용하지않고 forEach문을 이용하여 사용 -->
				<!-- var은 사용할 변수이고, items은  객체이다 -->
				<c:forEach var="map" items="${list}">
					<tr>
						<td>${map.deptNo}</td>
						<td>${map.deptName}</td>
						<td>${map.cnt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>