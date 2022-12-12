package app.response;

import app.entity.User;

public class UserResponse {
	private User user;
	private Boolean status;
	private String message;
	
	public UserResponse() {}
	

	public UserResponse(User user, Boolean status, String message) {
		this.user = user;
		this.status = status;
		this.message = message;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "UserResponse [user=" + user + ", status=" + status + ", message=" + message + "]";
	}
	
	
	

}
