<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
  <meta charset="utf-8">
  <!-- boot strap -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 
		1. 디렉티비 (지금은 불가능)
		2. 선언식 (사용을 안하면 가능)
		3. 스크립트식 (jstl : 사용자가 생성한 태그)
		4. 표현식 (el 사용)
		jsp에서 나올수 있는 자바코드 4가지를 없애야 한다.
	 -->
	<div class="container">
		<br>
		<h1>INFORMATION</h1>
		<hr>
	</div>
	<!-- sessionEmpNo의 값이 null 값이 아니라면, login 되어있는 상태이기 때문에 로그아웃 버튼 생성 -->
	<c:if test="${sessionEmpNo != null}">
	<div class="container">
		<!-- 로그아웃 버튼 클릭시, session 초기화 시켜주고, sessionEmpNo의 값도 0으로 초기화 시켜주어야 한다. -->
		<a class="btn btn-dark" role="button"
			href="${pageContext.request.contextPath}/logout?sessionEmpNo=${sessionEmpNo}">로그아웃</a>
	</div>
	</c:if>
	<!-- WEB APP 네비게이션 -->
	<!-- 6개의 테이블의 각 전체 행의 수를 나타내는 테이블 -->
	<div class="container">
		<hr>테이블 이름을 클릭하면 해당 테이블의 리스트를 확인할 수 있습니다.<hr>
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th>테이블 이름</th>
					<th>전체 행의 수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href="<%=request.getContextPath()%>/employees/getEmployeesList">employees</a></td>
					<td>${employeesRowCount}</td>
				</tr>
				<tr>
					<td><a href="<%=request.getContextPath()%>/departments/getDepartmentsList">departments</a></td>
					<td>${departmentsRowCount}</td>
				</tr>
				<tr>
					<td><a href="<%=request.getContextPath()%>/deptManager/getDeptManagerList">dept_manager</a></td>
					<td>${deptManagerRowCount}</td>
				</tr>
				<tr>
					<td><a href="<%=request.getContextPath()%>/deptEmp/getDeptEmpList">dept_emp</a></td>
					<td>${deptEmpRowCount}</td>
				</tr>
				<tr>
					<td><a href="<%=request.getContextPath()%>/titles/getTitlesList">titles</a></td>
					<td>${titlesRowCount}</td>
				</tr>
				<tr>
					<td><a href="<%=request.getContextPath()%>/salaries/getSalariesList">salaries</a></td>
					<td>${salariesRowCount}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>