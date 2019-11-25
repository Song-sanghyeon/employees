<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesListOrderBy</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<!-- ### 상단 메뉴 ################################################################ -->
		<h1>사원 목록 (order by)</h1>
		<!-- 홈으로 이동 -->
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/employees/getEmployeesList">
				홈으로</a>
		</div>
		<!-- ### 상단 메뉴 끝 ########################################################################## -->
		
		<!-- ### 테이블 시작 ######################################################################################### -->
		<table class="table table-hover">
			<thead>
				<tr>
					<th>사원 이름</th>
					<th>사원 생일</th>
					<th>사원 이름</th>
					<th>사원 성</th>
					<th>사원 성별</th>
					<th>사원 입사일</th>
				</tr>
			</thead>
			<tbody>
				<!-- 자바코드인 while문 대신 html에서 사용할 수 있는 코드 var : 객체 items : 변수 -->
				<c:forEach var="employees" items="${list}">
				<tr>
					<td>${employees.empNo}</td>
					<td>${employees.birthDate}</td>
					<td>${employees.firstName}</td>
					<td>${employees.lastName}</td>
					<td>${employees.gender}</td>
					<td>${employees.hireDate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- ### 테이블 끝 ############################################################################### -->
	</div>
</body>
</html>