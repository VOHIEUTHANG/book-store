package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.dao.ProductDao;
import app.entity.Product;

@Controller
@RequestMapping("admin")
public class AdminController {
	ProductDao productDao = new ProductDao();
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String index() {
		return "admin-pages/home";
	}
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String getProductsPage(ModelMap model) {
		List<Product> products = new ArrayList<>();
		products = productDao.getAll();
		model.addAttribute("products", products);
		return "admin-pages/products";
	}

}
