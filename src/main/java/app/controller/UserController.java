package app.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.commons.UserInfo;
import app.commons.UserResponse;
import app.entity.User;
import app.service.UserService;

@Transactional
@Controller
@RequestMapping("user")
public class UserController {
	private UserService userService = new UserService();
	private UserInfo currentUser = new UserInfo();

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap model, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, ModelMap modelMap, HttpSession session)
			throws Exception {

		UserResponse res = userService.loginUser(username, password);

		if (res.getStatus()) {
			User user = res.getUser();
			currentUser.setName(user.getFullName());
			currentUser.setUsername(user.getUsername());
			currentUser.setAvatar(user.getAvatar());
			currentUser.setIsLogin(true);
			currentUser.setRole(user.getRole().getRole());
			
			System.out.println(currentUser.toString());
			
			session.setAttribute("user",currentUser);
			session.setAttribute("userInfo",user);
			return "index";
		}

		model.addAttribute("response", res);

		return "login";
	}
	
	@RequestMapping(value="profile")
	public String getProfilePage(HttpSession session) {
		User user = (User) session.getAttribute("userInfo");
		if(user == null) {
			return "login";
		}
		return "user-pages/user-profile";
	}



}
