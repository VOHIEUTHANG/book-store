<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
									src="resources/images/banner_1.jpg"
									alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="https://cdn.shopify.com/s/files/1/0603/3031/1875/files/Web_Desktop_2x_15d4d2e9-e527-4ad9-b787-82f98cff873b_1950x.jpg?v=1660272572"
									alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="https://cdn.shopify.com/s/files/1/0603/3031/1875/files/0715_ct8532-105_web_03_1512x.jpg?v=1657880022"
									alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="https://cdn.shopify.com/s/files/1/0603/3031/1875/files/0715_BB550WT1_web_03_1512x.jpg?v=1657880102"
									alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="https://cdn.shopify.com/s/files/1/0603/3031/1875/files/Web_Desktop_2x_d7775c58-a3f5-4e05-b95c-00a19e19a32d_1950x.jpg?v=1657879961"
									alt="" />
							</a></li>
							<li class="glide__slide"><a href="#"> <img
									src="https://cdn.shopify.com/s/files/1/0603/3031/1875/files/Web_Desktop_2x_e9224153-8aa8-400e-a3ce-00a2621e5d8e_1950x.jpg?v=1658485552"
									alt="" />
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
										<li class="glide__slide">
											<div class="category-item wow fadeInLeft">
												<a href="#" class="product-link">
													<div class="category__image">
														<img
															src="https://cf.shopee.vn/file/a9c5a715c3afa17f3b50177c5289e1c3"
															alt="" />
													</div>
												</a>
												<div class="category__desc">
													<a href="#">
														<div class="category__brand">brand name</div>
													</a> <a href="#">
														<div class="category__name">book name</div>
													</a>
													<div class="category__price">
														GIÁ <span class="remove">1.200.000 VND</span> <span>1.100.000
															VND</span>
													</div>
												</div>
												<div class="product-discount-label">-12%</div>
												<div class="category__foot" style="height: 1rem"></div>
											</div>
										</li>
										<li class="glide__slide">
											<div class="category-item wow fadeInLeft">
												<a href="#" class="product-link">
													<div class="category__image">
														<img src="https://picsum.photos/500/300?random=9" alt="" />
													</div>
												</a>
												<div class="category__desc">
													<a href="#">
														<div class="category__brand">brand name</div>
													</a> <a href="#">
														<div class="category__name">book name</div>
													</a>
													<div class="category__price">
														GIÁ <span class="remove">1.200.000 VND</span> <span>1.100.000
															VND</span>
													</div>
												</div>
												<div class="product-discount-label">-12%</div>
												<div class="category__foot" style="height: 1rem"></div>
											</div>
										</li>
										<li class="glide__slide">
											<div class="category-item wow fadeInLeft">
												<a href="#" class="product-link">
													<div class="category__image">
														<img src="https://picsum.photos/500/300?random=11" alt="" />
													</div>
												</a>
												<div class="category__desc">
													<a href="#">
														<div class="category__brand">brand name</div>
													</a> <a href="#">
														<div class="category__name">book name</div>
													</a>
													<div class="category__price">
														GIÁ <span>1.100.000 VND</span>
													</div>
												</div>

												<div class="category__foot" style="height: 1rem"></div>
											</div>
										</li>
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
								<div class="single-brand glide__slide">
									<a href="#"><img
										src="https://picsum.photos/500/300?random=1" alt="" /></a>
								</div>
								<div class="single-brand glide__slide">
									<a href="#"><img
										src="https://picsum.photos/500/300?random=2" alt="" /></a>
								</div>
								<div class="single-brand glide__slide">
									<a href="#"><img
										src="https://picsum.photos/500/300?random=3" alt="" /></a>
								</div>
								<div class="single-brand glide__slide">
									<a href="#"><img
										src="https://picsum.photos/500/300?random=4" alt="" /></a>
								</div>
								<div class="single-brand glide__slide">
									<a href="#"><img
										src="https://picsum.photos/500/300?random=5" alt="" /></a>
								</div>
								<div class="single-brand glide__slide">
									<a href="#"><img
										src="https://picsum.photos/500/300?random=6" alt="" /> </a>
								</div>
								<div class="single-brand glide__slide">
									<a href="#"><img
										src="https://picsum.photos/500/300?random=7" alt="" /></a>
								</div>
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
								<div class="custom-col-5 custom-col-style mb-4 wow fadeInUp">
									<div class="product-wrapper">
										<div class="product-img">
											<a href="#"> <img
												src="https://picsum.photos/500/300?random=20" alt="" />
											</a>
											<div class="product-action flex-center">
												<div class="animate-left add-to-wishlist" title="Wishlist"
													data-product-id="10">
													<i class="fa-regular fa-heart"></i>
												</div>
												<a href="#" class="animate-right" title="Add To Cart"
													data-product-id="99"> <span
													class="material-symbols-outlined"> shopping_cart </span>
												</a>
											</div>
										</div>
										<div class="product-content">
											<a href="#">
												<div class="category__brand">123</div>
											</a>
											<h4>
												<a href="#">book name</a>
											</h4>
											<span class="remove">Giá sản phẩm</span> <span>Giá sau
												giảm giá</span>
										</div>
										<div class="product-discount-label">-10%</div>
									</div>
								</div>
								<div class="custom-col-5 custom-col-style mb-4 wow fadeInUp">
									<div class="product-wrapper">
										<div class="product-img">
											<a href="#"> <img
												src="https://picsum.photos/500/300?random=24" alt="" />
											</a>
											<div class="product-action">
												<div class="animate-left add-to-wishlist" title="Wishlist"
													data-product-id="10">
													<i class="fa-regular fa-heart"></i>
												</div>
												<a href="#" class="animate-right" title="Add To Cart"
													data-product-id="99"> <span
													class="material-symbols-outlined"> shopping_cart </span>
												</a>
											</div>
										</div>
										<div class="product-content">
											<a href="#">
												<div class="category__brand">123</div>
											</a>
											<h4>
												<a href="#">book name</a>
											</h4>
											<span class="remove">Giá sản phẩm</span> <span>Giá sau
												giảm giá</span>
										</div>
										<div class="product-discount-label">-10%</div>
									</div>
								</div>
								<div class="custom-col-5 custom-col-style mb-4 wow fadeInUp">
									<div class="product-wrapper">
										<div class="product-img">
											<a href="#"> <img
												src="https://picsum.photos/500/300?random=29" alt="" />
											</a>
											<div class="product-action">
												<div class="animate-left add-to-wishlist" title="Wishlist"
													data-product-id="10">
													<i class="fa-regular fa-heart"></i>
												</div>
												<a href="#" class="animate-right" title="Add To Cart"
													data-product-id="99"> <span
													class="material-symbols-outlined"> shopping_cart </span>
												</a>
											</div>
										</div>
										<div class="product-content">
											<a href="#">
												<div class="category__brand">123</div>
											</a>
											<h4>
												<a href="#">book name</a>
											</h4>
											<span class="remove">Giá sản phẩm</span> <span>Giá sau
												giảm giá</span>
										</div>
										<div class="product-discount-label">-10%</div>
									</div>
								</div>
								<div class="custom-col-5 custom-col-style mb-4 wow fadeInUp">
									<div class="product-wrapper">
										<div class="product-img">
											<a href="#"> <img
												src="https://picsum.photos/500/300?random=24" alt="" />
											</a>
											<div class="product-action">
												<div class="animate-left add-to-wishlist" title="Wishlist"
													data-product-id="10">
													<i class="fa-regular fa-heart"></i>
												</div>
												<a href="#" class="animate-right" title="Add To Cart"
													data-product-id="99"> <span
													class="material-symbols-outlined"> shopping_cart </span>
												</a>
											</div>
										</div>
										<div class="product-content">
											<a href="#">
												<div class="category__brand">123</div>
											</a>
											<h4>
												<a href="#">book name</a>
											</h4>
											<span class="remove">Giá sản phẩm</span> <span>Giá sau
												giảm giá</span>
										</div>
										<div class="product-discount-label">-10%</div>
									</div>
								</div>
								<div class="custom-col-5 custom-col-style mb-4 wow fadeInUp">
									<div class="product-wrapper">
										<div class="product-img">
											<a href="#"> <img
												src="https://picsum.photos/500/300?random=31" alt="" />
											</a>
											<div class="product-action">
												<div class="animate-left add-to-wishlist" title="Wishlist"
													data-product-id="10">
													<i class="fa-regular fa-heart"></i>
												</div>
												<a href="#" class="animate-right" title="Add To Cart"
													data-product-id="99"> <span
													class="material-symbols-outlined"> shopping_cart </span>
												</a>
											</div>
										</div>
										<div class="product-content">
											<a href="#">
												<div class="category__brand">123</div>
											</a>
											<h4>
												<a href="#">book name</a>
											</h4>
											<span class="remove">Giá sản phẩm</span> <span>Giá sau
												giảm giá</span>
										</div>
										<div class="product-discount-label">-10%</div>
									</div>
								</div>
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