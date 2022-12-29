<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
										class="nav-link icon-cart" href="/cart"> <i
											class="ti-shopping-cart"></i> <span class="shop-count pink">0</span>
									</a>
										<ul class="cart-dropdown">
											<div style="flex-direction: column"
												class="d-flex justify-content-center align-items-center">
												<i style="font-size: 4rem !important" class="ti-face-sad">
												</i>
												<p style="text-align: center; margin-top: 2rem"
													class="nothing-title">Bạn không có sản phẩm nào trong
													giỏ hàng !</p>
											</div>
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
