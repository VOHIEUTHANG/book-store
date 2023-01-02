package app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.commons.Response;
import app.dao.AddressDao;
import app.dao.CartDao;
import app.dao.OrderDao;
import app.dao.OrderItemDao;
import app.dao.ProductDao;
import app.dao.UserDao;
import app.entity.Cart;
import app.entity.DeliveryAddress;
import app.entity.Order;
import app.entity.OrderItem;
import app.entity.Product;
import app.entity.User;


@Controller
@Transactional
@RequestMapping("order")
public class OrderController {
	AddressDao addressDao = new AddressDao();
	CartDao cartDao = new CartDao();
	UserDao userDao = new UserDao();
	ProductDao productDao = new ProductDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	OrderDao orderDao = new OrderDao();
	
	@RequestMapping(value = "insert", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
	@ResponseBody
	public String insertDeliveryAddress(HttpSession session, ModelMap model, @RequestParam int addressID)
			throws Exception {
		Response response = new Response();
		User currentUser = (User) session.getAttribute("userEntity");		
		
		if(currentUser == null) {
			response.setStatus(false);
			response.setMessage("Vui lòng đăng nhập để thực hiện chức năng này!");
		}else {
			List<Cart> carts = cartDao.getByUsername(currentUser.getUsername());
			int totalPrice = 0;
			
			for (int i = 0; i < carts.size(); i++) {
			    Cart cart = carts.get(i);
			    int currentPrice = Math.round(cart.getProduct_cart().getPrice() * (100 - cart.getProduct_cart().getDiscountPercent())/100) * cart.getQuantity();
			    totalPrice += currentPrice;
			}
			
			DeliveryAddress address = addressDao.getById(addressID);
			String deliveryAddress = address.getAddressDetail() + " - " + address.getWard() + " - " + address.getDistrict() + " - " + address.getProvince(); 
			
			Order order = new Order(totalPrice + 30, deliveryAddress, currentUser);
			
			Boolean addOrderStatus = true;
			
			Boolean insertOrderResult = orderDao.insert(order);		
			
			if(!insertOrderResult) {
				addOrderStatus = false;
			}
					
			if(insertOrderResult) {				
				for (int i = 0; i < carts.size(); i++) {
				    Cart cart = carts.get(i);
				    Product product = cart.getProduct_cart();				   
				    int originPrice = product.getPrice();
				    int discountPercent = product.getDiscountPercent();
				    int actualPrice = Math.round(originPrice * (100 - discountPercent)/100) * cart.getQuantity();
				    
				    OrderItem orderItem = new OrderItem(originPrice, discountPercent, cart.getQuantity(),actualPrice,product, order);
				    Boolean insertOrderItemResult = orderItemDao.insert(orderItem);	
				    
					if(!insertOrderItemResult) {
						addOrderStatus = false;
					}
				}
				
			}
						
			if(addOrderStatus) {
				response.setStatus(true);
				response.setMessage("Đặt đơn hàng thành công !");
				
//				remove cart
				for (int i = 0; i < carts.size(); i++) {
				    Cart cart = carts.get(i);
				    cartDao.deleteByID(cart.getId());				     
				}
				
				List<Cart> cartList = cartDao.getByUsername(currentUser.getUsername());
				session.setAttribute("carts", cartList);				    
				
			}else {
				response.setStatus(false);
				response.setMessage("Thêm đơn hàng xảy ra lỗi, vui lòng kiểm tra lại !");
			}
			
		}
				
		Gson gson = new GsonBuilder().create();
		String jsonResponse = gson.toJson(response);
		return jsonResponse;
	}
	


	
	
}
