package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.dao.UserDao;

@Controller
@Transactional
@RequestMapping("amdin")
public class userController {
	
	@Autowired
	UserDao userDaoImpl;
	

    @RequestMapping(value = "/user", method = RequestMethod.GET)
     public String  viewUser( Model model) {
    	 model.addAttribute("user", userDaoImpl.listUser());
    	 // hehe đó là flow của project
    	 return "" ; //
     }

}
