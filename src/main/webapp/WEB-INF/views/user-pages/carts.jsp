<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>Delivery address</title>
<jsp:include page="../partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/cart.css">
</head>
<body>
	<div id="root">
		<jsp:include page="../partials/header.jsp" />
		<div id="main">
			<div class="cart-main-area cart">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<form action="#" id="main-form">
								<c:if test="${carts.size() == 0}">
									<div style="flex-direction: column; min-height: 50vh;"
										class="d-flex justify-content-center align-items-center">
										<img width="200px" src="/resources/images/emptyCart.png" />
										<p
											style="text-align: center; margin-top: 4rem; font-size: 1.8rem"
											class="nothing-title">Bạn không có sản phẩm nào trong giỏ
											hàng !</p>
										<a href="/" class="button light" style="margin-top: 2rem;">Mua
											hàng</a>
									</div>
								</c:if>

								<c:if test="${carts.size() > 0}">
									<div class="table-content table-responsive">
										<table>
											<thead>
												<tr>
													<th>Xóa</th>
													<th>Hình Ảnh</th>
													<th>Tên sản phẩm</th>
													<th>Giá</th>
													<th>Số lượng</th>
													<th>Giảm giá</th>
													<th>Tổng tiền</th>
												</tr>
											</thead>
											<tbody>

												<c:set var="totalDiscount" value="0" />
												<c:set var="originPrice" value="0" />

												<c:forEach var="cart" items="${carts}">
													<c:set var="originPrice"
														value="${cart.product_cart.price * cart.quantity + originPrice}" />
													<c:set var="totalDiscount"
														value="${(cart.product_cart.price*cart.product_cart.discountPercent/100) * cart.quantity + totalDiscount}" />
													<tr data-product-id="${cart.product_cart.id}">
														<td class="product-remove">
															<a href="<c:url value='/cart/delete/${cart.id}.htm'/>" class="product-remove-btn">
																<i class="ti-close"></i>
															</a>
														</td>
														<td class="product-thumbnail"><a
															href="<c:url value='/product/detail/${cart.product_cart.id}.htm'/>"><img
																src="${cart.product_cart.images.get(0).imageURL}" alt="" />

																<c:if test="${cart.product_cart.discountPercent > 0}">
																	<div class="discount-label">
																		- <span class="cart-discount">
																			${cart.product_cart.discountPercent} </span> %
																	</div>
																</c:if> </a></td>
														<td class="product-name"><a
															href="<c:url value='/product/detail/${cart.product_cart.id}.htm'/>">
																${cart.product_cart.name} </a></td>
														<td class="product-price-cart"><span
															class="amount cart-price"><fmt:formatNumber
																	currencySymbol="" maxFractionDigits="0"
																	value="${cart.product_cart.price * (100 - cart.product_cart.discountPercent) * 10 }"
																	type="currency" /> VND </span></td>
														<td class="product-quantity">
															<div class="cart-plus-minus" data-cartid="${cart.id}"
																data-product-count="${cart.quantity}"
																data-product-id="${cart.id}"
																data-inventory="${cart.product_cart.inventory}">
																<div class="dec qtybutton">-</div>
																<input type="text" value="${cart.quantity}"
																	name="qtybutton" class="cart-plus-minus-box"
																	class="product-count" />
																<div class="inc qtybutton">+</div>
															</div>
														</td>
														<td>
															<div class="discount d-flex justify-content-center">
																- <span class="cart-discount-price">
																	${cart.product_cart.discountPercent}%</span>
															</div>
														</td>
														<td class="product-subtotal"><fmt:formatNumber
																currencySymbol="" maxFractionDigits="0"
																value="${cart.quantity * cart.product_cart.price * (100 - cart.product_cart.discountPercent) * 10 }"
																type="currency" /> VND</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:if>
								<c:if test="${carts.size() > 0}">
									<hr>
									<div class="row justify-content-center">
										<div class="col-md-8">
											<div class="cart-page-total">
												<h2>Đơn hàng</h2>
												<ul>
													<li>Tổng tiền hàng<span
														class="origin-price-total origin-price"><fmt:formatNumber
																currencySymbol="" maxFractionDigits="0"
																value="${originPrice * 1000}" type="currency" /> VND </span></li>
													<li>Giảm giá sản phẩm<span class=" discount">-
															<span class="discount-total"> <fmt:formatNumber
																	currencySymbol="" maxFractionDigits="0"
																	value="${totalDiscount * 1000}" type="currency" /> VND
														</span>
													</span></li>
													<li>Phí vận chuyển<span class="shipment">+
															30.000 VND</span></li>
													<li>Tổng tiền thực thanh toán<span
														class="payment-total"> <fmt:formatNumber
																currencySymbol="" maxFractionDigits="0"
																value="${(originPrice - totalDiscount + 30) * 1000}"
																type="currency" /> VND
													</span></li>
												</ul>
												<div id="purchase" class="button btn-checkout large">
													Mua hàng</a>
												</div>
											</div>
										</div>
								</c:if>

							</form>
						</div>
					</div>
				</div>
				<c:if test="${cart.size() > 0}">
					<div id="choose-address-modal" class="">
						<div class="my-overlay">
							<div class="my-modal">
								<form id="form-update-address">
									<div class="head">
										<div class="title">Chọn địa chỉ nhận hàng</div>
									</div>
									<div class="body">
										<div class="address-wrapper">
											<c:forEach var="address" items="${deliveryAddress}"
												varStatus="status">
												<div class="form-check"
													style="display: flex; align-items: center; margin-bottom: 10px;">
													<c:if test="${status.index == 0}">
														<input data-address-id="${address.id}" checked
															class="form-check-input" style="cursor: pointer;"
															type="radio" name="address"
															id="input-address-${status.index}">
														<label class="form-check-label"
															style="margin-left: 14px; cursor: pointer;"
															for="input-address-${status.index}">
															<div class="content" style="text-align: left">
																<div class="sub-addres">${address.addressDetail}</div>
																<div class="main-address">
																	<span class="ward"> ${address.ward} </span> , <span
																		class="district">${address.district} </span> , <span
																		class="province">${address.province} </span>
																</div>
															</div>
														</label>
													</c:if>

													<c:if test="${status.index != 0}">
														<input data-address-id="${address.id}"
															class="form-check-input" style="cursor: pointer;"
															type="radio" name="address"
															id="input-address-${status.index}">
														<label class="form-check-label"
															style="margin-left: 14px; cursor: pointer;"
															for="input-address-${status.index}">
															<div class="content" style="text-align: left">
																<div class="sub-addres">${address.addressDetail}</div>
																<div class="main-address">
																	<span class="ward"> ${address.ward} </span> , <span
																		class="district">${address.district} </span> , <span
																		class="province">${address.province} </span>
																</div>
															</div>
														</label>
													</c:if>
												</div>
											</c:forEach>
											<c:if test="${deliveryAddress.size() <= 0}">
												<div
													style="height: 50%; font-size: 2rem; display: flex; justify-content: center;">
													<div
														style="text-align: center; margin-bottom: 2rem; margin-right: 1rem">Bạn
														chưa có địa chỉ nào ?</div>
													<a
														style="text-transform: initial; text-decoration: underline; color: dodgerblue;"
														href="/delivery-address">Thêm địa chỉ mới</a>
												</div>
											</c:if>
										</div>
									</div>
									<div class="foot"
										style="display: flex; justify-content: center">
										<button class="button" id="order-btn" type="submit">ĐẶT
											HÀNG</button>
									</div>
									<div class="btn-close-modal">
										<div class="ti-close"></div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<jsp:include page="../partials/footer.jsp" />
	</div>
	<jsp:include page="../partials/external_foot.jsp" />
	<script src="resources/libs/localPicker.js"></script>
	<script>
	const plusBtn = $('.inc.qtybutton');
    const minusBtn = $('.dec.qtybutton');

    (function renderEditCartProductQuantity(){
       [...$('.cart-plus-minus')].forEach(productNode=>{
          const inventory = Number(productNode.dataset.inventory);
          const minusBtn = $(productNode).find(".dec.qtybutton");
          const plusBtn = $(productNode).find('.inc.qtybutton');

          const currentProductCount = Number($(productNode).find('.cart-plus-minus-box').val());
          if(currentProductCount <= 1){
             minusBtn.addClass('disable');
          }

          if(currentProductCount >= inventory){
             plusBtn.addClass('disable');
          }

       })
    })();
    
    minusBtn.click(async function() {
        if(!$(this).hasClass('disable')){
        const parentElement = $(this).parents(".cart-plus-minus"); 
        const inventory = parentElement[0].dataset.inventory;
        const cartID = parentElement[0].dataset.cartid;
        
        const productCount = parentElement.find('.cart-plus-minus-box');
        const plusBtn = parentElement.find('.inc.qtybutton');
        plusBtn.removeClass("disable");
        let productCountNumber = Number(productCount.val());
        
        if(productCountNumber > 1){
           productCountNumber--;
           productCount.val(productCountNumber);
           
           const formData = new FormData();
           
           formData.append('quantity', productCountNumber);    
          
           // update cart
           const {data, status} = await axios.post("/BooksStore/cart/update/" + cartID + ".htm", formData, { 
				headers: { 'Content-Type': 'multipart/form-data' } 
				}); 
           
           if(status === 200){
        	   const {message, status} = data;
        	   if(status){
        		   Swal.fire('Thành công !', message, 'success');
        		   setTimeout(()=>{location.reload()},2000);
        	   }else{
        		   Swal.fire('Xảy ra lỗi !', data.message, 'error'); 
        	   }
           }else{
        	   Swal.fire('Xảy ra lỗi !', 'Gọi API xảy ra lỗi, vui lòng thử lại !', 'error');   	   
           }
           
     }

        if(productCountNumber === 1) {
           $(this).addClass('disable');
        }
        }
     });

     plusBtn.click(async function() {
        if(!$(this).hasClass('disable')){
        const parentElement = $(this).parents(".cart-plus-minus"); 
        const inventory = parentElement[0].dataset.inventory;
        const cartID = parentElement[0].dataset.cartid;
        const productCount = parentElement.find('.cart-plus-minus-box');
        const minusBtn = parentElement.find('.dec.qtybutton');
        minusBtn.removeClass('disable');

        let productCountNumber = Number(productCount.val());

        if (productCountNumber <  Number(inventory)) {
           productCountNumber++;
           productCount.val(productCountNumber);
                      
           const formData = new FormData();
           
           formData.append('quantity', productCountNumber);  
           
           console.log();
          
           // update cart
           const {data, status} = await axios.post("/BooksStore/cart/update/" + cartID + ".htm", formData, { 
				headers: { 'Content-Type': 'multipart/form-data' } 
				}); 
           
           if(status === 200){
        	   const {message, status} = data;
        	   if(status){
        		   Swal.fire('Thành công !', message, 'success');
        		   setTimeout(()=>{location.reload()},2000);
        	   }else{
        		   Swal.fire('Xảy ra lỗi !', data.message, 'error'); 
        	   }
           }else{
        	   Swal.fire('Xảy ra lỗi !', 'Gọi API xảy ra lỗi, vui lòng thử lại !', 'error');   	   
           }
           
           
        }
        
        if(productCountNumber ===  Number(inventory) ){
           $(this).addClass('disable');
        }
        }
     });
	</script>

</body>
</html>