package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.dao.AuthorDao;
import app.dao.CategoryDao;
import app.dao.ProductDao;
import app.dao.ProductImagesDao;
import app.dao.PublisherDao;
import app.entity.Author;
import app.entity.Category;
import app.entity.Product;
import app.entity.ProductImage;
import app.entity.Publisher;

@Controller
public class MainController {
	CategoryDao categoryDao = new CategoryDao();
	AuthorDao authorDao = new AuthorDao();
	PublisherDao publisherDao = new PublisherDao();
	ProductDao productDao = new ProductDao();
	ProductImagesDao productImagesDao = new ProductImagesDao();

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping("login")
	public String getFormLogin() {
		return "login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "index";
	}

	@RequestMapping("register")
	public String getFormRegister() {
		return "register";
	}

	@RequestMapping("/403")
	String forbiddenPage() {
		return "403";
	}

	@RequestMapping("/404")
	String notFoundPage() {
		return "404";
	}

	@RequestMapping("/init-db")
	@ResponseBody
	String initDatabase() {
		Author author1 = new Author("Nguyễn Nhật Ánh");
		Author author2 = new Author("Diệp Lạc Vô Tâm");
		Author author3 = new Author("Nguyễn Nhật Ánh");
		Author author4 = new Author("Trần Lộ");
		Author author5 = new Author("Michelle Buchanan");

		Category category1 = new Category("Tiểu thuyết");
		Category category2 = new Category("Tâm lý");
		Category category3 = new Category("Truyện ngắn");
		Category category4 = new Category("Kỹ năng sống");
		Category category5 = new Category("Tiểu sử");

		Publisher publisher1 = new Publisher("Nhà xuất bản Trẻ");
		Publisher publisher2 = new Publisher("Nhà Xuất Bản Tổng hợp TP.HCM");

		authorDao.insert(author1);
		categoryDao.insert(category1);
		publisherDao.insert(publisher1);

		Product product1 = new Product("Ngồi khóc trên cây", 149, author1, category1, publisher1);
		productDao.insert(product1);
	
		ProductImage productImages1 = new ProductImage("resources/images/products/1-1.png",product1);
		ProductImage productImages2 = new ProductImage("resources/images/products/1-2.png",product1);
		ProductImage productImages3 = new ProductImage("resources/images/products/1-3.png",product1);
		ProductImage productImages4 = new ProductImage("resources/images/products/1-4.png",product1);
		
		productImagesDao.insert(productImages1);
		productImagesDao.insert(productImages2);
		productImagesDao.insert(productImages3);
		productImagesDao.insert(productImages4);

		System.out.print("Init db successfully !");
		return "success";
	}
}
