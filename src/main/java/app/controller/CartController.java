package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
import app.entity.Product;
import app.entity.User;

@Controller
@Transactional
@RequestMapping("cart")
public class CartController {
	OrderDao orderDao = new OrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	ProductDao productDao = new ProductDao();
	CartDao cartDao = new CartDao();
	UserDao userDao = new UserDao();
	AddressDao addressDao = new AddressDao();

	@RequestMapping(value = "insert", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
	@ResponseBody
	public String insertCartItem(HttpSession session, ModelMap model, @RequestParam int quantity,
			@RequestParam String productID) throws Exception {
		Response response = new Response();
		User currentUser = (User) session.getAttribute("userEntity");

		if (currentUser == null) {
			response.setStatus(false);
			response.setMessage("Vui lòng đăng nhập để thực hiện chức năng này !");
		} else {
			List<Product> products = productDao.getProductById(productID);
			if (products.size() > 0) {
				Product product = products.get(0);
				List<Cart> carts = cartDao.getByUsernameAndProductID(currentUser.getUsername(), product.getId());
				if (carts.size() > 0) {
//					update cart item
					Cart cart = carts.get(0);
					int currentQuantity = cart.getQuantity();
					currentQuantity += quantity;

					if (currentQuantity > product.getInventory()) {
						response.setStatus(false);
						response.setMessage("Sản phẩm đặt đã quá số lượng hàng trong kho !");
					} else {
						cart.setQuantity(currentQuantity);
						Boolean updateResult = cartDao.update(cart);

						if (updateResult) {
//							update product inventory
							product.setInventory(product.getInventory() - quantity);
							Boolean udpateProductResult = productDao.update(product);
//							update user carts
							List<Cart> cartlist = cartDao.getByUsername(currentUser.getUsername());		
							session.setAttribute("carts", cartlist);

							response.setStatus(true);
							response.setMessage("Thêm sản phầm vào giỏ hàng thành công !");
						} else {
							response.setStatus(false);
							response.setMessage("Cập nhật số lượng sản phẩm vào giỏ hàng xảy ra lỗi!");
						}

					}

				} else {
					if (quantity > product.getInventory()) {
						response.setStatus(false);
						response.setMessage("Sản phẩm đặt đã quá số lượng hàng trong kho !");
					} else {
						Cart cart = new Cart();
						cart.setQuantity(quantity);
						cart.setUser_cart(currentUser);
						cart.setProduct_cart(product);

						Boolean insertResult = cartDao.insert(cart);

						if (insertResult) {
//							update product inventory
							product.setInventory(product.getInventory() - quantity);
							Boolean udpateProductResult = productDao.update(product);
//							update user carts
							List<Cart> cartlist = cartDao.getByUsername(currentUser.getUsername());		
							session.setAttribute("carts", cartlist);
							response.setStatus(true);
							response.setMessage("Thêm sản phẩm vào giỏ hàng thành công !");
						} else {
							response.setStatus(false);
							response.setMessage("Thêm sản phẩm vào giỏ hàng xảy ra lỗi !");
						}

					}

				}

			} else {
				response.setStatus(false);
				response.setMessage("Không tìm thấy sản phẩm !");
			}

		}

		Gson gson = new GsonBuilder().create();
		String jsonResponse = gson.toJson(response);
		return jsonResponse;

	}

	@RequestMapping(value = "delete/{cartID}", method = RequestMethod.GET)
	public String deleteCart(@PathVariable("cartID") int cartID, HttpServletRequest request, HttpSession session) {
		User currentUser = (User) session.getAttribute("userEntity");
		if (currentUser == null)
			return "403";
		
		Cart cart = cartDao.findOneById(cartID);
		
		if(cart == null) {
			return "404";
		}else {		
			Product product = cart.getProduct_cart();
			int currentInventory = product.getInventory();
			currentInventory += cart.getQuantity();
			product.setInventory(currentInventory);
			
			productDao.update(product);
			
			Boolean deleteResult = cartDao.deleteByID(cartID);
			
			List<Cart> carts = cartDao.getByUsername(currentUser.getUsername());		
			session.setAttribute("carts", carts);
		
		}

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String getAllCart(HttpSession session, ModelMap modelMap) {
		User currentUser = (User) session.getAttribute("userEntity");
		if (currentUser == null)
			return "403";

		List<DeliveryAddress> deliveryAddress = addressDao.getByUsername(currentUser.getUsername());
		modelMap.addAttribute("deliveryAddress", deliveryAddress);
		List<Cart> carts = cartDao.getByUsername(currentUser.getUsername());
		modelMap.addAttribute("carts", carts);
		return "user-pages/carts";
	}

	@RequestMapping(value = "update/{cartID}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
	@ResponseBody
	public String updateCartItem(@PathVariable("cartID") int cartID, HttpSession session, ModelMap model, @RequestParam int quantity) throws Exception {
		Response response = new Response();
		User currentUser = (User) session.getAttribute("userEntity");
		

		if (currentUser == null) {
			response.setStatus(false);
			response.setMessage("Vui lòng đăng nhập để thực hiện chức năng này !");
		} else {
			Cart cart = cartDao.findOneById(cartID);
			
			if(cart == null) {
				response.setStatus(false);
				response.setMessage("Không tìm thấy sản phẩm trong giỏ hàng của bạn !");
			}else {				
				Product product = cart.getProduct_cart();				
				int currentInventory = product.getInventory();		
				int previousQuantity = cart.getQuantity();
				
				if(quantity > currentInventory + previousQuantity) {
					response.setStatus(false);
					response.setMessage("Sản phẩm đã vượt quá số lượng hàng trong kho !");
				}else {
					cart.setQuantity(quantity);
					Boolean updateResult = cartDao.update(cart);
//					Update product
					int newInventory = currentInventory + previousQuantity - quantity;
					product.setInventory(newInventory);
					productDao.update(product);
					
					if(updateResult) {
						response.setStatus(true);
						response.setMessage("Cập nhật giỏ hàng thành công !");
						List<Cart> carts = cartDao.getByUsername(currentUser.getUsername());
						System.out.println(carts.size());
						session.setAttribute("carts", carts);
					}else {
						response.setStatus(false);
						response.setMessage("Cập nhật giỏ hàng thất bại !");
					}
				}

			}
		}

		Gson gson = new GsonBuilder().create();
		String jsonResponse = gson.toJson(response);
		return jsonResponse;

	}
}
