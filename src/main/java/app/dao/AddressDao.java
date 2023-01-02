package app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Author;
import app.entity.DeliveryAddress;
import app.entity.Product;
import app.entity.Role;
import app.entity.Wishlist;
import hibernate.hibernateUtils;

public class AddressDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public List<DeliveryAddress> getByUsername(String username) {
		Session session = factory.openSession();
		String hql = "FROM DeliveryAddress WHERE username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		List<DeliveryAddress> addressList = query.list();
		return addressList;
	}
	
	public DeliveryAddress getById(int id) {
		Session session = factory.openSession();
		String hql = "FROM DeliveryAddress WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<DeliveryAddress> addressList = query.list();
		if(addressList.size() > 0) {
			return addressList.get(0);
		}else {
			return null;
		}
	}

	public Boolean insert(DeliveryAddress address) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(address);
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
			String hql = "DELETE from DeliveryAddress where id = :id";
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

}
