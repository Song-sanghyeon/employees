<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jsp페이지에서 java코드를 사용하지 않기 위한 tag 라이브러리이용 이름이 길기떄문에 호출하기 쉽게 c로 이름 변경 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesList</title>
</head>
<body>
	<h1>사원 목록</h1>
	<!-- form을 사용하여 사원리스트에서 페이지에 조회할 수 있는 limit을 선택할 수 있다. -->
	<form method="get" action="<%=request.getContextPath()%>/employees/getEmployeesList">
		<select name="Limit">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option value="40">40</option>
			<option value="50">50</option>
		</select>
		<button type="submit">사원 목록</button>
	</form>
	<table>
		<thead>
			<tr>
				<th>사원 번호</th>
				<th>사원 생일</th>
				<th>사원 이름</th>
				<th>사원 성</th>
				<th>사원 성별</th>
				<th>사원 입사일</th>
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
</body>
</html>