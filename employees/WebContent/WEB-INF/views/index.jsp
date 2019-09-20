<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
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
	
	<!-- WEB APP 네비게이션 -->
	<div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/departments/getDepartmentsList">부서 목록</a></li>
			<li><a href="<%=request.getContextPath()%>/employees/getEmployeesList?Limit=10">사원 목록</a></li>
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