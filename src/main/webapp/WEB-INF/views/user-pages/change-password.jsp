<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<link rel="icon"
	href="https://png.pngtree.com/template/20190316/ourmid/pngtree-books-logo-image_79143.jpg">
<title>User Profile</title>
<jsp:include page="../partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/login.css">
<style>
.change-password .login-title {
	margin-bottom: 3rem;
}
</style>
</head>
<body>
	<div id="root">
		<jsp:include page="../partials/header.jsp" />
		<div id="main">
			<div class="change-password">
				<div class="container">
					<div class="login">
						<div class="container">
							<div class="login-wrapper">
								<div class="login-title">Đổi mật khẩu</div>
								<form action="/api/user/change-password"
									id="form-change-password" method="put">
									<div class="row form-container">
										<div class="col-12">
											<input class="input-feild" id="current-password"
												placeholder="Mật khẩu hiện tại" type="password"
												name="current-password" />
										</div>
										<div class="col-12">
											<input class="input-feild" id="new-password"
												placeholder="Mật khẩu mới" type="password"
												name="new-password" />
										</div>
										<div class="col-12">
											<input class="input-feild" id="confirm-password"
												placeholder="Nhập lại mật khẩu mới" type="password"
												name="confirm-password" />
										</div>
									</div>
									<button type="submit" class="button button-full">CHANGE
										PASSWORD</button>
									<p id="error-message" style="color: red"></p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../partials/footer.jsp" />
	</div>
	<jsp:include page="../partials/external_foot.jsp" />
	<script>
	 const validate = new window.JustValidate('#form-change-password');
     validate
        .addField('#current-password', [
           {
              rule: 'required',
              errorMessage: 'Không được để trống mật khẩu !',
           },
        ])
        .addField('#new-password', [
           {
              rule: 'required',
              errorMessage: 'Vui lòng điền mật khẩu mới!',
           },
           {
              rule: 'password',
              errorMessage: 'Mật khẩu phải có ít nhất 8 ký tự, trong đó có ít nhất một ký tự chữ và một ký tự số !',
           },
        ])
        .addField('#confirm-password', [
           {
              rule: 'required',
              errorMessage: 'Vui lòng điền trường này!',
           },
        ])
        .onSuccess(async (event) => {
           if ($('#new-password').val() !== $('#confirm-password').val()) {
              validate.showErrors({ '#confirm-password': 'Mật khẩu không trùng khớp !' });
           } else {
              const currentPassword = $('#current-password').val();
              const newPassword = $('#new-password').val();
              const payload = { currentPassword, newPassword };
              
              const formData = new FormData();
              
              formData.append('passwordInfo',JSON.stringify(payload));
              
              const {data, status} = await axios.post(`/BooksStore/user/change-password.htm`, formData, { 
  				headers: { 'Content-Type': 'multipart/form-data' } 
				});
          	console.log(data);
			if(status == 200 && data.status){
				console.log("Cập nhật thông tin người dùng thành công !");
				location.reload();
			}else{
				
			   $("#error-message").text(data.message);
			}
                                        
           }
        });
     
     $('#confirm-password').on('input', function () {
        if ($('#new-password').val() !== $(this).val()) {
           validate.showErrors({ '#confirm-password': 'Mật khẩu không trùng khớp !' });
        } else {
           validate.showSuccessLabels({ '#confirm-password': '' });
        }
     });
	</script>
</body>
</html>