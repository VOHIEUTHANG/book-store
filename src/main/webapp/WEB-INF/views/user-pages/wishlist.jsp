<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/" />
<title>Wish list</title>
<jsp:include page="../partials/external_head.jsp" />
<link rel="stylesheet" href="resources/css/wishlist.css">
</head>
<body>
	<div id="root">
		<jsp:include page="../partials/header.jsp" />
		<div id="main">
			<div class="cart-main-area pt-95 pb-100 wishlist">
				<div class="container">
					<h1 class="cart-heading">Wishlist</h1>
					<form action="#">
						<div class="product-style">
							<div class="custom-row row gx-5 gy-5">
								<c:if test="${wishlist.size() <= 0}">
									<div class="d-flex justify-content-center align-items-center"
										style="flex-direction: column">
										<i style="font-size: 6rem" class="ti-dropbox"></i>
										<h2 class="cart-heading"
											style="font-size: 2.8rem; margin-top: 3rem; color: #444">
											Wishlist chưa có sản phẩm nào :((</h2>
									</div>
								</c:if>
								<c:forEach var="w" items="${wishlist}">
									<div
										class="col col-sm-12 col-md-6 col-lg-4 col-xl-3 product-item">
										<div class="product-wrapper">
											<div class="product-img">
												<a
													href="<c:url value='/product/detail/${w.product_wishlist.id}.htm'/>">
													<img src="${w.product_wishlist.images.get(0).imageURL}"
													alt="" />
												</a> <a href="#" class="add-to-cart"> <i
													class="ti-shopping-cart"></i>
												</a>
											</div>
											<div class="product-content">
												<div class="category__brand">${w.product_wishlist.author.authorName}</div>
												<h4>
													<a
														href="<c:url value='/product/detail/${w.product_wishlist.id}.htm'/>">${w.product_wishlist.name}</a>
												</h4>
												<span>${w.product_wishlist.price}.000 VND</span>
											</div>
											<a href="<c:url value='/user/wishlist/delete/${w.id}.htm'/>"
												class="product-remove"
												data-product-id="${w.product_wishlist.id}"> <i
												class="ti-close"></i>
											</a>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="../partials/footer.jsp" />
	</div>
	<jsp:include page="../partials/external_foot.jsp" />
</body>
</html>