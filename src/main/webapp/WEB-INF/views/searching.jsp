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
.all-product .shop-page-wrapper {
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
									<div class="sidebar-widget mb-45">
										<h3 class="sidebar-title">Thể loại</h3>
										<div class="sidebar-categories">
											<ul>
												<li class="category-tab default" data-id="all"><a
													href="#"> Tất cả </a></li>
												<c:forEach var="category" items="${categoryList}">
													<li class="category-tab" data-id="${category.id}"><a
														href="#">${category.categoryName}</a></li>
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
														<span class="" style="color: #6c6c6c; font-weight: 500">${currentCount}</span>
														Sản phẩm tìm thấy trong <span class=""
															style="color: #6c6c6c; font-weight: 500">${totalCount}
														</span> sản phẩm
													</p>
												</div>
												<div class="shop-selector">
													<span class="nowrap">Sắp xếp: </span> <select
														class="form-select sort-select"
														aria-label="Default select example">
														<option value="default" selected>Mặc định</option>
														<option value="asc">Giá Thấp -> Cao</option>
														<option value="desc">Giá Cao -> Thấp</option>
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
																		<span><fmt:formatNumber currencySymbol=""
																				maxFractionDigits="0"
																				value="${product.price * 1000}" type="currency" />
																			VND</span>
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

													<div class="pagination-style mt-10 text-center"
														id="pagination">
														<ul class="pagination-tabs-list">
															<li class="pagination-control previous-page"><a
																href="#"><i class="ti-angle-left"></i></a></li>
															<c:forEach var="i" begin="1" end="${pageCount}">
																<c:if test="${currentPage+1 == i}">
																	<li class="pageNumber active" data-page-number="${i}"><a
																		href="<c:url value='product/search.htm?page=${i}' />">${i}</a></li>
																</c:if>
																<c:if test="${currentPage+1 != i}">
																	<li class="pageNumber" data-page-number="${i}"><a
																		href="<c:url value='product/search.htm?page=${i}' />">${i}</a></li>
																</c:if>
															</c:forEach>
															<li class="pagination-control next-page"><a href="#"><i
																	class="ti-angle-right"></i></a>
														</ul>

													</div>

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
	<script>
		const totalPageCount = <c:out value="${totalCount}"/>

		const url = new URL(window.location.href);
		const urlParams = new URLSearchParams(window.location.search);
		
		

		$(()=>{
			const categoryTabs = $('.category-tab');
			const currentCateID = urlParams.get('category');
			
			if (currentCateID) {
				const cateSelector = ".category-tab[data-id=" + currentCateID + "]";
				
				$('.category-tab.active').removeClass('active');
				$(cateSelector).addClass('active');
			} else {
				$(`.category-tab.default`).addClass('active');
			}

			categoryTabs.click(function(e) {
				e.preventDefault();
				const cateID = this.dataset.id;
				console.log('123');
				url.searchParams.set('category', cateID);
				window.location.href = url.href;
			})
		})
		
		$(()=>{
		
            const searchKeyword = urlParams.get('text');
            $('#search-input').val(searchKeyword);
           
            const searchInput = $('#search-input')
            const searchBtn  = $('#search-btn');
            const searchActions = ()=>{
               let keyword = searchInput.val().trim();
                  (()=>{
                  const keywordArray = keyword.split(' ');
                  keyword = keywordArray.reduce((acc,keyword)=>{
                     if(keyword !== ''){
                        return acc + ' ' +  keyword;
                     }
                     return acc;
                  },'');
               })();
               url.searchParams.set('text',keyword.trim());
               url.searchParams.set('page',1);
               window.location.href = url.href;
            }
            searchBtn.click(searchActions)
            searchInput.on('keyup', function (e) {
               if (e.key === 'Enter' || e.keyCode === 13) {
                  searchActions();
               }
            });
		})
		
		$(()=>{
			 const sortByPriceSelectNode = $('.sort-select');
			 sortByPriceSelectNode.on('change',function(){
                 const sortType = $(this).val();
                 url.searchParams.set('price', sortType);
                 window.location.href = url.href;
              })
              
             const sortType = urlParams.get('price');
			 
			const selector = ".sort-select option[value=" + sortType + "]";
			 
             $(selector)?.attr('selected', true);
		})
		
		
	</script>

	<script>
		
	</script>
</body>
</html>