package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Category;
import hibernate.hibernateUtils;

public class CategoryDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public Boolean insert(Category category) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(category);
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
	
	public List<Category> getAll(){
		Session session = factory.openSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> categorys = query.list();
		
		return categorys;
	}
	
	public List<Category> getFamousestCategory(){
		Session session = factory.openSession();
		String hql = "FROM Category";		
		Query query = session.createQuery(hql);
		query.setMaxResults(8);
		List<Category> categorys = query.list();
		
		return categorys;
	}
}
