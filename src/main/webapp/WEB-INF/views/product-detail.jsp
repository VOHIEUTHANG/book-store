<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>PRODUCT DETAIL</title>
<jsp:include page="./partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/product-detail.css">
</head>
<body>
	<div id="root">
		<jsp:include page="./partials/header.jsp" />
		<div id="main">
			<div id="product-detail-page">
				<div class="product-details">
					<div class="container">
						<div class="row gx-5">
							<div class="col-md-12 col-lg-6 col-12">
								<div class="product-details-img-content">
									<div class="product-details-tab mr-35 product-details-tab2">
										<div class="product-details-large tab-content">
											<c:forEach var="img" items="${images}" varStatus="status">
												<c:if test="${status.index == 0}">
													<div class="tab-pane fade active show"
														id="pro-details${status.index + 1}" role="tabpanel">
														<div class="easyzoom easyzoom--overlay">
															<a target="_blank" href="${img.imageURL}"> <img
																src="${img.imageURL}" alt="" />
															</a>
														</div>
													</div>
												</c:if>
												<c:if test="${status.index > 0}">
													<div class="tab-pane fade"
														id="pro-details${status.index + 1}" role="tabpanel">
														<div class="easyzoom easyzoom--overlay">
															<a target="_blank" href="${img.imageURL}"> <img
																src="${img.imageURL}" alt="" />
															</a>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</div>
										<div class="product-details-small nav ml-10 product-details-2"
											role="tablist">
											<div class="swiper product-images-slide">
												<div class="swiper-wrapper">
													<c:forEach var="img" items="${images}" varStatus="status">
														<c:if test="${status.index == 0}">
															<a class="mb-10 swiper-slide active "
																href="#pro-details${status.index + 1}"
																data-bs-toggle="list" role="tab" aria-selected="true">
																<img class="main-image" src="${img.imageURL}" alt="" />
															</a>
														</c:if>
														<c:if test="${status.index > 0}">
															<a class="mb-10 swiper-slide "
																href="#pro-details${status.index + 1}"
																data-bs-toggle="list" role="tab" aria-selected="true">
																<img class="" src="${img.imageURL}" alt="" />
															</a>
														</c:if>
													</c:forEach>
												</div>
												<div class="swiper-pagination"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 col-lg-6 col-12">
								<div class="product-details-content">
									<h3 id="product-name">
										${product.name }
										<c:if test="${product.discountPercent > 0}">
											<span class="discount"> (${product.discountPercent}-%)</span>
										</c:if>
									</h3>

									<div class="details-price">
										<c:choose>
											<c:when test="${product.discountPercent > 0}">
												<div>
													<span class="remove">${product.price}.000 VND</span>
												</div>
												<span>Giá: ${price}.000 VND</span>
											</c:when>
											<c:when test="${product.discountPercent <= 0}">
												<span class="">Giá: ${product.price}.000 VND</span>
											</c:when>
										</c:choose>
									</div>
									<p style="text-transform: none; font-size: 18px;">${product.description}</p>
									<div class="quick-view-select">
										<div class="select-option-part">
											<div style="margin-top: 2rem">
												Số lượng trong kho: <span
													style="font-size: 1.8rem; font-weight: 400"
													class="product-quantity">${product.inventory}</span>
											</div>
										</div>
									</div>
									<div class="quickview-plus-minus">
										<div class="cart-plus-minus">
											<div class="dec qtybutton">-</div>
											<input type="text" value="1" name="qtybutton"
												class="cart-plus-minus-box" id="product-count" />
											<div class="inc qtybutton">+</div>
										</div>
										<div class="quickview-btn-cart">
											<div class="button" id="add-to-cart">add to cart</div>
										</div>
										<div class="quickview-btn-wishlist">
											<div class="btn-hover" id="add-to-wishlist"
												data-product-id="${product.id}">
												<i class="fa-regular fa-heart"></i>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="product-description-review-area">
					<div class="container" id="comment">
						<div class="product-description-review text-center">
							<div class="description-review-text">
								<div class="section-title-3">
									<h2>Mô tả sản phẩm</h2>
								</div>
								<div>
									<p class="product-desc">${product.description}</p>
								</div>
								<hr />
								<div class="section-title-3">
									<h2>Đánh giá</h2>
								</div>
								<div class="">
									<c:if test="${user != null}">
										<div class="current-comment-wrapper">
											<div class="current-user">
												<img src="${user.avatar}" alt="" />
											</div>
											<div class="current-content-wrapper">
												<input id="comment-content" type="text"
													placeholder="Viết bình luận" />
												<div class="content-body d-flex justify-content-between">
													<div class="current-image">
														<div class="image-placeholder" style="position: relative">
															<input type="file" name="" id="image-review" hidden /> <label
																for="image-review">
																<div class="plus-icon">
																	<i class="ti-plus"></i>
																</div>
															</label> <img id="image-preview" src=""
																style="position: absolute; top: 0; left: 0; z-index: 100"
																width="100%" height="100%" alt="" class="hidden" />
														</div>
													</div>
													<div class="submit-image" data-product-id="${product.id}">
														<div class="button">Bình luận</div>
													</div>
												</div>
											</div>
										</div>
									</c:if>

									<c:if test="${user == null}">
										<a href="<c:url value='/login.htm'/>">
											<div class="button">Viết đánh giá</div>
										</a>
									</c:if>

									<hr />
									<div class="comment-container">
										<div class="comment-list">

											<c:forEach var="cm" items="${comments}">
												<div class="comment-item">
													<div class="comment-avatar">
														<img src="${cm.user_comment.avatar}" alt="" />
													</div>
													<div class="comment-content">
														<div class="user-name">${cm.user_comment.fullName}</div>
														<div class="comment-title">${cm.content}</div>
														<c:if test="${cm.imageURL != null}">
															<div class="comment-images-wrapper">
																<img src="${cm.imageURL}" alt="" />
															</div>
														</c:if>
													</div>

													<c:if
														test="${cm.user_comment.username.equals(user.username)}">
														<div class="delete-comment" data-comment-id="${cm.id}">
															<i class="ti-close"></i>
														</div>
													</c:if>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="product-area">
					<div class="container">
						<div class="section-title-3">
							<h2>Sản phẩm liên quan</h2>
						</div>
						<div class="product-style">
							<div class="related-products">
								<c:if test="${relatedProducts.size() > 0}">
									<div class="related-product-active">
										<div class="row gy-4">
											<c:forEach var="p" items="${relatedProducts}">
												<div class="col col-sm-12 col-md-6 col-lg-4 col-xl-3">
													<div class="product-wrapper">
														<div class="product-img">
															<a href="<c:url value='/product/detail/${p.id}.htm'/>"> <img
																src="${p.images.get(0).imageURL}" alt="" />
															</a>
														</div>
														<div class="product-content">
															<h4>
																<a href="<c:url value='/product/detail/${p.id}.htm'/>">${p.name}</a>
															</h4>

															<c:choose>
																<c:when test="${p.discountPercent > 0}">
																	<span class="remove">${p.price}.000 VND</span>
																	<span>Giá: ${p.price * (100-p.discountPercent)/100}.000
																		VND</span>
																</c:when>
																<c:when test="${p.discountPercent <= 0}">
																	<span class="">Giá: ${p.price}.000 VND</span>
																</c:when>
															</c:choose>
														</div>
														<c:if test="${p.discountPercent > 0}">
															<div class="product-discount-label">
																-${p.discountPercent}</div>
														</c:if>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
								<c:if test="${relatedProducts.size() <= 0}">
									<div style="flex-direction: column"
										class="d-flex justify-content-center align-items-center">
										<i style="font-size: 6rem !important" class="ti-dropbox">
										</i>
										<p
											style="text-align: center; margin-top: 4rem; font-size: 2.2rem"
											class="nothing-title">Hiện tại không có sản phẩm nào liên
											quan !</p>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./partials/footer.jsp" />
	</div>
	<jsp:include page="./partials/external_foot.jsp" />
	<script>
		const swiper = new Swiper('.swiper.product-images-slide', {
			direction : 'vertical',
			slidesPerView : 4,
			pagination : {
				el : '.swiper-pagination',
				clickable : true,
			},
		});
	</script>
	<script>
	   const productCountEle = $('#product-count');
       const plusBtn = $('.inc.qtybutton');
       const minusBtn = $('.dec.qtybutton');
       let currentVal = Number(productCountEle.val());
       
       minusBtn.click(() => {
              currentVal > 1 && currentVal--;
              productCountEle.val(currentVal);
        });

       plusBtn.click(() => {
          const currentProductCount = $('.product-quantity').text();

             const convertNumber = Number(currentProductCount);
             if (currentVal < convertNumber) {
                currentVal++;
                productCountEle.val(currentVal);
             }
       });
       
	</script>
</body>
</html>