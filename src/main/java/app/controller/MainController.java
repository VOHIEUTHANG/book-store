package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public String index(ModelMap model) {
		List<Product> products = new ArrayList<>();
		List<Product> newestProduct = new ArrayList<>();
		List<Author> authors = new ArrayList<>();
		
		products = productDao.getAll();
		newestProduct = productDao.getNewestProducts();
		authors = authorDao.getAll();
		
		model.addAttribute("products", products);
		model.addAttribute("newestProduct", newestProduct);
		model.addAttribute("authors", authors);
		
		return "index";
	}
	

	@RequestMapping("login")
	public String getFormLogin() {
		return "login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("userEntity");
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
		Author author6 = new Author("Bernadette Russell");

		Category category1 = new Category("Tiểu thuyết");
		Category category2 = new Category("Tâm lý");
		Category category3 = new Category("Truyện ngắn");
		Category category4 = new Category("Kỹ năng sống");
		Category category5 = new Category("Tiểu sử");

		Publisher publisher1 = new Publisher("Nhà xuất bản Trẻ");
		Publisher publisher2 = new Publisher("Nhà Xuất Bản Tổng hợp TP.HCM");
		Publisher publisher3 = new Publisher("Nhà Xuất Văn Học");

//		authorDao.insert(author1);
//		categoryDao.insert(category1);
//		publisherDao.insert(publisher1);
//
//		Product product1 = new Product("Ngồi khóc trên cây", 149, author1, category1, publisher1);
//		productDao.insert(product1);
		
//		ProductImage productImages1 = new ProductImage("resources/images/products/1-1.png",product1);
//		ProductImage productImages2 = new ProductImage("resources/images/products/1-2.png",product1);
//		ProductImage productImages3 = new ProductImage("resources/images/products/1-3.png",product1);
//		ProductImage productImages4 = new ProductImage("resources/images/products/1-4.png",product1);
//		
//		productImagesDao.insert(productImages1);
//		productImagesDao.insert(productImages2);
//		productImagesDao.insert(productImages3);
//		productImagesDao.insert(productImages4);
		
//		authorDao.insert(author6);
//		categoryDao.insert(category4);
//		publisherDao.insert(publisher2);
//		
//		Product product2 = new Product("Tử tế đáng giá bao nhiêu?", 99, author6, category4, publisher2);
//		productDao.insert(product2);
//		
//		ProductImage productImages5 = new ProductImage("resources/images/products/2-1.png",product2);
//		ProductImage productImages6 = new ProductImage("resources/images/products/2-2.png",product2);
//		ProductImage productImages7 = new ProductImage("resources/images/products/2-3.png",product2);
//		ProductImage productImages8 = new ProductImage("resources/images/products/2-4.png",product2);
//		
//		productImagesDao.insert(productImages5);
//		productImagesDao.insert(productImages6);
//		productImagesDao.insert(productImages7);
//		productImagesDao.insert(productImages8);
		
		authorDao.insert(author2);
		categoryDao.insert(category1);
		publisherDao.insert(publisher3);
		
		Product product3 = new Product("Em vốn thích cô độc, cho đến khi có anh", 108, author2, category1, publisher3, 2018);
		productDao.insert(product3);
		
		ProductImage productImages9 = new ProductImage("resources/images/products/3-1.png",product3);
		ProductImage productImages10 = new ProductImage("resources/images/products/3-2.png",product3);
		ProductImage productImages11 = new ProductImage("resources/images/products/3-3.png",product3);
		ProductImage productImages12 = new ProductImage("resources/images/products/3-4.png",product3);
		
		productImagesDao.insert(productImages9);
		productImagesDao.insert(productImages10);
		productImagesDao.insert(productImages11);
		productImagesDao.insert(productImages12);
		
		System.out.print("Init db successfully !");
		return "success";
	}
}
