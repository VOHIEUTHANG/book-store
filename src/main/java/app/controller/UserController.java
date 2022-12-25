package app.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import app.commons.UserInfo;
import app.commons.UserResponse;
import app.entity.Role;
import app.entity.User;
import app.service.UserService;
import app.utils.Upload;

@Transactional
@Controller
@RequestMapping("user")
public class UserController {
	private UserService userService = new UserService();

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
			return "index";
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



}
