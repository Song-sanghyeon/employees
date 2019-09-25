<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesListByPage</title>
</head>
<body>
	<h1>사원 목록 (10명씩 페이징)</h1>
	<!-- ### 테이블 시작 ####################################################################  -->
		<table border="1">
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
	<!-- ### 테이블 끝 ######################################################################### -->
	
	<!-- 첫번째 페이지를 제외하고 나타나는 이전과, 마지막 페이지를 제외하고 나타나는 다음 생성 -->
	<c:if test="${currentPage > 1}"> <!-- if문 -->
	<a href="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${currentPage-1}">이전</a>
	</c:if>
	
	<c:if test="${currentPage < lastPage}">
	<a href="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${currentPage+1}">다음</a>
	</c:if>
</body>
</html>