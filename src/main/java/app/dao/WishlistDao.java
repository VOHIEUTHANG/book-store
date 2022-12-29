package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Author;
import app.entity.Product;
import app.entity.Role;
import app.entity.Wishlist;
import hibernate.hibernateUtils;

public class WishlistDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public List<Wishlist> getByUsername(String username) {
		Session session = factory.openSession();
		String hql = "FROM Wishlist WHERE UserID = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		List<Wishlist> wishlist = query.list();
		return wishlist;
	}

	public Boolean insert(Wishlist wishlist) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(wishlist);
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
			String hql = "DELETE from Wishlist where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);	
			System.out.println(query.executeUpdate());			
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
	
	public Boolean checkExistedWishlist(String username, String productID) {
		Session session = factory.openSession();
		String hql = "FROM Wishlist WHERE UserID = :username AND ProductID = :productID";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("productID", productID);
		List<Wishlist> wishlist = query.list();
		if(wishlist != null && wishlist.size() > 0) {
			return true;
		}
		return false;
	}
}
