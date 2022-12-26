<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>User Profile</title>
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
											value="${userEntity.username}" name="username" id="username" />
									</div>

									<div class="col-12">
										<input class="input-feild" placeholder="Email"
											value="${userEntity.email}" id="email" type="email"
											name="email" />
									</div>
									<div class="col-12">
										<input class="input-feild" value="${userEntity.phoneNumber}"
											placeholder="Số điện thoại" id="phoneNumber" type="text"
											name="phoneNumber" />
									</div>
									<div class="col-12">
										<input type="text" class="input-feild"
											value="${userEntity.fullName}" placeholder="Họ và tên"
											autocomplete="off" name="fullName" id="fullName" />
									</div>
									<div class="col-12">
										<input class="input-feild" placeholder="Địa chỉ"
											value="${userEntity.address}" type="text" id="address"
											name="address" />
									</div>
									<div class="col-12">
										<div class="gender-wrapper">
											<div class="form-check">
												<input value="male" class="form-check-input gender-radio"
													type="radio" name="gender" id="gender_1"
													${userEntity.gender ? 'checked' : ''} /> <label
													class="form-check-label" for="gender_1"> Nam </label>
											</div>
											<div class="form-check">
												<input value="female" class="form-check-input gender-radio"
													type="radio" name="gender" id="gender_2"
													${ !userEntity.gender  ? 'checked' : ''} /> <label
													class="form-check-label" for="gender_2"> Nữ </label>
											</div>

										</div>
									</div>
									<button type="submit" id="update-user-submit-btn"
										class="button disable button-full">CẬP NHẬT</button>
									<p id="message-error" class="text-danger just-validate-error-label"></p>
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
	<script type="module">
	   import { getFormData } from '/BooksStore/resources/js/utils.js';
        
        const validate = new window.JustValidate('#form-update-user-profile');
        const submitBtn = $('#update-user-submit-btn');
        const avatarNode = $('#avatar-upload');
        validate
           .addField('#fullName', [
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
                 rule: 'email',
                 errorMessage: 'Email Không đúng định dạng !',
              }
           ])
			.addField('#phoneNumber', [
              {
                 rule: 'minLength',
				 value: 9, 
                 errorMessage: 'Không đúng định dạng số điện thoại !',
              },
			  {
                 rule: 'maxLength',
				 value: 12, 
                 errorMessage: 'Không đúng định dạng số điện thoại !',
              }
           ])
           .onSuccess(async (event) => {
              if (!submitBtn.hasClass('disable')) {
                 const totalData = getFormData(event.target);
                 const genderOptionSelected = $('.gender-radio:checked');
                 const formData = new FormData();
                 console.log(totalData);
		
                 formData.append(
                    'userInfo',
                    JSON.stringify({
                       fullName: totalData.fullName,
                       address: totalData.address,
                       gender: genderOptionSelected.val(),
					   email: totalData.email,
					   phoneNumber: totalData.phoneNumber,
                    }),
                 );
                 if (avatarNode[0]?.files?.length > 0) {
                    const avatarFile = avatarNode[0].files[0];
                    formData.append('avatar', avatarFile);
                 }

				const {data, status} = await axios.post(`/BooksStore/user/update.htm`, formData, { 
        				headers: { 'Content-Type': 'multipart/form-data' } 
    				});

				if(status == 200 && data === "OK"){
				location.reload();
				}else{
				 $("#error-message").text("Cập nhật thông tin người dùng thất bại !");
				}

              } else {
                 toastr.info('Thông tin người dùng không có sự sửa đổi nào !');
              }
           });
		
        const removeClass = () => {
           submitBtn.hasClass('disable') && submitBtn.removeClass('disable');
        };

        !(function checkModifyUser() {
           const genderCheckInput = $('.form-check > input[type = radio]');
  		$('#email').on('input', removeClass);
           $('#phoneNumber').on('input', removeClass);
           $('#fullName').on('input', removeClass);
           $('#address').on('input', removeClass);
           genderCheckInput.change(removeClass);
           avatarNode.change(removeClass);
        })();
	</script>
	<script>
		const uploadAvatart = $('#avatar-upload');
		const imgPreview = $('#avatar-img');
		uploadAvatart.change(function() {
			if (this.files && this.files[0]) {
				const tempURL = URL.createObjectURL(this.files[0]);
				imgPreview.attr('src', tempURL);
			}
		});
	</script>
</body>
</html>