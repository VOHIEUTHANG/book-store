package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Comment;
import app.entity.Wishlist;
import hibernate.hibernateUtils;

public class CommentDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public List<Comment> getCommentsByProductID(String productId) {
		Session session = factory.openSession();
		String hql = "FROM Comment WHERE productId = :productId ORDER BY createAt DESC";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		List<Comment> comments = query.list();		
		return comments;
	}

	public Boolean insert(Comment comment) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(comment);
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
			String hql = "DELETE from Comment where id = :id";
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
	
	
}
