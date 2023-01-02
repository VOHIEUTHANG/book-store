<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>Purchase Orders</title>
<jsp:include page="../partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/purchase-order.css">
</head>
<body>
	<div id="root">
		<jsp:include page="../partials/header.jsp" />
		<div id="main">
			<div id="purchase-order">
				<div class="container page-container">
					<h1 class="page-heading">
						<span>Đơn mua</span>
					</h1>
					<div class="container">
<!-- 						<div class="row"> -->
<!-- 							<ul class="purchase-tabs d-flex"> -->
<!-- 								<li id="all" class="purchase-item col active">Tất cả</li> -->
<!-- 								<li id="processing" class="purchase-item col">Chờ xác nhận</li> -->
<!-- 								<li id="success" class="purchase-item col">Đã giao</li> -->
<!-- 								<li id="cancel" class="purchase-item col">Đã hủy</li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
						<div class="row mt-1">
							<div class="purchase-container">
								<c:forEach var="order" items="${orderList}">
									<div class="purchase-wrapper success">
										<div class="purchase-head">
											<div class="d-flex justify-content-between">
												<div>
													Mã đơn hàng: #ORDER-<span>${order.id}</span>
												</div>
												<div class="purchase-info d-flex">
													<div class="purchase-order-time">
														<span>${order.createAt }</span>
													</div>
													<div class="divider-vertical"></div>

													<c:choose>
														<c:when test="${order.deliveryStatus == 0}">
															<div class="purchase-status">Chờ xác nhận</div>
														</c:when>
														<c:when test="${order.deliveryStatus == 1}">
															<div class="purchase-status">Đang lấy hàng</div>
														</c:when>
														<c:when test="${order.deliveryStatus == 2}">
															<div class="purchase-status">Đang giao hàng</div>
														</c:when>
														<c:when test="${order.deliveryStatus == 3}">
															<div class="purchase-status">Giao hành thành công</div>
														</c:when>
														<c:when test="${order.deliveryStatus == 4}">
															<div class="purchase-status">Giao hành thất bại</div>
														</c:when>
													</c:choose>

												</div>
											</div>
										</div>
										<div class="purchase-body">
											<div class="purchase-product-list">
												<c:set var="orderItems" value="${order.orderItems}" />
												<c:forEach var="item" items="${orderItems}">
													<div
														class="purchase-product-item d-flex justify-content-between">
														<div class="left-section d-flex">
															<div class="product-img">
																<a
																	href="<c:url value='/product/detail/${item.product_orderItem.id}.htm'/>">
																	<img
																	src="${item.product_orderItem.images.get(0).imageURL}" />
																</a>
															</div>
															<div class="product-info">
																<a
																	href="<c:url value='/product/detail/${item.product_orderItem.id}.htm'/>">
																	<p class="name">${item.product_orderItem.name}</p>
																</a>
																<p class="price">
																	Giá:
																	<fmt:formatNumber currencySymbol=""
																		maxFractionDigits="0"
																		value="${item.product_orderItem.price * 1000}"
																		type="currency" />
																	VND
																</p>
																<p class="quantity">
																	<span class="denind-uppercase">x</span> <span>${item.quantity}</span>
																</p>
															</div>
														</div>
														<div class="center-section">
															<a
																href="<c:url value='/product/detail/${item.product_orderItem.id}.htm#comment'/>">
																<button class="button light">Đánh giá</button>
															</a>
														</div>
														<div class="right-section">

															<c:if test="${item.discountPercent > 0}">
																<div class="origin-price">
																	<fmt:formatNumber currencySymbol=""
																		maxFractionDigits="0"
																		value="${item.originPrice * 1000}" type="currency" />
																	VND x ${item.quantity}
																</div>
																<div class="discount">- ${item.discountPercent} %
																</div>
															</c:if>

															<c:if test="${item.discountPercent <= 0}">
																<div>-</div>
																<div>-</div>
															</c:if>
															<div class="last-price">
																<fmt:formatNumber currencySymbol=""
																	maxFractionDigits="0"
																	value="${item.actualPrice * 1000}" type="currency" />
																VND
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
										<div class="purchase-foot">
											<div class="d-flex justify-content-between">
												<div></div>
												<div class="total-price">
													Tổng tiền <span> <fmt:formatNumber currencySymbol=""
															maxFractionDigits="0" value="${order.totalPrice * 1000}"
															type="currency" /> VND
													</span>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>

								<c:if test="${orderList.size() <= 0}">
									<h3 style="margin-top: 2rem; text-align: center">Bạn không
										có đơn hàng nào !</h3>
								</c:if>

							</div>
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