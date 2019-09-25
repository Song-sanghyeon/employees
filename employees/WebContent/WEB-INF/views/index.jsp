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
			<li>
				<a href="<%=request.getContextPath()%>/departments/getDepartmentsList">부서 목록</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/employees/getEmployeesList?Limit=10">사원 목록</a>
			</li>
			<!-- limit가 50인 테이블의 리스트 (오름차순, 내림차순) -->
			<li>
				<!-- first_name 기준  -->
				사원 목록 first_name (limit 50)
				<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=asc">[오름차순]</a>
				<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=desc">[내림차순]</a>
			</li>
			<li>
				<!-- 중복 제거 sql distinct을 이용하여 titles 테이블의 중복을 제외한 데이터만 볼 수 있는 리스트 -->
				<a href="${pageContext.request.contextPath}/titles/getTitlesListDistinct">업무 목록 (중복제거 distinct)</a>
			</li>
			<li>
				<!-- salaries 테이블의 데이터를 기반으로 총 행의수, 연봉을 더한값, 평균값, 가장 큰 값, 가장 작은 값, 평균 편차를 볼 수 있는 리스트 -->
				<a href="${pageContext.request.contextPath}/salaries/getSalariesStatistics">연봉 통계값 (count, sum, avg, max, min, std)</a>
			</li>
			<li>
				<!-- employees 테이블의 데이터 중 성별로 나누어 각 성별의 사원 수를 볼 수 있는 리스트 -->
				<a href="${pageContext.request.contextPath}/employees/getEmployeesCountByGender">사원 수 (성별 group by gender)</a>
			</li>
			<li>
				<!-- dept_emp와 departments 테이블을 join하여 각 부서별 사원의 수를 볼 수 있는데, 퇴사자를 제외한 현재 사원의 수 이다. -->
				<a href="${pageContext.request.contextPath}/departments/getDepartmentsCountByDeptNo">현재 부서별 사원 수</a>
			</li>
		</ul>
	</div>
	
	<div>
		<form method="post" action="${pageContext.request.contextPath}/employees/getEmployeesListBetween">
			<input type="number" name="begin" value="10001">~<input type="number" name="end" value="49999">
			<button type="submit">사원 목록 (between ... and ...)</button>
			(${minEmpNo}~${maxEmpNo})
		</form>
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