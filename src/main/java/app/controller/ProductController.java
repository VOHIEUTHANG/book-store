package app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.dao.CategoryDao;
import app.dao.ProductDao;
import app.dao.WishlistDao;
import app.entity.Category;
import app.entity.Product;
import app.entity.User;
import app.entity.Wishlist;
import app.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {
	ProductDao productDao = new ProductDao();
	WishlistDao wishlistDao = new WishlistDao();
	ProductService productService = new ProductService();
	CategoryDao categoryDao = new CategoryDao();

	@RequestMapping(value = "delete/{productID}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("productID") String productID) {
		Boolean deleteResult = productService.deleteById(productID);
		return "redirect:/admin/products.htm";
	}

	@RequestMapping(value = "detail/{productID}", method = RequestMethod.GET)
	public String getProductDetail(@PathVariable("productID") String productID, ModelMap map) {
		List<Product> products = productDao.getProductById(productID);
		if (products.size() > 0) {
			Product product = products.get(0);
			List<Product> relatedProducts = productDao.getRelatedProducts(product.getId(), product.getAuthor().getId());
			map.addAttribute("product", product);
			map.addAttribute("relatedProducts", relatedProducts);
			map.addAttribute("images", product.getImages());
			map.addAttribute("comments", product.getComments());
			int price = Math.round(product.getPrice() * (100 - product.getDiscountPercent()) / 100);
			map.addAttribute("price", price);
		} else {
			return "404";
		}
		return "product-detail";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String getAllProduct(ModelMap model, HttpServletRequest request) {
		int ITEM_PER_PAGE = 9;
		List<Category> categoryList = categoryDao.getFamousestCategory();
		List<Product> productList = productDao.getAll();
		
		int totalProductCount = productList.size(); 

		model.addAttribute("categoryList", categoryList);

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);

		int category = ServletRequestUtils.getIntParameter(request, "category", -1);
		String sort = ServletRequestUtils.getStringParameter(request, "price", "");
		String searchText = ServletRequestUtils.getStringParameter(request, "text", "");
		
//       Preprocess input ===>         

		page -= 1;

//      Preprocess input ===> 

		Boolean isSortByPrice = false;

		if (sort.equals("desc") || sort.equals("asc")) {
			isSortByPrice = true;
		}

		Boolean isSearchByCategory = category > -1;
		Boolean isFilterBySearchText = searchText.length() > 0;

		if (isSortByPrice) {
			productList = productDao.sortByPrice(sort);
			if (isFilterBySearchText) {
				productList = productDao.filterBySearchTextAndSortByPrice(searchText, sort);
				if (isSearchByCategory) {
					productList = productDao.allCase(searchText, sort, category);
				}

			}else if (isSearchByCategory) {
				productList = productDao.filterByCategoryAndSortByPrice(category, sort);							
			}

		} else if (isSearchByCategory) {
			productList = productDao.filterByCategoryID(category);
			if (isSortByPrice) {
				productList = productDao.filterByCategoryAndSortByPrice(category, sort);
				if (isFilterBySearchText) {
					productList = productDao.allCase(searchText, sort, category);
				}
			}else if(isFilterBySearchText) {
				productList = productDao.filterByCategoryAndSearchText(category, searchText);
			}

		} else if (isFilterBySearchText) {
			productList = productDao.filterBySearchText(searchText);
			if (isSearchByCategory) {
				productList = productDao.filterByCategoryAndSearchText(category, searchText);
				if (isSortByPrice) {
					productList = productDao.allCase(searchText, sort, category);
				}
			}else if(isSortByPrice) {
				productList = productDao.filterBySearchTextAndSortByPrice(searchText, sort);
			}

		}

		PagedListHolder<Product> pagedListHolder = new PagedListHolder<Product>(productList);

		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(ITEM_PER_PAGE);
		pagedListHolder.setMaxLinkedPages(ITEM_PER_PAGE);

		List<Product> pageSlice = pagedListHolder.getPageList();

		model.addAttribute("currentPage", page);
		model.addAttribute("productList", pageSlice);
		model.addAttribute("pageCount", pagedListHolder.getPageCount());
		model.addAttribute("currentCount", productList.size());
		model.addAttribute("totalCount", totalProductCount);
		

		return "searching";
	}

}
