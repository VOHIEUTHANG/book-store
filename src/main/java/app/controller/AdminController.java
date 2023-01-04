package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.dao.*;
import app.entity.*;

@Controller
@RequestMapping("admin")
public class AdminController {
	ProductDao productDao = new ProductDao();
	CategoryDao categoryDao = new CategoryDao();
	AuthorDao authorDao = new AuthorDao();
	PublisherDao publisherDao = new PublisherDao();
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String index() {
		return "admin-pages/home";
	}
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String getProductsPage(ModelMap model) {
		List<Product> products = new ArrayList<>();
		List<Category> categories = categoryDao.getAll();
		List<Author> authors = authorDao.getAll();
		List<Publisher> publishers = publisherDao.getAll();
		products = productDao.getAll();
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		model.addAttribute("authors", authors);
		model.addAttribute("publishers", publishers);
		return "admin-pages/products";
	}

}
