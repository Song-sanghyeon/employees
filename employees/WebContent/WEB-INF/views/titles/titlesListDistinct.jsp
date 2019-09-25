<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>titlesListDistinct</title>
</head>
<body>
	<!-- titles 테이블의 중복된 데이터를 제거하고 한개의 데이터씩들만 리스트로 출력하여 조회할 수 있는 페이지 -->
	<h1>업무 목록 (중복제거 distinct)</h1>
	<div>
		<a href="${pageContext.request.contextPath}/">홈으로</a>
	</div>
	<ol>
		<c:forEach var="row" items="${list}">
			<li>${row}</li>
		</c:forEach>
	</ol>
</body>
</html>