package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Product;
import hibernate.hibernateUtils;

public class ProductDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public Boolean insert(Product product) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(product);
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
	
	public List<Product> getAll(){
		Session session = factory.openSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> products = query.list();
		
		return products;
	}
}
