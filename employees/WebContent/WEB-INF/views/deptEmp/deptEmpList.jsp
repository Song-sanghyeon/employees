<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptEmp List</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>사원 부서 변동 현황 목록</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">홈으로</a>
		</div>
		<br>
		<div class="list-group">
			<!-- first_name 기준  -->
			<a class="list-group-item list-group-item-action" 
				href="${pageContext.request.contextPath}/deptEmp/getDeptEmpNowList">
				현재 근무중인 사원</a>
			<a class="list-group-item list-group-item-action"
				href="${pageContext.request.contextPath}/deptEmp/getDeptEmpSearch">
				사원의 부서 변동 현황</a>
		</div>
		<!-- ### 테이블 시작 ####################################################################  -->
		<table class="table table-hover">
			<thead>
				<tr>
					<th>사원 번호</th>
					<th>부서 번호</th>
					<th>전입 일</th>
					<th>이적 일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="deptEmp" items="${list}">
					<tr>
						<td>${deptEmp.empNo}</td>
						<td>${deptEmp.deptNo}</td>
						<td>${deptEmp.fromDate}</td>
						<td>${deptEmp.toDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- ### 테이블 끝 ######################################################################### -->
		
		<!-- 첫번째 페이지를 제외하고 나타나는 이전과, 마지막 페이지를 제외하고 나타나는 다음 생성 -->
		<c:if test="${currentPage > 1}"> <!-- if문 -->
		<a class="btn btn-dark" role="button"
			href="${pageContext.request.contextPath}/deptEmp/getDeptEmpList?currentPage=${currentPage-1}">
			이전</a>
		</c:if>
		
		<c:if test="${currentPage < lastPage}">
		<a class="btn btn-dark" role="button"
			href="${pageContext.request.contextPath}/deptEmp/getDeptEmpList?currentPage=${currentPage+1}">
			다음</a>
		</c:if>
	</div>
</body>
</html>