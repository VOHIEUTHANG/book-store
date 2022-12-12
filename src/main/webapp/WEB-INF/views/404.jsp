<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<jsp:include page="./partials/external_head.jsp" />
<title>403 Forbidden</title>
<link href="https://fonts.googleapis.com/css?family=Oswald:700"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Lato:400"
	rel="stylesheet" />
<link rel="stylesheet" href="resources/css/error_page.css">
</head>
<body>
	<div id="notfound">
		<div class="notfound-bg">
			<div></div>
			<div></div>
			<div></div>
			<div></div>
		</div>
		<div class="notfound">
			<div class="notfound-404">
				<h1>404</h1>
			</div>
			<h2>Page Not Found</h2>
			<p>The page you are looking for might have been removed had its
				name changed or is temporarily unavailable.</p>
			<a class="button" href="/">Homepage</a>
		</div>
	</div>
</body>
</html>