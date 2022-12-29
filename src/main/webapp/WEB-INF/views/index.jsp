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
</head>
<body>
	<div id="root">
		<jsp:include page="./partials/header.jsp" />
		<div id="main">
			<div class="banner-container">
				<div class="glide">
					<div class="glide__track" data-glide-el="track">
						<ul class="glide__slides">
							<li class="glide__slide"><a href="#"> <img
									src="resources/images/banner/banner_1.jpg" alt="" />
							</a></li>

							<li class="glide__slide"><a href="#"> <img
									src="resources/images/banner/banner_2.jpg" alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="resources/images/banner/banner_3.jpg" alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="resources/images/banner/banner_4.jpg" alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="resources/images/banner/banner_5.jpg" alt="" />
							</a></li>
						</ul>
					</div>
					<div class="glide__arrows" data-glide-el="controls">
						<button class="glide__arrow glide__arrow--left" data-glide-dir="<">
							<i class="fa-solid fa-angle-left"></i>
						</button>
						<button class="glide__arrow glide__arrow--right"
							data-glide-dir=">">
							<i class="fa-solid fa-angle-right"></i>
						</button>
					</div>
					<div class="glide__bullets" data-glide-el="controls[nav]">
						<button class="glide__bullet" data-glide-dir="=0"></button>
						<button class="glide__bullet" data-glide-dir="=1"></button>
						<button class="glide__bullet" data-glide-dir="=2"></button>
						<button class="glide__bullet" data-glide-dir="=3"></button>
						<button class="glide__bullet" data-glide-dir="=4"></button>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="main-container">
					<div class="category">
						<div class="section-title text-center mb-6" id="title">
							<h2>Sản phẩm mới nhất</h2>
						</div>
						<div class="category__container">
							<div class="glide category-product">
								<div class="glide__track" data-glide-el="track">
									<ul class="glide__slides">
										<c:forEach var="p" items="${newestProduct}" varStatus="status">
											<li class="glide__slide">
												<div class="category-item wow fadeInLeft">
													<a href="<c:url value='/product/detail/${p.id}.htm'/>" class="product-link">
														<div class="category__image">
															<img src="${p.images.get(0).imageURL}" alt="" />
														</div>
													</a>
													<div class="category__desc">
														<a href="<c:url value='/product/detail/${p.id}.htm'/>">
															<div class="category__brand">${p.author.authorName}</div>
														</a> <a href="#">
															<div class="category__name">${p.name}</div>
														</a>
														<div class="category__price">
															<span>Giá: ${p.price * (100-p.discountPercent)/100}.000
																VND</span>
														</div>
													</div>
													<div class="category__foot" style="height: 1rem"></div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
								<div class="glide__arrows" data-glide-el="controls">
									<button class="glide__arrow glide__arrow--left wow fadeInLeft"
										data-glide-dir="<">
										<i class="fa-solid fa-angle-left"></i>
									</button>
									<button
										class="glide__arrow glide__arrow--right wow fadeInRight"
										data-glide-dir=">">
										<i class="fa-solid fa-angle-right"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container-full ptb-5">
				<div class="brand-logo-area">
					<div class="glide brand-logo-wrapper">
						<div class="brand-logo-active glide__track" data-glide-el="track">
							<div class="glide__slides">
								<c:forEach var="a" items="${authors}">
									<div class="single-brand glide__slide px-6 py-4">
										<a class="d-flex flex-column justify-content-center align-items-center" href="#">
										<img style="border-radius:50%; width: 80px; height:80px" src="${a.authorPhoto}" alt="" /> 
											<div style="color:#333;text-transform: none;'" class="text-center mt-4">${a.authorName}</div>
										</a>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="all-products-area ptb-5">
				<div class="plr-10">
					<div class="container-fluid">
						<div class="section-title text-center mb-6">
							<h2>Có thể bạn thích</h2>
						</div>
						<div class="product-style">
							<div class="custom-row">
								<c:forEach var="p" items="${products}" varStatus="status">
									<div class="custom-col-5 custom-col-style mb-4 wow fadeInUp">
										<div class="product-wrapper">
											<div class="product-img">
												<a href="<c:url value='/product/detail/${p.id}.htm'/>"> <img src="${p.images.get(0).imageURL}"
													alt="" />
												</a>
												<div class="product-action flex-center">												
													<a href="<c:url value='/user/add-to-wishlist/${p.id}.htm'/>" class="animate-left add-to-wishlist" title="Wishlist"
														data-product-id="10">
														<i class="fa-regular fa-heart"></i>
													</a>
													<a href="#" class="animate-right" title="Add To Cart"
														data-product-id="99"> <span
														class="material-symbols-outlined"> shopping_cart </span>
													</a>
												</div>
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
												<div class="product-discount-label">-${p.discountPercent}%</div>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="load-more">
							<div class="button">Load More</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./partials/footer.jsp" />
	</div>
	<jsp:include page="./partials/external_foot.jsp" />
	<script>
		new Glide('.glide', {
			type : 'carousel',
			autoplay : 5000,
			animationDuration : 1000,
			hoverpause : true,
		}).mount();

		new Glide('.glide.category-product', {
			type : 'carousel',
			perView : 4,
			gap : 20,
		}).mount();

		new Glide('.glide.brand-logo-wrapper', {
			type : 'carousel',
			perView : 8,
			hoverpause : false,
			autoplay : 1000,
			animationDuration : 2000,
			gap : 20,
		}).mount();
	</script>
</body>
</html>