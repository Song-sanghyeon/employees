<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SALARIES</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>사원의 연봉 현황 사원 번호 검색</h1>
		<div>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/">홈으로</a>
			<a class="btn btn-light" role="button"
				href="${pageContext.request.contextPath}/salaries/getSalariesList">뒤로가기</a>
		</div>
		<br>
		<!-- 사원 번호를 검색하기위한 폼 -->
		<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/salaries/getSalariesSearchList">
			<input type="number" name="empNo" value="10010">
			<button type="submit" class="btn btn-secondary">검색</button>
		</form>
	</div>
	</div>
</body>
</html>