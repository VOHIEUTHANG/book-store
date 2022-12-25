package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.ProductImage;
import hibernate.hibernateUtils;

public class ProductImagesDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public Boolean insert(ProductImage productImages) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(productImages);
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
	
	public List<ProductImage> getAll(){
		Session session = factory.openSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<ProductImage> productImages = query.list();
		
		return productImages;
	}
}
