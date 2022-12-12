<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>HOME PAGE</title>
<jsp:include page="../partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/css/profile.css">
</head>
<body>
	<div id="root">
		<jsp:include page="../partials/header.jsp" />
		<div id="main">
			<div id="profile">
				<div class="login signup">
					<div class="container">
						<div class="login-wrapper">
							<form id="form-update-user-profile" action="/api/v1/auth/signup"
								method="post">
								<div class="avatar">
									<label for="avatar-upload" class="avatar__upload">
										<div class="avatar-wrapper">
											<img id="avatar-img" src="${user.avatar}" alt="avatar" />
										</div> <input id="avatar-upload" hidden name="avatar" type="file"
										accept="image/*" />
									</label>
								</div>
								<div class="row form-container">
									<div class="col-12">
										<input type="text" class="input-feild" readonly="true"
											placeholder="Tên đăng nhập" autocomplete="off"
											value="${userInfo.username}" name="username" id="username" />
									</div>

									<div class="col-12">
										<input class="input-feild" placeholder="Email"
											value="${userInfo.email}" id="email" type="email"
											name="email" />
									</div>
									<div class="col-12">
										<input class="input-feild" value="${userInfo.phoneNumber}"
											placeholder="Số điện thoại" id="phoneNumber" type="text"
											name="phoneNumber" />
									</div>
									<div class="col-12">
										<input type="text" class="input-feild"
											value="${userInfo.fullName}" placeholder="Họ và tên"
											autocomplete="off" name="fullName" id="fullName" />
									</div>
									<div class="col-12">
										<input class="input-feild" placeholder="Địa chỉ"
											value="${userInfo.address}" type="text" id="address"
											name="address" />
									</div>
									<div class="col-12">
										<div class="gender-wrapper">
											<div class="form-check">
												<input value="male" class="form-check-input gender-radio"
													type="radio" name="gender" id="gender_1"
													${userInfo.gender ? 'checked' : ''} /> <label
													class="form-check-label" for="gender_1"> Nam </label>
											</div>
											<div class="form-check">
												<input value="female" class="form-check-input gender-radio"
													type="radio" name="gender" id="gender_2"
													${ !userInfo.gender  ? 'checked' : ''} /> <label
													class="form-check-label" for="gender_2"> Nữ </label>
											</div>

										</div>
									</div>
									<button type="submit" id="update-user-submit-btn"
										class="button disable button-full">CẬP NHẬT</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../partials/footer.jsp" />
	</div>
	<jsp:include page="../partials/external_foot.jsp" />
</body>
</html>