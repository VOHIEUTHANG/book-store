package app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.dao.ProductDao;
import app.dao.WishlistDao;
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



}
