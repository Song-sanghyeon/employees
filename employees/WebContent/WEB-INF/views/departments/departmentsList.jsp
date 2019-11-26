<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- tag 라이브러리를 이용하여 java코드를 사용하지않고 jsp페이지를 만들기 위한 호출 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>departmentsList</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>부서 목록</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">홈으로</a>
		</div>
		<br>
		<div class="list-group">
			<!-- dept_emp와 departments 테이블을 join하여 각 부서별 사원의 수를 볼 수 있는데, 퇴사자를 제외한 현재 사원의 수 이다. -->
			<a class="list-group-item list-group-item-action" 
				href="${pageContext.request.contextPath}/departments/getDepartmentsCountByDeptNo">현재 부서별 사원 수</a>
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
				<c:forEach var="departments" items="${list}">
					<tr>
						<td>${departments.deptNo}</td>
						<td>${departments.deptName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>