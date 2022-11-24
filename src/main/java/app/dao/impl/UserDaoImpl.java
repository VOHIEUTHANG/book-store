package app.dao.impl;

import java.util.List;

import org.hibernate.Query;

import app.dao.AbstractDao;
import app.dao.UserDao;
import app.entity.User;

public class UserDaoImpl  extends  AbstractDao<User> implements UserDao{
	 
	
	// muốn over tụi này thì chuột phải chọn SOURCE ->  over/imp
	// muốn lm gì thì lm ở đây
	
	@Override
	public boolean save(User t) {
		// TODO Auto-generated method stub
		return super.save(t);
	}

	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return super.delete(t);
	}

	@Override
	public <T> List<T> getFromQuery(String hql, Class<T> type, Object... params) {
		// TODO Auto-generated method stub
		
		return super.getFromQuery(hql, type, params);
	}

	@Override
	public User getOne(Class<User> type, Object id) {
		// TODO Auto-generated method stub
		return super.getOne(type, id);
	}

	@Override
	public void setParameters(Query query, Object... params) {
		// TODO Auto-generated method stub
		super.setParameters(query, params);
	}

	// viết hàm ở đây nè
	@Override
	public boolean updatePass(String newPass, String userName) {
		// TODO Auto-generated method stub
		return false;
	}
    
	//  
	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		 return getFromQuery("FROM User", User.class);
	}
	
 // mấy cái kia c tử xử 
}
