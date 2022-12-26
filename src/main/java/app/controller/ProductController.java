package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.dao.ProductDao;
import app.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {
	ProductDao productDao = new ProductDao();
	ProductService productService = new ProductService();

	@RequestMapping(value = "delete/{productID}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("productID") String productID) {
		Boolean deleteResult = productService.deleteById(productID);
		System.out.print(deleteResult);
		
		return "redirect:/admin/products.htm";
	}

}
