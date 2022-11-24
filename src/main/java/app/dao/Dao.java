package app.dao;

import java.util.List;

import javax.persistence.Query;

public interface Dao<T> {
	
	boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
    // à viế thêm cái hàm get list ở đây :)) quên
   // public String action(T t, int crud);


    /**
     * Get result as list from a HQL
     *
     * @param hql    hibernate query
     * @param type   type of object needs to return
     * @param params parameters
     * @return list
     */
    
    
    public <T> List<T> getFromQuery(String hql, Class<T> type, Object... params);

    public T getOne(Class<T> type, Object id);
 
    
  
}
