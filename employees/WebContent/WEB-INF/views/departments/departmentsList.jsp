<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- tag 라이브러리를 이용하여 java코드를 사용하지않고 jsp페이지를 만들기 위한 호출 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>departmentsList</title>
</head>
<body>
	<h1>부서 목록</h1>
	<table>
		<thead>
			<tr>
				<th>부서 번호</th>
				<th>부서 이름</th>
			</tr>
		</thead>
		<tbody>
			<!-- foreach문을 이용하여 for문을 대신함. var : 사용할 변수 items : 객체 -->
			<c:forEach var="departments" items="${list}">
				<tr>
					<td>${departments.deptNo}</td>
					<td>${departments.deptName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>