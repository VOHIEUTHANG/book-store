<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>Delivery address</title>
<jsp:include page="../partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/delivery-address.css">
</head>
<body>
	<div id="root">
		<jsp:include page="../partials/header.jsp" />
		<div id="main">
			<div class="delivery-address">
				<div class="container">
					<div class="row">
						<div class="delivery-address-container">
							<div class="wrapper">
								<div
									class="head d-flex justify-content-between align-items-center">
									<div class="title">Địa chỉ giao hàng</div>
									<button class="button" id="add-address">
										<i class="ti-plus"></i> <span style="margin-left: 3px">Thêm
											địa chỉ</span>
									</button>
								</div>
								<div class="body">
									<ul class="address-list">

										<c:forEach var="address" items="${deliveryAddress}">
											<li class="address-item d-flex justify-content-between">
												<div class="content" style="text-align: left">
													<div class="sub-addres" id="detail-${address.id}">
														${address.addressDetail}</div>
													<div class="main-address">
														<span id="ward-${address.id}" class="ward">${address.ward}</span>
														, <span id="district-${address.id}" class="district">${address.district}</span>
														, <span id="province-${address.id}" class="province">${address.province}</span>
													</div>
												</div>
												<div class="edit">
													<div data-address-id="${address.id}"
														class="update button light button--modify update-address">
														<i class="fa-solid fa-pen-to-square"></i> Cập nhật
													</div>
													<div data-address-id="${address.id}"
														class="remove button button--danger delete-address">
														<i class="fa-solid fa-trash-can"></i> Xóa
													</div>
												</div>
											</li>
										</c:forEach>

										<c:if test="${deliveryAddress.size() <= 0}">
											<div style="flex-direction: column"
												class="d-flex justify-content-center align-items-center">
												<p
													style="text-align: center; margin-top: 2rem; font-size: 2rem"
													class="nothing-title">Bạn không có địa chỉ nhận hàng
													nào !</p>
											</div>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="add-address-modal" class="">
					<div class="my-overlay">
						<div class="my-modal">
							<form id="form-address">
								<div class="head">
									<div class="title">Địa chỉ mới</div>
								</div>
								<div class="body">
									<div class="province-picker">
										<select id="add-province" name="add-province"></select>
									</div>
									<div class="district-picker">
										<select id="add-district" name="add-district"></select>
									</div>
									<div class="ward-picker">
										<select id="add-ward" name="add-ward"></select>
									</div>
									<div>
										<input id="add-detail-address" class="input-address"
											placeholder="Nhập địa chỉ cụ thể" type="text"
											name="detail-address" />
									</div>
								</div>
								<div class="foot">
									<button class="button" type="submit">Thêm địa chỉ</button>
								</div>
								<div class="btn-close-modal">
									<div class="ti-close"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div id="update-address-modal" class="">
					<div class="my-overlay">
						<div class="my-modal">
							<form id="form-update-address">
								<div class="head">
									<div class="title">Cập nhật địa chỉ</div>
								</div>
								<div class="body">
									<div class="province-picker">
										<select id="ud-province" name="ud-province"></select>
									</div>
									<div class="district-picker">
										<select id="ud-district" name="ud-district"></select>
									</div>
									<div class="ward-picker">
										<select id="ud-ward" name="ud-ward"></select>
									</div>
									<div>
										<input id="ud-detail-address" class="input-address"
											placeholder="Nhập địa chỉ cụ thể" type="text"
											name="detail-address" />
									</div>
								</div>
								<div class="foot">
									<button class="button" id="update-btn" type="submit">Lưu
										cập nhật</button>
								</div>
								<div class="btn-close-modal">
									<div class="ti-close"></div>
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
	<script>
		const addModal = $('#add-address-modal');
		const updateModal = $('#update-address-modal');
		const addressList = $('.address-list');
		
		 const showButton = $('#add-address');
         const modalContainer = $('#add-address-modal .my-modal');
         const closeBtn = $('#add-address-modal .btn-close-modal');
         const overlay = $('#add-address-modal .my-overlay');
         showButton.click(function (e) {
            addModal.addClass('show');
         });
         closeBtn.click(() => {
            addModal.removeClass('show');
         });
         overlay.mousedown(() => {
            addModal.removeClass('show');
         });
         modalContainer.click((e) => {
            e.stopPropagation();
         });
         modalContainer.mousedown((e) => {
            e.stopPropagation();
         });
         
         const validate = new window.JustValidate('#form-address');
         validate
            .addField('#add-detail-address', [
               {
                  rule: 'required',
                  errorMessage: 'Không được để trống dòng này !',
               },
            ])
            .addField('#add-province', [
               {
                  rule: 'required',
                  errorMessage: 'KHông được để trống dòng này !',
               },
            ])
            .addField('#add-district', [
               {
                  rule: 'required',
                  errorMessage: 'KHông được để trống dòng này !',
               },
            ])
            .addField('#add-ward', [
               {
                  rule: 'required',
                  errorMessage: 'KHông được để trống dòng này !',
               },
            ])
            .onSuccess(async (e) => {
               const province = $('#add-province').find(':selected').text();
               const ward = $('#add-ward').find(':selected').text();
               const district = $('#add-district').find(':selected').text();
               const detailAddress = $('#add-detail-address').val().trim();

               const addressData = {
                  province,
                  district,
                  ward,
                  detailAddress,
               };
               
               const formData = new FormData();
		
               formData.append('province', province);
               formData.append('district', district);
               formData.append('ward', ward);
               formData.append('detailAddress', detailAddress);

               const {data, status} = await axios.post(`/BooksStore/user/delivery-address/insert.htm`, formData, { 
    				headers: { 'Content-Type': 'multipart/form-data' } 
   				}); 
               
               if(status===200){
            	   if(data.status){
            		location.reload();   
            	   }            	   
               }else{
            	   toastr.error('Them dia chi xay ra loi!');
               }
               
             
            });
	</script>
</body>
</html>