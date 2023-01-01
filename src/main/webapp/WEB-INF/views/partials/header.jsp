<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<header class="header" id="header">
	<div class="header__container">
		<div class="header__logobar">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="nav-wrapper flex-start">
							<ul class="nav-list">
								<li class="nav-item search-section"><a class="nav-link"
									href="/products/search"> <span
										class="material-symbols-outlined" style="font-size: 28px">
											search </span></a></li>
							</ul>
						</div>
					</div>
					<div class="col">
						<div class="logo-wrapper wow flipInY">
							<a href="<c:url value='/index.htm'/>"
								style="display: flex; justify-content: center;"
								class="main-logo"> <span> Bookstore</span>
							</a>
						</div>
					</div>
					<div class="col">
						<div class="nav-wrapper">
							<ul class="nav-list">
								<c:if test="${user != null}">
									<li class="nav-item user-cart header-cart cart-res"><a
										class="nav-link icon-cart"
										href="<c:url value='/cart/view.htm' />"> <i
											class="ti-shopping-cart"></i> <span class="shop-count pink">${carts.size()}</span>
									</a>
										<ul style="overflow-y: scroll; max-height: 70vh"
											class="cart-dropdown">
											<c:if test="${carts.size() == 0}">
												<div style="flex-direction: column"
													class="d-flex justify-content-center align-items-center">
													<i style="font-size: 4rem !important" class="ti-face-sad">
													</i>
													<p style="text-align: center; margin-top: 2rem"
														class="nothing-title">Bạn không có sản phẩm nào trong
														giỏ hàng !</p>
												</div>
											</c:if>
											<c:set var="totalPrice" value="0" />
											<c:if test="${carts.size() > 0}">
												<c:forEach var="cart" items="${carts}">
													<li class="single-product-cart">
														<div
															style="width: 100px; height: 100px; overflow: hidden; border: 1px solid #cccc; padding: 4px;"
															class="cart-img">
															<a
																href="<c:url value='/product/detail/${cart.product_cart.id}.htm'/>"><img
																src="${cart.product_cart.images.get(0).imageURL}" alt="" /></a>
														</div>
														<div class="cart-title">
															<h5>
																<a
																	href="<c:url value='/product/detail/${cart.product_cart.id}.htm'/>">
																	${cart.product_cart.name} </a>
															</h5>
															<span> <fmt:formatNumber currencySymbol=""
																	maxFractionDigits="0"
																	value="${cart.product_cart.price * (100 - cart.product_cart.discountPercent) * 10}"
																	type="currency" /> VND x ${cart.quantity}
															</span>
															<c:set var="totalPrice"
																value="${totalPrice + (cart.product_cart.price * (100 - cart.product_cart.discountPercent)/100) * cart.quantity}" />
														</div>
														<div class="cart-delete">
															<a href="<c:url value='/cart/delete/${cart.id}.htm'/>">
																<i class="ti-trash"></i>
															</a>
														</div>
													</li>
												</c:forEach>
												<div class="cart-foot">
													<li class="cart-space">
														<div class="cart-sub">
															<h4>Tổng cộng</h4>
														</div>
														<div class="cart-price">
															<h4>
																<fmt:formatNumber currencySymbol=""
																	maxFractionDigits="0" value="${totalPrice * 1000}"
																	type="currency" />
																VND
															</h4>
														</div>
													</li>
													<li class="cart-btn-wrapper"><a class="button full"
														href="<c:url value='/cart/view.htm'/>">Xem giỏ hàng</a></li>
												</div>
											</c:if>

										</ul></li>
									<li class="nav-item user-info">
										<div class="nav-item__avatar">
											<img src="${user.avatar}" alt="" />
										</div>
										<div class="user-options">
											<a href="<c:url value='/user/profile.htm'/>"
												id="user-profile" class="user-options__item info"> <i
												class="fa-regular fa-user"></i> <span>Thông tin</span>
											</a> <a href="<c:url value='/user/wishlist.htm'/>"
												class="user-options__item"> <i
												class="fa-regular fa-heart"></i> <span>Wishlist</span>
											</a> <a href="<c:url value='/user/delivery-address.htm'/>"
												class="user-options__item"> <i class="ti-location-pin"></i>
												<span>Địa chỉ</span>
											</a> <a href="<c:url value='/user/order.htm'/>"
												class="user-options__item"> <i class="ti-pencil-alt"></i>
												<span>Đơn mua</span>
											</a> <a href="<c:url value='/user/change-password.htm'/>"
												class="user-options__item"> <i class="ti-key"></i> <span>Đổi
													mật khẩu</span>
											</a> <a href="<c:url value='/logout.htm'/>"
												class="user-options__item logout"> <i
												class="fa-solid fa-arrow-right-from-bracket"></i> <span>Đăng
													xuất</span>
											</a>
										</div>
									</li>
								</c:if>
								<c:if test="${user == null}">
									<li class="nav-item user-section"><a class="nav-link"
										href="<c:url value='/login.htm'/>"><i
											class="fa-regular fa-user"></i></a></li>
								</c:if>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="header__menu">
			<div class="menu-wrapper">
				<ul class="menu-list">
					<li class="menu-item"><a href="/products/search?brandID=6"
						class="menu-link">nike</a></li>
					<li class="menu-item"><a href="/products/search?brandID=1"
						class="menu-link">adidas</a></li>
					<li class="menu-item"><a href="/products/search?brandID=4"
						class="menu-link">converse</a></li>
					<li class="menu-item"><a href="/products/search?brandID=10"
						class="menu-link">vans</a></li>
					<li class="menu-item"><a href="#" class="menu-link">Danh
							mục</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href=""><span>category
										name</span></a></li>
							<li><a class="dropdown-item" href=""><span>category
										name</span></a></li>
							<li><a class="dropdown-item" href=""><span>category
										name</span></a></li>
						</ul></li>
					<li class="menu-item"><a href="#" class="menu-link">All
							Brand</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href=""><span>Brand
										name 1</span></a></li>
							<li><a class="dropdown-item" href=""><span>Brand
										name 2</span></a></li>
						</ul></li>
					<li class="menu-item" id="discount-btn"><a href=""
						class="wow tada menu-link menu-link--sale">Discount</a></li>
				</ul>
			</div>
		</div>
	</div>
</header>
