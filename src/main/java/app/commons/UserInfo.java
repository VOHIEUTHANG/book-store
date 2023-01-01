package app.commons;

import java.util.List;

import app.entity.Cart;

public class UserInfo {
	private String username;
	private String name;
	private String avatar;
	private String role;
	private Boolean isLogin = false;

	public UserInfo() {
	}

	public UserInfo(String username, String name, String avatar, String role, Boolean isLogin) {
		super();
		this.username = username;
		this.name = name;
		this.avatar = avatar;
		this.role = role;
		this.isLogin = isLogin;
	}	

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", name=" + name + ", avatar=" + avatar + ", role=" + role
				+ ", isLogin=" + isLogin + "]";
	}
	
	

}
