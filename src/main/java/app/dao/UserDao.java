package app.dao;

import java.util.List;

import app.entity.User;

public interface UserDao  extends Dao<User>{
	// viết  hàm thêm ở đây nè ví dụ update pass hay cái chi đó
	
	List<User> listUser();
	boolean updatePass(String newPass, String userName);

}
