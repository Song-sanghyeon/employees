<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<!-- 
		1. 디렉티비 (지금은 불가능)
		2. 선언식 (사용을 안하면 가능)
		3. 스크립트식 (jstl : 사용자가 생성한 태그)
		4. 표현식 (el 사용)
		jsp에서 나올수 있는 자바코드 4가지를 없애야 한다.
	 -->
	<h1>Index</h1>
	<!-- 6개의 테이블의 각 전체 행의 수를 나타내는 테이블 -->
	<h2>테이블 정보</h2>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>테이블 이름</th>
					<th>전체 행의 수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>employees</td>
					<td>${employeesRowCount}</td>
				</tr>
				<tr>
					<td>departments</td>
					<td>${departmentsRowCount}</td>
				</tr>
				<tr>
					<td>dept_manager</td>
					<td>${deptManagerRowCount}</td>
				</tr>
				<tr>
					<td>dept_emp</td>
					<td>${deptEmpRowCount}</td>
				</tr>
				<tr>
					<td>titles</td>
					<td>${titlesRowCount}</td>
				</tr>
				<tr>
					<td>salaries</td>
					<td>${salariesRowCount}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- WEB APP 네비게이션 -->
	<div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/departments/getDepartmentsList">부서 목록</a></li>
			<li><a href="<%=request.getContextPath()%>/employees/getEmployeesList?Limit=10">사원 목록</a></li>
			<!-- limit가 50인 테이블의 리스트 (오름차순, 내림차순) -->
			<li>
				<!-- first_name 기준  -->
				사원 목록 first_name (limit 50)
				<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=asc">[오름차순]</a>
				<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=desc">[내림차순]</a>
			</li>
		</ul>
	</div>
	
	<div>
		EL -> employees table total row count : ${employeesRowCount}
		EL -> departments table total row count : ${departmentsRowCount}
		<br>
		표현식 -> employees table total row count : <%=request.getAttribute("employeesRowCount")%>
		표현식 -> departments table total row count : <%=request.getAttribute("departmentsRowCount")%>
	</div>
</body>
</html>