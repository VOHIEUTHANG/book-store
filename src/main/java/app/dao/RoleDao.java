package app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import app.entity.Role;
import hibernate.hibernateUtils;

public class RoleDao {
	SessionFactory factory = hibernateUtils.getSessionFactory();
	
	public List<Role> getUserRole(){
		Session session = factory.openSession();
		String hql = "FROM Role r WHERE r.id = 1";
		Query query = session.createQuery(hql);
		List<Role> role = query.list();
		
		return role;
	}
}
