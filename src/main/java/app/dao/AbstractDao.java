package app.dao;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T> implements Dao<T > {
	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public boolean save(T t) {
		Transaction trans = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			trans = session.beginTransaction();
			session.saveOrUpdate(t);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean update(T t) {
		Transaction trans = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			trans = session.beginTransaction();
			session.saveOrUpdate(t);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean delete(T t) {
		Transaction trans = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			trans = session.beginTransaction();
			session.delete(t);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}


	@Override
	public <T> List<T> getFromQuery(String hql, Class<T> type, Object... params) {
//	        LOGGER.info("getFromQuery " + hql);
		List<T> list = new ArrayList<>();
		Transaction trans = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			trans = session.beginTransaction();
			Query query = (Query) session.createQuery(hql);
          // đoạn này rảnh thì  chỉnh lại
			setParameters(query, params);

			list.addAll(query.list());
			trans.commit();

			return list;
		} catch (Exception e) {
//	            LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			Throwable error = e.getCause();
			System.out.println("t" + error.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public T getOne(Class<T> type, Object id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (T) session.get(type, (Serializable) id);
		} catch (Throwable e) {
			e.printStackTrace();
//	            LOGGER.error(e.getMessage(), e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}
 
	
	
	/// có khi sai chỗ này nè =((

	public void setParameters(Query query, Object... params) {
		for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
		
	}

	
}
