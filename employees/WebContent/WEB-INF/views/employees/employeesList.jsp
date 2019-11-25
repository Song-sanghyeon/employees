<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jsp페이지에서 java코드를 사용하지 않기 위한 tag 라이브러리이용 이름이 길기떄문에 호출하기 쉽게 c로 이름 변경 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employees List</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>사원 목록</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">홈으로</a>
		</div>
		<div class="list-group">
			<!-- limit가 50인 테이블의 리스트 (오름차순, 내림차순) -->
			<!-- first_name 기준  -->
			<a class="list-group-item list-group-item-action" 
				href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=asc">
				사원 목록 first_name (limit 50) [오름차순]</a>
			<a class="list-group-item list-group-item-action"
				href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=desc">
				사원 목록 first_name (limit 50) [내림차순]</a>
			<!-- employees 테이블의 데이터 중 성별로 나누어 각 성별의 사원 수를 볼 수 있는 리스트 -->
			<a class="list-group-item list-group-item-action"
				href="${pageContext.request.contextPath}/employees/getEmployeesCountByGender">
				사원 수 (성별 group by gender)</a>
			<!-- employees의 테이블에 데이터를 출력하는 페이지인데, 10개의 데이터씩 페이징화 시켜서 조회할 수 있는 페이지 이다. -->
			<a class="list-group-item list-group-item-action"
				href="${pageContext.request.contextPath}/employees/getEmployeesListByPage">
				사원 목록 페이징 (10명 씩)</a>
		</div>
	</div>
	<hr>
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/employees/getEmployeesListBetween">
			<input type="number" name="begin" value="10001">~<input type="number" name="end" value="49999">
			<button type="submit" class="btn btn-secondary">사원 목록 (between ... and ...)</button>
			원하는 사원 번호의 범위를 지정하면 범위내 사원 리스트를 출력합니다.
		</form>
	</div>
	<hr>
	<div class="container">
		<!-- form을 사용하여 사원리스트에서 페이지에 조회할 수 있는 limit을 선택할 수 있다. -->
		<form method="get" action="<%=request.getContextPath()%>/employees/getEmployeesList">
			<select name="Limit">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
				<option value="50">50</option>
			</select>
			<button type="submit" class="btn btn-secondary">사원 목록</button>(선택한 수 만큼의 사원 리스트를 출력합니다)
		</form>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>사원 번호</th>
					<th>생일</th>
					<th>이름</th>
					<th>성</th>
					<th>성별</th>
					<th>입사일</th>
				</tr>
			</thead>
			<tbody>
				<!-- java코드인 for문을 사용하지않고 forEach문을 이용하여 사용 -->
				<!-- var은 사용할 변수이고, items은  객체이다 -->
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
	</div>
</body>
</html>