<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>HOME PAGE</title>
<jsp:include page="./partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/login.css">
</head>
<body>
	<div id="root">
		<jsp:include page="./partials/header.jsp" />
		<div class="login">
			<div class="container">
				<div class="login-wrapper">
					<div class="login-title">Đăng nhập</div>
					<div class="login-method-wrapper d-flex justify-content-center">
						<div class="login-method-item">
							<a href="#"> <i class="fa-brands fa-google"></i>
							</a>
						</div>
						<div class="login-method-item">
							<a href="#"> <i class="fa-brands fa-facebook"></i>
							</a>
						</div>
					</div>
					<div class="divider">
						<div class="line"></div>
						<div class="or">OR</div>
					</div>
					<div style="text-transform: initial">Đăng nhập với tài khoản:</div>
					<form action="user/login.htm" id="form-login" method="post">
						<div class="row form-container">
							<div class="col-12">
								<input class="input-feild" id="username" name="username"
									placeholder="Tên đăng nhập" type="text" />
							</div>
							<div class="col-12">
								<input class="input-feild" id="password" placeholder="Mật khẩu"
									type="password" name="password" />
							</div>
						</div>
						<div class="text--underline">Quên mật khẩu?</div>
						<button type="submit" class="button button-full">LOGIN</button>
						<p class="text-danger">${message}</p>
					</form>
					<div class="more-options">
						<a href="<c:url value='/register.htm'/>" class="text--underline">Tạo
							tại khoản</a> <a href="<c:url value='/index.htm'/>"
							class="text--underline">Trở về cửa hàng</a>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./partials/footer.jsp" />
	</div>
	<jsp:include page="./partials/external_foot.jsp" />
</body>
</html>