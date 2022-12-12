package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController{

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping("login")
	public String getFormLogin() {
		return "login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
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
}
