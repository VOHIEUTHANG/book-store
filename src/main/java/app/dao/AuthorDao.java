package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Author;
import app.entity.Role;
import app.entity.User;
import hibernate.hibernateUtils;

public class AuthorDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public Boolean insert(Author author) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(author);
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
	
	public List<Author> getAll(){
		Session session = factory.openSession();
		String hql = "FROM Author";
		Query query = session.createQuery(hql);
		List<Author> authors = query.list();
		
		return authors;
	}
}
