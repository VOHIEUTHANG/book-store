package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.entity.Product;
import app.entity.User;
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

	public List<Product> getAll() {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0";
		Query query = session.createQuery(hql);
		List<Product> products = query.list();

		return products;
	}

	public List<Product> getNewestProducts() {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 ORDER BY createAt DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(6);
		List<Product> products = query.list();

		return products;
	}

	public List<Product> getProductById(String productID) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE id = :productID";
		Query query = session.createQuery(hql);
		query.setParameter("productID", productID);
		query.setMaxResults(1);
		List<Product> products = query.list();

		return products;
	}

	public List<Product> getRelatedProducts(String productID, int authorID) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE authorId = :authorID AND id != :productID";
		Query query = session.createQuery(hql);
		query.setParameter("authorID", authorID);
		query.setParameter("productID", productID);
		query.setMaxResults(10);
		List<Product> products = query.list();

		return products;
	}

	public Boolean update(Product product) {
		Session session = (Session) factory.openSession();
		Product updateProduct = (Product) session.merge(product);
		Transaction t = session.beginTransaction();
		try {
			session.update(updateProduct);
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

	public List<Product> sortByPrice(String sortByPrice) {
		sortByPrice = sortByPrice.toUpperCase();
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 ORDER BY price " + sortByPrice;
		Query query = session.createQuery(hql);
		List<Product> products = query.list();

		return products;
	}

	public List<Product> filterByCategoryID(int categoryId) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 AND categoryId = :categoryId";
		Query query = session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		List<Product> products = query.list();
		return products;
	}

	public List<Product> filterByCategoryAndSortByPrice(int categoryId, String sortByPrice) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 AND categoryId = :categoryId ORDER BY price " + sortByPrice;
		Query query = session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		List<Product> products = query.list();
		return products;
	}

	public List<Product> filterByCategoryAndSearchText(int categoryId, String searchText) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 AND categoryId = :categoryId And name LIKE :searchText";
		Query query = session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		query.setParameter("searchText", "%" + searchText + "%");
		List<Product> products = query.list();
		return products;
	}

	public List<Product> filterBySearchTextAndSortByPrice(String searchText, String sortByPrice) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 AND name LIKE :searchText ORDER BY price " + sortByPrice;
		Query query = session.createQuery(hql);
		query.setParameter("searchText", "%" + searchText + "%");
		List<Product> products = query.list();
		return products;
	}

	public List<Product> filterBySearchText(String searchText) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 AND name LIKE :searchText";
		Query query = session.createQuery(hql);
		query.setParameter("searchText", "%" + searchText + "%");
		List<Product> products = query.list();
		return products;
	}

	public List<Product> allCase(String searchText, String sortByPrice, int categoryId) {
		Session session = factory.openSession();
		String hql = "FROM Product WHERE isDeleted = 0 AND categoryId = :categoryId And name LIKE :searchText ORDER BY price "
				+ sortByPrice;
		Query query = session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		query.setParameter("searchText", "%" + searchText + "%");
		List<Product> products = query.list();
		return products;
	}
}
