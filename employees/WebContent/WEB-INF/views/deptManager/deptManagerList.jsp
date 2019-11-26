<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesListByPage</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>매니져 목록 (현재 근무 중인 매니져)</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">
				홈으로</a>
		</div>
		<!-- ### 테이블 시작 ####################################################################  -->
			<table class="table table-hover">
			<thead>
				<tr>
					<th>사원 번호</th>
					<th>부서 번호</th>
					<th>부서 이름</th>
					<th>사원 이름</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="deptManager" items="${list}">
					<tr>
						<td>${deptManager.empNo}</td>
						<td>${deptManager.deptNo}</td>
						<td>${deptManager.deptName}</td>
						<td>${deptManager.empName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>