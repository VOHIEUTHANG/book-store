package app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Author;
import app.entity.Cart;
import app.entity.DeliveryAddress;
import app.entity.Order;
import app.entity.OrderItem;
import app.entity.Product;
import app.entity.Role;
import app.entity.Wishlist;
import hibernate.hibernateUtils;

public class CartDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();
	
	public List<Cart> getByUsernameAndProductID(String username, String productID) {
		Session session = factory.openSession();
		String hql = "FROM Cart WHERE username = :username AND productId = :productID";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("productID", productID);
		List<Cart> carts = query.list();
		return carts;
	}
	
	public List<Cart> getByUsername(String username) {
		Session session = factory.openSession();
		String hql = "FROM Cart WHERE username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		List<Cart> carts = query.list();
		return carts;
	}
	
	public Cart findOneById(int cartID) {
		Session session = factory.openSession();
		String hql = "FROM Cart WHERE id = :id";		
		Query query = session.createQuery(hql);
		query.setParameter("id", cartID);
		List<Cart> carts = query.list();
		if(carts.size() > 0) {
			return carts.get(0);
		}
		return null;
	}

	public Boolean insert(Cart cart) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cart);
			t.commit();
			return true;
		} catch (Exception e) {
			System.out.print(e);
			t.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public Boolean deleteByID(int id) {
		Session session = (Session) factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			String hql = "DELETE from Cart where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			System.out.println(query.executeUpdate());
			t.commit();
			return true;
		} catch (Exception e) {
			System.out.print(e);
			t.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	public Boolean update(Cart cart) {
		Session session = (Session) factory.openSession();
		Cart updateCart = (Cart) session.merge(cart);
		Transaction t = session.beginTransaction();
		try {
			session.update(updateCart);
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
