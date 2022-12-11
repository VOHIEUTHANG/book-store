package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Staff {
	@Id
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column
	private String avatar;
	@Column(columnDefinition = "bit default 1")
	private Boolean isActive;
	
	public Staff(String username, String password, String name, String avatar, Boolean isActive) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.isActive = isActive;
	}
	public Staff() {};
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Staff [username=" + username + ", password=" + password + ", name=" + name + ", avatar=" + avatar
				+ ", isActive=" + isActive + "]";
	}
	
}
