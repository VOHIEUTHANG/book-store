package app.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;

import app.dao.ProductDao;
import app.dao.RoleDao;
import app.dao.UserDao;
import app.entity.Product;
import app.entity.Role;
import app.entity.User;

@Controller
public class ProductService {
	private static ProductDao ProductDao = new ProductDao();
	
	public boolean deleteById(String id){
		List<Product> products = ProductDao.getProductById(id);
		
		if(products.size()>0) {
			Product targetProduct = products.get(0);
			targetProduct.setIsDeleted(true);
			
			Boolean updateResult = ProductDao.update(targetProduct);
			
			return updateResult;
			
		}
		
		return false;
	}
	

}
