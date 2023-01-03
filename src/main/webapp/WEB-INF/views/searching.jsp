<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>Searching</title>
<jsp:include page="./partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/searching.css">
<style>
	.all-product .shop-page-wrapper{
	  padding: 6rem 0;
	}	
</style>
</head>
<body>
	<div id="root">
		<jsp:include page="./partials/header.jsp" />
		<div id="main">
			<div class="all-product">
				<div class="shop-page-wrapper">
					<div class="container">
						<div class="row gx-5">
							<div class="col-lg-3" id="sticky-parent">
								<div class="shop-sidebar" id="sticky-element">
									<div class="sidebar-widget mb-50">
										<h3 class="sidebar-title">Tìm kiếm</h3>
										<div class="sidebar-search">
											<div class="search-input-wrapper">
												<input id="search-input" placeholder="Tìm kiếm ..."
													type="text" />
												<button id="search-btn">
													<i class="ti-search"></i>
												</button>
											</div>
										</div>
									</div>
									<div class="sidebar-widget mb-40">
										<h3 class="sidebar-title">Lọc theo giá</h3>
										<div class="price_filter">
											<div id="slider-range"></div>
											<div class="price_slider_amount">
												<div class="label-input">
													<div class="amount-range-slider"></div>
												</div>
												<label> <span id="start-price">0 VND</span> - <span
													id="end-price">0 VND</span>
												</label>
											</div>
										</div>
									</div>
									<div class="sidebar-widget mb-45">
										<h3 class="sidebar-title">Danh mục</h3>
										<div class="sidebar-categories">
											<ul>
												<li class="category-tab default" data-id="all"><a
													href="#">Tất cả<span>${productList.size()}</span></a></li>
												<c:forEach var="category" items="${categoryList}">
													<li class="category-tab" data-id="${category.id}"><a
														href="#">${category.categoryName}<span>${category.products.size()}</span></a>
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>									
								</div>
							</div>
							<div class="col-lg-9">
								<div class="shop-product-wrapper">
									<div class="shop-bar-area">
										<div class="shop-bar pb-60">
											<div class="shop-found-selector">
												<div class="shop-found">
													<p>
														<span class="" style="color: #6c6c6c; font-weight: 500">xx</span>
														Sản phẩm tìm thấy trong <span class=""
															style="color: #6c6c6c; font-weight: 500">${productList.size()}</span>
														sản phẩm
													</p>
												</div>
												<div class="shop-selector">
													<span class="nowrap">Sắp xếp: </span> <select
														class="form-select sort-select"
														aria-label="Default select example">
														<option value="default" selected>Mặc định</option>
														<option value="ASC">Giá Thấp -> Cao</option>
														<option value="DESC">Giá Cao -> Thấp</option>
													</select>
												</div>
											</div>
										</div>
										<div class="shop-product-content tab-content">
											<div id="grid-sidebar7" class="tab-pane fade active show">
												<div class="row">
													<c:if test="${ productList.size() > 0}">
														<c:forEach var="product" items="${productList}">
															<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12">
																<div class="product-wrapper product-box-style mb-30">
																	<div class="product-img">
																		<a
																			href="<c:url value='/product/detail/${product.id}.htm'/>">
																			<img src="${product.images.get(0).imageURL}" alt="" />
																		</a>
																		<div class="product-action">
																			<div class="animate-left add-to-wishlist"
																				title="Wishlist" data-product-id="${product.id}">
																				<i class="fa-regular fa-heart"></i>
																			</div>
																			<a class="animate-right" title="Add To Cart"
																				href="<c:url value='/product/detail/${product.id}.htm'/>">
																				<i class="ti-shopping-cart"></i>
																			</a>
																		</div>
																	</div>
																	<div class="product-content">
																		<h4>
																			<a
																				href="<c:url value='/product/detail/${product.id}.htm'/>">
																				${product.name} </a>
																		</h4>
																		<span><fmt:formatNumber currencySymbol="" maxFractionDigits="0" value="${product.price * 1000}" type="currency" /> VND</span>
																	</div>
																	<c:if test="${product.discountPercent > 0}">
																		<div class="product-discount-label">
																			-${product.discountPercent}</div>
																	</c:if>
																</div>
															</div>
														</c:forEach>
													</c:if>

													<c:if test="${ productList.size() <= 0}">
														<div style="flex-direction: column"
															class="d-flex justify-content-center align-items-center">
															<i style="font-size: 6rem !important" class="ti-na">
															</i>
															<p
																style="text-align: center; margin-top: 4rem; font-size: 2.4rem"
																class="nothing-title">Không tìm thấy sản phầm nào !</p>
														</div>
													</c:if>

												</div>
											</div>
										</div>
									</div>
								</div>
								
								<c:if test="${productList.size() > 0}">
								<div class="pagination-style mt-10 text-center" id="pagination">
									<ul class="pagination-tabs-list"></ul>
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
</body>
</html>