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
		<div class="login signup">
			<div class="container">
				<div class="login-wrapper">
					<div class="login-title">Đăng ký tài khoản</div>
					<form id="form-register" action="/api/v1/auth/signup" method="post">
						<div class="divider">
							<div class="line"></div>
							<div class="or">Thông tin cá nhân</div>
						</div>
						<div class="row form-container">
							<div class="col-12">
								<input type="text" class="input-feild" placeholder="Họ và tên"
									autocomplete="off" name="fullName" id="fullName" />
							</div>
							<div class="col-12">
								<input class="input-feild" placeholder="Email" id="email"
									type="email" name="email" />
							</div>
							<div class="col-12">
								<input class="input-feild" placeholder="Số điện thoại"
									id="phoneNumber" type="text" name="phoneNumber" />
							</div>
							<div class="divider">
								<div class="line"></div>
								<div class="or">Thông tin tài khoản</div>
							</div>
							<div class="col-12">
								<input class="input-feild" placeholder="Tên đăng nhập"
									type="text" id="userName" name="username" />
							</div>
							<div class="col">
								<input class="input-feild" placeholder="Mật khẩu"
									type="password" id="password" name="password" />
							</div>
							<button type="submit" class="button button-full">REGISTER</button>
							<p id="error-message" class="text-danger just-validate-error-label"></p>
						</div>
					</form>
					<div class="more-options">
						<a href="<c:url value='/login.htm'/>" class="text--underline">Đăng nhập với tài
							khoản có sẵn</a> <a href="<c:url value='/index.htm'/>" class="text--underline">Trở về cửa
							hàng</a>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./partials/footer.jsp" />
	</div>
	<jsp:include page="./partials/external_foot.jsp" />
	<script type="module"> 
	     import { getFormData } from '/BooksStore/resources/js/utils.js';
         const validate = new window.JustValidate('#form-register');

         validate
            .addField('#fullName', [
               {
                  rule: 'required',
                  errorMessage: 'Không được để trống họ tên',
               },
               {
                  rule: 'minLength',
                  value: 3,
                  errorMessage: 'Họ và tên phải có ít nhất 3 ký tự',
               },
               {
                  rule: 'maxLength',
                  value: 30,
                  errorMessage: 'Họ và tên chỉ được có tối đa 30 ký tự',
               },
            ])
            .addField('#email', [
               {
                  rule: 'required',
                  errorMessage: 'Không được để trống email !',
               },
               {
                  rule: 'email',
                  errorMessage: 'Không đúng định dạng email !',
               },
            ])
            .addField('#phoneNumber', [
               {
                  rule: 'minLength',
                  value: 10,
                  errorMessage: 'Số điện thoại phải có ít nhất 10 chữ số!',
               },
               {
                  rule: 'maxLength',
                  value: 12,
                  errorMessage: 'Số điện thoại chỉ được có tối đa 12 chữ số!',
               },
            ])
            .addField('#userName', [
               {
                  rule: 'required',
                  errorMessage: 'Vui lòng điền tên đăng nhập !',
               },
               {
                  rule: 'minLength',
                  value: 6,
                  errorMessage: 'Tên đăng nhập phải có ít nhất 6 ký tự !',
               },
               {
                  rule: 'maxLength',
                  value: 20,
                  errorMessage: 'Tên đăng nhập chỉ được có nhiều nhất 20 ký tự !',
               },
            ])
            .addField('#password', [
               {
                  rule: 'required',
                  errorMessage: 'Vui lòng điền mật khẩu !',
               },
               {
                  rule: 'password',
                  errorMessage: 'Mật khẩu phải có ít nhất 8 ký tự, trong đó có ít nhất một ký tự chữ và một ký tự số !',
               },
            ])
            .onSuccess(async (event) => {
               const data = getFormData(event.target);
				const formData = new FormData();
				formData.append(
                    'userInfo',
                    JSON.stringify(data),
                 );
               try {
                  const { data, status } = await axios.post('/BooksStore/user/register.htm', formData,
					{ headers: { 'Content-Type': 'application/json; charset=utf-8'} });
                  if (status === 200) {
                     const {status, message}  = data;
					if(status){
					//back to the home page
					$(".main-logo")[0].click();
                  }else{
					$("#error-message").text(message);
				  }
				}
               } catch (error) {
				  console.log(error);
                  toastr.error('Xảy ra lỗi khi gửi request về server !');
               }
            });
      </script>
</body>
</html>