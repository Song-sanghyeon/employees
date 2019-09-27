<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptEmpList</title>
</head>
<body>
	<h1>현재 근무중인 부서별 사원 목록</h1>
	<div>
		<a href="${pageContext.request.contextPath}/">홈으로</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>사원 번호</th>
				<th>부서 번호</th>
				<th>부서 이름</th>
				<th>사원 이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="deptEmp" items="${list}">
				<tr>
					<td>${deptEmp.empNo}</td>
					<td>${deptEmp.deptNo}</td>
					<td>${deptEmp.deptName}</td>
					<td>${deptEmp.empName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/deptEmp/getDeptEmpList?currentPage=${currentPage-1}">이전</a>
	</c:if>
	
	<c:if test="${currentPage < lastPage}">
		<a href="${pageContext.request.contextPath}/deptEmp/getDeptEmpList?currentPage=${currentPage+1}">다음</a>
	</c:if>
</body>
</html>