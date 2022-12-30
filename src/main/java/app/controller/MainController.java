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

//		Author author1 = new Author("Nguyễn Nhật Ánh", "resources/images/authors/author1.jpg");
//		Author author2 = new Author("Diệp Lạc Vô Tâm", "resources/images/authors/author2.jpg");
//		Author author4 = new Author("Jack Canfield, D.D Watkins", "resources/images/authors/author4.jpg");
//		Author author5 = new Author("Sasha K. Shillcutt, MD", "resources/images/authors/author5.jpg");
//		Author author6 = new Author("Trần Lộ","resources/images/authors/author6.jpg");
//		Author author7 = new Author("John E. Douglas & Mark Olshaker", "resources/images/authors/author7.jpg");
//		Author author8 = new Author("Jennifer L. Scott", "resources/images/authors/author8.jpg");
//		Author author9 = new Author("Michelle Buchanan", "resources/images/authors/author9.jpg");
//		Author author10 = new Author("Yuval Noah Harari", "resources/images/authors/author10.jpg");
//		Author author11 = new Author("Antoine de Saint-Exupéry", "resources/images/authors/author11.jpg");
//		Author author12 = new Author("José Mauro de Vasconcelos", "resources/images/authors/author12.jpg");
//		Author author13 = new Author("Nguyễn Hiến Lê", "resources/images/authors/author13.jpg");
//		Author author14 = new Author("Paul Kalanithi", "resources/images/authors/author14.jpg");
//		Author author15 = new Author("Nhiều tác giả", "resources/images/authors/author15.jpg");
//		Author author16 = new Author("Ở Đây Zui Nè", "resources/images/authors/author16.jpg");
//		Author author17 = new Author("An", "resources/images/authors/author17.jpg");
//		Author author18 = new Author("Jim Collins","resources/images/authors/author18.jpg");
//		Author author19 = new Author("Văn Chính", "resources/images/authors/author19.jpg");
//		Author author20 = new Author("Higashino Keigo", "resources/images/authors/author20.jpg");
//
//		Category category1 = new Category("Tiểu thuyết");
//		Category category2 = new Category("Tâm lý");
//		Category category3 = new Category("Truyện ngắn");
//		Category category4 = new Category("Kỹ năng sống");
//		Category category5 = new Category("Kỹ năng");
//		Category category6 = new Category("Tư duy, kỹ năng sống");
//		Category category7 = new Category("Tiểu thuyết trinh thám");
//		Category category8 = new Category("Phát triển bản thân");
//		Category category9 = new Category("Tâm linh");
//		Category category10 = new Category("Hư cấu");
//		Category category11 = new Category("Hồi Ký");
//		Category category12 = new Category("Tản văn");
//		Category category13 = new Category("Bài học kinh doanh");
//
//		Publisher publisher1 = new Publisher("Nhà xuất bản Trẻ");
//		Publisher publisher2 = new Publisher("Nhà Xuất Bản Tổng hợp TP.HCM");
//		Publisher publisher3 = new Publisher("Nhà Xuất Văn Học");
//		Publisher publisher4 = new Publisher("Nhà Xuất Bản Lao Động Xã Hội");
//		Publisher publisher5 = new Publisher("Nhà Xuất Bản Thế Giới");
//		Publisher publisher6 = new Publisher("Nhà xuất bản Thanh Niên");
//		Publisher publisher7 = new Publisher("Nhà xuất bản Phụ nữ Việt Nam");
//		Publisher publisher9 = new Publisher("Nhà Xuất Bản Dân Trí");
//		Publisher publisher10 = new Publisher("Nhà xuất bản Tri Thức");
//		Publisher publisher11 = new Publisher("NXB Dân Trí");
//		Publisher publisher12 = new Publisher("NXB Hội Nhà Văn");
//		Publisher publisher13 = new Publisher("NXB Hồng Đức");
//		Publisher publisher14 = new Publisher("NXB Lao Động");
//		Publisher publisher15 = new Publisher("NXB Phụ Nữ Việt Nam");
//		Publisher publisher16 = new Publisher("NXB Thanh Niên");
//
//		authorDao.insert(author1);
//		authorDao.insert(author2);
//		authorDao.insert(author4);
//		authorDao.insert(author5);
//		authorDao.insert(author6);
//		authorDao.insert(author7);
//		authorDao.insert(author8);
//		authorDao.insert(author9);
//		authorDao.insert(author10);
//		authorDao.insert(author11);
//		authorDao.insert(author12);
//		authorDao.insert(author13);
//		authorDao.insert(author14);
//		authorDao.insert(author15);
//		authorDao.insert(author16);
//		authorDao.insert(author17);
//		authorDao.insert(author18);
//		authorDao.insert(author19);
//		authorDao.insert(author20);
//
//		categoryDao.insert(category1);
//		categoryDao.insert(category2);
//		categoryDao.insert(category3);
//		categoryDao.insert(category4);
//		categoryDao.insert(category5);
//		categoryDao.insert(category6);
//		categoryDao.insert(category7);
//		categoryDao.insert(category8);
//		categoryDao.insert(category9);
//		categoryDao.insert(category10);
//		categoryDao.insert(category11);
//		categoryDao.insert(category12);
//		categoryDao.insert(category13);
//
//		publisherDao.insert(publisher1);
//		publisherDao.insert(publisher2);
//		publisherDao.insert(publisher3);
//		publisherDao.insert(publisher4);
//		publisherDao.insert(publisher5);
//		publisherDao.insert(publisher6);
//		publisherDao.insert(publisher7);
//		publisherDao.insert(publisher9);
//		publisherDao.insert(publisher10);
//		publisherDao.insert(publisher11);
//		publisherDao.insert(publisher12);
//		publisherDao.insert(publisher13);
//		publisherDao.insert(publisher14);
//		publisherDao.insert(publisher15);
//		publisherDao.insert(publisher16);
//
//		Product product1 = new Product("Ngồi khóc trên cây", 149, author1, category1, publisher1, 2016);
//		Product product2 = new Product("Tử tế đáng giá bao nhiêu?", 99, author6, category4, publisher2, 2012);
//		Product product3 = new Product("Em vốn thích cô độc, cho đến khi có anh", 108, author2, category1, publisher3,
//				2018);
//		Product product4 = new Product("Người nam châm", 65, author4, category4, publisher4, 2007);
//		Product product5 = new Product("Dữ dội và dịu êm", 109, author5, category5, publisher5, 2021);
//		Product product6 = new Product("Tâm lý học - Nghệ thuật giải mã hành vi", 105, author6, category6, publisher6,
//				2021);
//		Product product7 = new Product("Mindhunter: Kẻ săn suy nghĩ", 125, author7, category7, publisher7, 2022);
//		Product product8 = new Product("20 bí mật sành điệu từ Madame Chic", 99, author8, category8, publisher5, 2017);
//		Product product9 = new Product("Thần số học - Làm chủ cuộc đời", 139, author9, category9, publisher9, 2022);
//		Product product10 = new Product("Sapiens: Lược sử loài người", 299, author10, category10, publisher10, 2011);
//		Product product11 = new Product("Hoàng tử bé", 69, author11, category1, publisher11, 2019);
//		Product product12 = new Product("Cây Cam Ngọt Của Tôi", 72, author12, category1, publisher12, 2020);
//		Product product13 = new Product("Lời Khuyên Thanh Niên", 92, author13, category2, publisher11, 2018);
//		Product product14 = new Product("Khi Hơi Thở Hóa Thinh Không", 92, author14, category3, publisher13, 2020);
//		Product product15 = new Product("Chuyện Kể Rằng Có Nàng Và Tôi", 57, author15, category4, publisher6, 2022);
//		Product product16 = new Product("Vui Vẻ Không Quạu Nha", 55, author16, category4, publisher6, 2021);
//		Product product17 = new Product("Hẹn Nhau Phía Sau Tan Vỡ", 68, author17, category4, publisher6, 2020);
//		Product product18 = new Product("Từ Tốt Đến Vĩ Đại", 110, author18, category5, publisher1, 2021);
//		Product product19 = new Product("Bán Hàng Shopee Thực Chiến Từ A-Z - 36 Chiến Lược Đỉnh Cao Bùng Nổ Doanh Số",
//				211, author19, category5, publisher7, 2022);
//		Product product20 = new Product("Bí Mật Của Naoko", 86, author20, category1, publisher12, 2018);
//
//		productDao.insert(product1);
//		productDao.insert(product2);
//		productDao.insert(product3);
//		productDao.insert(product4);
//		productDao.insert(product5);
//		productDao.insert(product6);
//		productDao.insert(product7);
//		productDao.insert(product8);
//		productDao.insert(product9);
//		productDao.insert(product10);
//		productDao.insert(product11);
//		productDao.insert(product12);
//		productDao.insert(product13);
//		productDao.insert(product14);
//		productDao.insert(product15);
//		productDao.insert(product16);
//		productDao.insert(product17);
//		productDao.insert(product18);
//		productDao.insert(product19);
//		productDao.insert(product20);
//
//		ProductImage productImages1 = new ProductImage("resources/images/products/1-1.png", product1);
//		ProductImage productImages2 = new ProductImage("resources/images/products/1-2.png", product1);
//		ProductImage productImages3 = new ProductImage("resources/images/products/1-3.png", product1);
//		ProductImage productImages4 = new ProductImage("resources/images/products/1-4.png", product1);
//		ProductImage productImages5 = new ProductImage("resources/images/products/2-1.png", product2);
//		ProductImage productImages6 = new ProductImage("resources/images/products/2-2.png", product2);
//		ProductImage productImages7 = new ProductImage("resources/images/products/2-3.png", product2);
//		ProductImage productImages8 = new ProductImage("resources/images/products/2-4.png", product2);
//		ProductImage productImages9 = new ProductImage("resources/images/products/3-1.png", product3);
//		ProductImage productImages10 = new ProductImage("resources/images/products/3-2.png", product3);
//		ProductImage productImages11 = new ProductImage("resources/images/products/3-3.png", product3);
//		ProductImage productImages12 = new ProductImage("resources/images/products/3-4.png", product3);
//		ProductImage productImages13 = new ProductImage("resources/images/products/4-1.png", product4);
//		ProductImage productImages14 = new ProductImage("resources/images/products/4-2.png", product4);
//		ProductImage productImages15 = new ProductImage("resources/images/products/4-3.png", product4);
//		ProductImage productImages16 = new ProductImage("resources/images/products/4-4.png", product4);
//		ProductImage productImages17 = new ProductImage("resources/images/products/5-1.png", product5);
//		ProductImage productImages18 = new ProductImage("resources/images/products/5-2.png", product5);
//		ProductImage productImages19 = new ProductImage("resources/images/products/5-3.png", product5);
//		ProductImage productImages20 = new ProductImage("resources/images/products/5-4.png", product5);
//		ProductImage productImages21 = new ProductImage("resources/images/products/6-1.png", product6);
//		ProductImage productImages22 = new ProductImage("resources/images/products/6-2.png", product6);
//		ProductImage productImages23 = new ProductImage("resources/images/products/6-3.png", product6);
//		ProductImage productImages24 = new ProductImage("resources/images/products/6-4.png", product6);
//		ProductImage productImages25 = new ProductImage("resources/images/products/7-1.png", product7);
//		ProductImage productImages26 = new ProductImage("resources/images/products/7-2.png", product7);
//		ProductImage productImages27 = new ProductImage("resources/images/products/7-3.png", product7);
//		ProductImage productImages28 = new ProductImage("resources/images/products/7-4.png", product7);
//		ProductImage productImages29 = new ProductImage("resources/images/products/8-1.png", product8);
//		ProductImage productImages30 = new ProductImage("resources/images/products/8-2.png", product8);
//		ProductImage productImages31 = new ProductImage("resources/images/products/8-3.png", product8);
//		ProductImage productImages32 = new ProductImage("resources/images/products/8-4.png", product8);
//		ProductImage productImages33 = new ProductImage("resources/images/products/9-1.png", product9);
//		ProductImage productImages34 = new ProductImage("resources/images/products/9-2.png", product9);
//		ProductImage productImages35 = new ProductImage("resources/images/products/9-3.png", product9);
//		ProductImage productImages36 = new ProductImage("resources/images/products/9-4.png", product9);
//		ProductImage productImages37 = new ProductImage("resources/images/products/10-1.png", product10);
//		ProductImage productImages38 = new ProductImage("resources/images/products/10-2.png", product10);
//		ProductImage productImages39 = new ProductImage("resources/images/products/10-3.png", product10);
//		ProductImage productImages40 = new ProductImage("resources/images/products/10-4.png", product10);
//		ProductImage productImages41 = new ProductImage("resources/images/products/11-1.png", product11);
//		ProductImage productImages42 = new ProductImage("resources/images/products/11-2.png", product11);
//		ProductImage productImages43 = new ProductImage("resources/images/products/11-3.png", product11);
//		ProductImage productImages44 = new ProductImage("resources/images/products/11-4.png", product11);
//		ProductImage productImages45 = new ProductImage("resources/images/products/12-1.png", product12);
//		ProductImage productImages46 = new ProductImage("resources/images/products/12-2.png", product12);
//		ProductImage productImages47 = new ProductImage("resources/images/products/12-3.png", product12);
//		ProductImage productImages48 = new ProductImage("resources/images/products/12-4.png", product12);
//		ProductImage productImages49 = new ProductImage("resources/images/products/13-1.png", product13);
//		ProductImage productImages50 = new ProductImage("resources/images/products/13-2.png", product13);
//		ProductImage productImages51 = new ProductImage("resources/images/products/13-3.png", product13);
//		ProductImage productImages52 = new ProductImage("resources/images/products/13-4.png", product13);
//		ProductImage productImages53 = new ProductImage("resources/images/products/14-1.png", product14);
//		ProductImage productImages54 = new ProductImage("resources/images/products/14-2.png", product14);
//		ProductImage productImages55 = new ProductImage("resources/images/products/14-3.png", product14);
//		ProductImage productImages56 = new ProductImage("resources/images/products/14-4.png", product14);
//		ProductImage productImages57 = new ProductImage("resources/images/products/15-1.png", product15);
//		ProductImage productImages58 = new ProductImage("resources/images/products/15-2.png", product15);
//		ProductImage productImages59 = new ProductImage("resources/images/products/15-3.png", product15);
//		ProductImage productImages60 = new ProductImage("resources/images/products/15-4.png", product15);
//		ProductImage productImages61 = new ProductImage("resources/images/products/16-1.png", product16);
//		ProductImage productImages62 = new ProductImage("resources/images/products/16-2.png", product16);
//		ProductImage productImages63 = new ProductImage("resources/images/products/16-3.png", product16);
//		ProductImage productImages64 = new ProductImage("resources/images/products/16-4.png", product16);
//		ProductImage productImages65 = new ProductImage("resources/images/products/17-1.png", product17);
//		ProductImage productImages66 = new ProductImage("resources/images/products/17-2.png", product17);
//		ProductImage productImages67 = new ProductImage("resources/images/products/17-3.png", product17);
//		ProductImage productImages68 = new ProductImage("resources/images/products/17-4.png", product17);
//		ProductImage productImages69 = new ProductImage("resources/images/products/18-1.png", product18);
//		ProductImage productImages70 = new ProductImage("resources/images/products/18-2.png", product18);
//		ProductImage productImages71 = new ProductImage("resources/images/products/18-3.png", product18);
//		ProductImage productImages72 = new ProductImage("resources/images/products/18-4.png", product18);
//		ProductImage productImages73 = new ProductImage("resources/images/products/19-1.png", product19);
//		ProductImage productImages74 = new ProductImage("resources/images/products/19-2.png", product19);
//		ProductImage productImages75 = new ProductImage("resources/images/products/19-3.png", product19);
//		ProductImage productImages76 = new ProductImage("resources/images/products/19-4.png", product19);
//		ProductImage productImages77 = new ProductImage("resources/images/products/20-1.png", product20);
//		ProductImage productImages78 = new ProductImage("resources/images/products/20-2.png", product20);
//		ProductImage productImages79 = new ProductImage("resources/images/products/20-3.png", product20);
//		ProductImage productImages80 = new ProductImage("resources/images/products/20-4.png", product20);
//
//		productImagesDao.insert(productImages1);
//		productImagesDao.insert(productImages2);
//		productImagesDao.insert(productImages3);
//		productImagesDao.insert(productImages4);
//		productImagesDao.insert(productImages5);
//		productImagesDao.insert(productImages6);
//		productImagesDao.insert(productImages7);
//		productImagesDao.insert(productImages8);
//		productImagesDao.insert(productImages9);
//		productImagesDao.insert(productImages10);
//		productImagesDao.insert(productImages11);
//		productImagesDao.insert(productImages12);
//		productImagesDao.insert(productImages13);
//		productImagesDao.insert(productImages14);
//		productImagesDao.insert(productImages15);
//		productImagesDao.insert(productImages16);
//		productImagesDao.insert(productImages17);
//		productImagesDao.insert(productImages18);
//		productImagesDao.insert(productImages19);
//		productImagesDao.insert(productImages20);
//		productImagesDao.insert(productImages21);
//		productImagesDao.insert(productImages22);
//		productImagesDao.insert(productImages23);
//		productImagesDao.insert(productImages24);
//		productImagesDao.insert(productImages25);
//		productImagesDao.insert(productImages26);
//		productImagesDao.insert(productImages27);
//		productImagesDao.insert(productImages28);
//		productImagesDao.insert(productImages29);
//		productImagesDao.insert(productImages30);
//		productImagesDao.insert(productImages31);
//		productImagesDao.insert(productImages32);
//		productImagesDao.insert(productImages33);
//		productImagesDao.insert(productImages34);
//		productImagesDao.insert(productImages35);
//		productImagesDao.insert(productImages36);
//		productImagesDao.insert(productImages37);
//		productImagesDao.insert(productImages38);
//		productImagesDao.insert(productImages39);
//		productImagesDao.insert(productImages40);
//		productImagesDao.insert(productImages41);
//		productImagesDao.insert(productImages42);
//		productImagesDao.insert(productImages43);
//		productImagesDao.insert(productImages44);
//		productImagesDao.insert(productImages45);
//		productImagesDao.insert(productImages46);
//		productImagesDao.insert(productImages47);
//		productImagesDao.insert(productImages48);
//		productImagesDao.insert(productImages49);
//		productImagesDao.insert(productImages50);
//		productImagesDao.insert(productImages51);
//		productImagesDao.insert(productImages52);
//		productImagesDao.insert(productImages53);
//		productImagesDao.insert(productImages54);
//		productImagesDao.insert(productImages55);
//		productImagesDao.insert(productImages56);
//		productImagesDao.insert(productImages57);
//		productImagesDao.insert(productImages58);
//		productImagesDao.insert(productImages59);
//		productImagesDao.insert(productImages60);
//		productImagesDao.insert(productImages61);
//		productImagesDao.insert(productImages62);
//		productImagesDao.insert(productImages63);
//		productImagesDao.insert(productImages64);
//		productImagesDao.insert(productImages65);
//		productImagesDao.insert(productImages66);
//		productImagesDao.insert(productImages67);
//		productImagesDao.insert(productImages68);
//		productImagesDao.insert(productImages69);
//		productImagesDao.insert(productImages70);
//		productImagesDao.insert(productImages71);
//		productImagesDao.insert(productImages72);
//		productImagesDao.insert(productImages73);
//		productImagesDao.insert(productImages74);
//		productImagesDao.insert(productImages75);
//		productImagesDao.insert(productImages76);
//		productImagesDao.insert(productImages77);
//		productImagesDao.insert(productImages78);
//		productImagesDao.insert(productImages79);
//		productImagesDao.insert(productImages80);
//
		System.out.print("Init db successfully !");
		return "success";
	}
}
