package app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Author;
import app.entity.DeliveryAddress;
import app.entity.Order;
import app.entity.OrderItem;
import app.entity.Product;
import app.entity.Role;
import app.entity.Wishlist;
import hibernate.hibernateUtils;

public class OrderItemDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();

	public Boolean insert(OrderItem orderItem) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(orderItem);
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
			String hql = "DELETE from Order where id = :id";
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
	
	public Boolean update(Order order) {
		Session session = (Session) factory.openSession();
		Order updateOrder = (Order) session.merge(order);
		Transaction t = session.beginTransaction();
		try {
			session.update(updateOrder);
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
