package app.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entity.Staff;


@Controller
public class mainController {
	
	@Autowired
	SessionFactory factory;
	
	@Transactional
	@RequestMapping("index")
	public String index() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff";
		Query query = session.createQuery(hql);
		List<Staff> staffs = query.list();
		
		System.out.print(staffs.size());
		
		return "index";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("register")
	public String register() {
		return "register";
	}
}
