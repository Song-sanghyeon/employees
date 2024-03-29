<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SALARIES STATISTICS</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>salary 연봉 통계값 (count, sum, avg, max, min, std)</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">홈으로</a>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/salaries/getSalariesList">뒤로가기</a>
		</div>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>count (전체 행의 수)</th>
					<th>sum (전체 사원의 연봉 합)</th>
					<th>avg (전체 사원의 평균 연봉)</th>
					<th>max (전체 사원 중 가장 높은 연봉)</th>
					<th>min (전체 사원 중 가장 낮은 연봉)</th>
					<th>std (전체 사원의 연봉 중간 값)</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${map.count}</td>
					<td>${map.sum}</td>
					<td>${map.avg}</td>
					<td>${map.max}</td>
					<td>${map.min}</td>
					<td>${map.std}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>