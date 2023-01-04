package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Publisher;
import hibernate.hibernateUtils;

public class PublisherDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public Boolean insert(Publisher publisher) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(publisher);
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
	
	public List<Publisher> getAll(){
		Session session = factory.openSession();
		String hql = "FROM Publisher";
		Query query = session.createQuery(hql);
		List<Publisher> publishers = query.list();
		
		return publishers;
	}
}
