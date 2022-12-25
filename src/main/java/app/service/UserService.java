package app.service;

import java.util.ArrayList;
import java.util.List;

import app.commons.UserResponse;
import app.dao.RoleDao;
import app.dao.UserDao;
import app.entity.Role;
import app.entity.User;
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

		List<User> users = userdao.getUserByUsername(username);
		if (users.size() > 0) {
			User user = users.get(0);
			String currentHashPassword = user.getPassword();
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
	
	public UserResponse registerUser(User user) throws Exception {
		Role role = this.getUserRole();
		user.setRole(role); //set role user
		
		user.setAvatar("resources/images/default-avatar.png");
		
		List<User> users = userdao.getUserByUsername(user.getUsername());
		UserResponse res = new UserResponse();
		
		if(users.size() <= 0) {
			PasswordHandler passHandler = new PasswordHandler();
			String hashPassword = passHandler.getHashPassword(user.getPassword());
			user.setPassword(hashPassword);
			
			String username = userdao.insert(user);
			
			System.out.println("username: " + username);
			
			if(username.length() > 0) {	
				List<User> targetUser = userdao.getUserByUsername(username);
				res.setUser(targetUser.get(0));
				res.setMessage("Đăng ký thành công !");
				res.setStatus(true);
			}else {
				res.setMessage("Đăng ký thất bại !");
				res.setStatus(false);
			}
		}else {
			res.setMessage("Tên đăng nhập đã được sử dụng !");
			res.setStatus(false);
		}
		
		return res;
	}
	
	
	public Boolean updateUser(User user) throws Exception {
		Boolean updateStatus = userdao.update(user);
		return updateStatus;
	}
	
	public Role getUserRole(){
		RoleDao roleDao = new RoleDao();
		List<Role> roles  = roleDao.getUserRole();
		if(roles.size() > 0) {
			return roles.get(0);
		}
		return null;
	}
	

}
