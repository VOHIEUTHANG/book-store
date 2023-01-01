package app.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;

import app.commons.Response;
import app.commons.UserInfo;
import app.commons.UserResponse;
import app.dao.AddressDao;
import app.dao.CommentDao;
import app.dao.ProductDao;
import app.dao.UserDao;
import app.dao.WishlistDao;
import app.entity.Comment;
import app.entity.DeliveryAddress;
import app.entity.Product;
import app.entity.Role;
import app.entity.User;
import app.entity.Wishlist;
import app.service.UserService;
import app.utils.PasswordHandler;
import app.utils.Upload;

@Transactional
@Controller
@RequestMapping("user")
public class UserController {
	private UserService userService = new UserService();
	WishlistDao wishlistDao = new WishlistDao();
	ProductDao productDao = new ProductDao();
	CommentDao commentDao = new CommentDao();
	AddressDao addressDao = new AddressDao();

	@Autowired
	ServletContext context;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap model, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, ModelMap modelMap, HttpSession session)
			throws Exception {

		UserResponse res = userService.loginUser(username, password);

		if (res.getStatus()) {
			User user = res.getUser();
			UserInfo currentUser = new UserInfo();
			currentUser.setName(user.getFullName());
			currentUser.setUsername(user.getUsername());
			currentUser.setAvatar(user.getAvatar());
			currentUser.setIsLogin(true);
			currentUser.setRole(user.getRole().getRole());

			System.out.println(currentUser.toString());

			session.setAttribute("user", currentUser);
			session.setAttribute("userEntity", user);
			session.setAttribute("carts", user.getCarts());
			

			if (user.getRole().getRole().equals("USER")) {
				return "redirect:/index.htm";
			} else if (user.getRole().getRole().equals("ADMIN")) {
				return "redirect:/admin/home.htm";
			}
		} else {
			model.addAttribute("message", res.getMessage());
		}

		model.addAttribute("response", res);

		return "login";
	}

	@RequestMapping(value = "profile")
	public String getProfilePage(HttpSession session) {
		User user = (User) session.getAttribute("userEntity");
		if (user == null) {
			return "login";
		}
		return "user-pages/user-profile";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data;application/json")
	@ResponseBody
	public String registerUser(HttpServletRequest req, HttpSession session) throws Exception {
		JSONObject data = new JSONObject(req.getParameter("userInfo"));

		String fullName = data.getString("fullName");
		String email = data.getString("email");
		String phoneNumber = data.getString("phoneNumber");

		String username = data.getString("username");
		String password = data.getString("password");

		Date now = new Date(System.currentTimeMillis());
		User user = new User(username, password, fullName, email, phoneNumber, now);
		UserResponse registerResult = userService.registerUser(user);

		if (registerResult.getStatus()) {
			User loggedUser = registerResult.getUser();
			UserInfo currentUser = new UserInfo();
			currentUser.setName(loggedUser.getFullName());
			currentUser.setUsername(loggedUser.getUsername());
			currentUser.setAvatar(loggedUser.getAvatar());
			currentUser.setIsLogin(true);
			currentUser.setRole(loggedUser.getRole().getRole());

			session.setAttribute("user", currentUser);
			session.setAttribute("userEntity", loggedUser);
			session.setAttribute("carts", loggedUser.getCarts());
		}

		registerResult.setUser(null);

		Gson gson = new GsonBuilder().create();
		String jsonResponse = gson.toJson(registerResult);
		return jsonResponse;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
	@ResponseBody
	public String updateUserWithoutAvatar(@RequestParam(value = "avatar", required = false) MultipartFile imageFile,
			HttpServletRequest req, HttpSession session, ModelMap model) throws Exception {
		Upload upload = new Upload();
		JSONObject data = new JSONObject(req.getParameter("userInfo"));
		String fullName = data.getString("fullName");
		String address = data.getString("address");
		String genderString = data.getString("gender");
		String phoneNumber = data.getString("phoneNumber");
		String email = data.getString("email");
		Boolean gender = false;

		if (genderString.equals("male")) {
			gender = true;
		}

		User currentUser = (User) session.getAttribute("userEntity");
		UserInfo currentUserInfo = (UserInfo) session.getAttribute("user");

		if (currentUser != null) {
			if (imageFile != null) {
				String path = upload.writeFile(imageFile, context);
				System.out.println(path);
				currentUser.setAvatar(path);
				currentUserInfo.setAvatar(path);
			}

			currentUser.setFullName(fullName);
			currentUserInfo.setName(fullName);
			currentUser.setAddress(address);
			currentUser.setGender(gender);
			currentUser.setAddress(address);
			currentUser.setPhoneNumber(phoneNumber);

			Boolean updateStatus = userService.updateUser(currentUser);
			session.setAttribute("userEntity", currentUser);
			session.setAttribute("user", currentUserInfo);

			if (updateStatus) {
				return "OK";
			} else {
				return "FAIL";
			}
		}
		System.out.println("User is null !");
		return "FAIL";
	}

	@RequestMapping("change-password")
	public String getChangePasswordPage(HttpSession session) {
		User currentUser = (User) session.getAttribute("userEntity");
		if (currentUser == null)
			return "login";
		return "user-pages/change-password";
	}

	@RequestMapping(value = "change-password", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
	@ResponseBody
	public String changePassword(HttpServletRequest req, HttpSession session, ModelMap model) throws Exception {
		User currentUser = (User) session.getAttribute("userEntity");
		if (currentUser == null)
			return "login";
		Upload upload = new Upload();
		JSONObject data = new JSONObject(req.getParameter("passwordInfo"));
		String currentPassword = data.getString("currentPassword");
		String newPassword = data.getString("newPassword");

		System.out.println("current pass => " + currentPassword);
		System.out.println("new pass => " + newPassword);

		PasswordHandler passHandler = new PasswordHandler();

		String trueCurrentPass = currentUser.getPassword();

		Boolean checkMatchPass = passHandler.checkPassword(currentPassword, trueCurrentPass);

		Response reponse = new Response();

		if (checkMatchPass) {
			String newHashPassword = passHandler.getHashPassword(newPassword);
			currentUser.setPassword(newHashPassword);
			System.out.println(newHashPassword);
			Boolean updateResult = userService.updateUser(currentUser);
			if (updateResult) {
				reponse.setStatus(true);
				reponse.setMessage("Cập nhật mật khẩu thành công !");
//				Log out
				session.removeAttribute("user");
				session.removeAttribute("userEntity");

			} else {
				reponse.setStatus(false);
				reponse.setMessage("Cập nhận mật khẩu thất bại !");
			}
		} else {
			reponse.setStatus(false);
			reponse.setMessage("Mật khẩu hiện tại không chính xác, vui lòng kiểm tra lại!");
		}

		Gson gson = new GsonBuilder().create();
		String jsonResponse = gson.toJson(reponse);

		return jsonResponse;
	}

//	WISHLIST ===> 

	@RequestMapping(value = "add-to-wishlist/{productID}", method = RequestMethod.GET)
	public String addToWishList(@PathVariable("productID") String productID, HttpSession session,
			HttpServletRequest request) {
		List<Product> products = productDao.getProductById(productID);
		if (products.size() > 0) {
			Product product = products.get(0);
			User user = (User) session.getAttribute("userEntity");

			if (user == null) {
				return "login";
			}

			Boolean isExistedWishlist = wishlistDao.checkExistedWishlist(user.getUsername(), product.getId());
			if (!isExistedWishlist) {
				Wishlist wishlist = new Wishlist(user, product);
				Boolean insertResult = wishlistDao.insert(wishlist);
				System.out.println("INSERT INTO WISHLIST STATUS " + insertResult);
			} else {
				System.out.println("EXISTED PRODUCT");
			}

		} else {
			return "404";
		}

		return "redirect:/user/wishlist.htm";

	}

	@RequestMapping("wishlist")
	public String getUserWishlist(HttpSession session, ModelMap model) {
		User currentUser = (User) session.getAttribute("userEntity");
		if (currentUser == null) {
			return "login";
		}
		List<Wishlist> wishlist = wishlistDao.getByUsername(currentUser.getUsername());
		model.addAttribute("wishlist", wishlist);

		return "user-pages/wishlist";
	}

	@RequestMapping(value = "wishlist/delete/{wishlistID}", method = RequestMethod.GET)
	public String deleteWishlist(@PathVariable("wishlistID") int wishlistID, HttpServletRequest request) {
		Boolean deleteResult = wishlistDao.deleteByID(wishlistID);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

//	COMMENT =>

	@RequestMapping(value = "comment/insert", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
	@ResponseBody
	public String insertComment(@RequestParam(value = "image", required = false) MultipartFile imageFile,
			HttpServletRequest req, HttpSession session, ModelMap model, @RequestParam String comment,
			@RequestParam String productID) throws Exception {
		Upload upload = new Upload();
		Response response = new Response();
		User currentUser = (User) session.getAttribute("userEntity");

		List<Product> products = productDao.getProductById(productID);

		Comment commentRecord = new Comment();
		commentRecord.setContent(comment);
		commentRecord.setProduct(products.get(0));
		commentRecord.setUser(currentUser);

		if (imageFile != null) {
			String path = upload.writeFile(imageFile, context);
			commentRecord.setImageURL(path);
		}

		Boolean insertResult = commentDao.insert(commentRecord);

		if (!insertResult) {
			response.setStatus(false);
			response.setMessage("Thêm bình luận thất bại !");
		} else {
			response.setStatus(true);
			response.setMessage("Thêm bình luận thành công !");
		}

		Gson gson = new GsonBuilder().create();
		String jsonResponse = gson.toJson(response);
		return jsonResponse;

	}

	@RequestMapping(value = "comment/delete/{commentID}", method = RequestMethod.GET)
	public String deleteComment(@PathVariable("commentID") int commentID, HttpServletRequest request,HttpSession session) {		
		User currentUser = (User) session.getAttribute("userEntity");
		if(currentUser == null) return "403";
		
		Boolean deleteResult = commentDao.deleteByID(commentID);
		if(!deleteResult) return "404";
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

//	DELIVERY ADDRESS
	@Transactional
	@RequestMapping("delivery-address")
	public String getUserAddress(HttpSession session, ModelMap model) {
		User currentUser = (User) session.getAttribute("userEntity");
		if (currentUser == null) {
			return "login";
		}

		List<DeliveryAddress> deliveryAddress = addressDao.getByUsername(currentUser.getUsername());
		model.addAttribute("deliveryAddress", deliveryAddress);

		return "user-pages/delivery-address";
	}

	@RequestMapping(value = "delivery-address/insert", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
	@ResponseBody
	public String insertDeliveryAddress(HttpSession session, ModelMap model, @RequestParam String province,
			@RequestParam String district, @RequestParam String ward, @RequestParam String detailAddress)
			throws Exception {
		Response response = new Response();
		User currentUser = (User) session.getAttribute("userEntity");
		
		DeliveryAddress address = new DeliveryAddress();
		address.setAddressDetail(detailAddress);
		address.setProvince(province);
		address.setDistrict(district);
		address.setWard(ward);
		address.setUser_delivery(currentUser);
		
		Boolean insertResult = addressDao.insert(address);
		
		if(currentUser == null) {
			response.setStatus(false);
			response.setMessage("Hết phiên đăng nhập, vui lòng đăng nhập lại!");
		}else {
			if(insertResult) {
				response.setStatus(true);
				response.setMessage("Thêm địa chỉ thành công !");
			}else {
				response.setStatus(false);
				response.setMessage("Thêm địa chỉ nhận hàng thất bại!");
			}
		}
				
		Gson gson = new GsonBuilder().create();
		String jsonResponse = gson.toJson(response);
		return jsonResponse;

	}
	
	@RequestMapping(value = "delivery-address/delete/{addressID}", method = RequestMethod.GET)
	public String deleteDeliveryAddress(@PathVariable("addressID") int addressID, HttpServletRequest request, HttpSession session) {		
		User currentUser = (User) session.getAttribute("userEntity");
		if(currentUser == null) return "403";
		
		Boolean deleteResult = addressDao.deleteByID(addressID);
		if(!deleteResult) return "404";
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

}
