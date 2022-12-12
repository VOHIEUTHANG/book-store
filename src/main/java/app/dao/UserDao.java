package app.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.sql.ordering.antlr.Factory;
import org.springframework.beans.factory.annotation.Autowired;

import app.entity.User;
import hibernate.hibernateUtils;

public class UserDao {
	
	SessionFactory factory = hibernateUtils.getSessionFactory();
	
	public List<User> getAllUser(){
		Session session = factory.openSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> users = query.list();
		
		return users;
	}
	
	public List<User> getUserByUsername(String username) {
		Session session = factory.openSession();
		String hql = "FROM User WHERE username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		List<User> users = query.list();
		
		return users;
	}
	
	public Boolean insert(User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			t.commit();
			return true;
		} catch (Exception e) {
			System.out.print(e);
			t.rollback();
			return false;
		}
		finally {
			session.close();
		}
	}
	

}
