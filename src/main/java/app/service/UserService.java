package app.service;

import java.util.List;

import app.dao.UserDao;
import app.entity.User;
import app.response.UserResponse;
import app.utils.PasswordHandler;

public class UserService {
	private static UserDao userdao = new UserDao();

	public List<User> getAllUser() {
		return userdao.getAllUser();
	}

	public UserResponse loginUser(String username, String password) throws Exception {
		UserResponse res = new UserResponse();
		PasswordHandler passHandler = new PasswordHandler();
		
		String hashPassword = passHandler.getHashPassword(password);
		System.out.println(hashPassword);

		List<User> users = userdao.getUserByUsername(username);
		if (users.size() > 0) {
			User user = users.get(0);
			String currentHashPassword = user.getPassword();
			System.out.println(currentHashPassword);
			Boolean isTruePassword = passHandler.checkPassword(password, currentHashPassword);

			if (isTruePassword) {
				res.setUser(user);
				res.setStatus(true);
				res.setMessage("Đăng nhập thành công !");
			}else {
				res.setStatus(false);
				res.setMessage("Mật khẩu không đúng, vui lòng kiểm tra lại !");
			}

		} else {
			res.setStatus(false);
			res.setMessage("Không tìm thấy người dùng !");
		}
		return res;
	}
	
	public Boolean registerUser(User user) throws Exception {
		PasswordHandler passHandler = new PasswordHandler();
		String hashPassword = passHandler.getHashPassword(user.getPassword());
		user.setPassword(hashPassword);
		
		Boolean insertStatus = userdao.insert(user);
		
		return insertStatus;
	}
	
	

}
