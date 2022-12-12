package app.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.User;
import app.response.UserResponse;
import app.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	private  UserService userService = new UserService();
	
	@Transactional
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap model, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, ModelMap modelMap, HttpSession session) throws Exception {

		UserResponse res = userService.loginUser(username, password);
		
		System.out.println(res.toString());
		
//		session.setAttribute("user", new User());
		
		System.out.println(username);
		System.out.println(password);
		return "login";
	}
}
