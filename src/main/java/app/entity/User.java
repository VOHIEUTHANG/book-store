package app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String email;
	@Column
	private String phoneNumber;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM//dd/yyyy")
	private Date dateOfBirth;
	@Column
	private String address;
	@Column
	private String avatar;
	@Column
	private Boolean gender; // 1 is man, 0 is woman
	@Column(columnDefinition = "bit default 1")
	private Boolean isActive;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@ManyToOne
	@JoinColumn(name = "roleID")
	private Role role;

	@OneToMany(mappedBy = "user_delivery", fetch = FetchType.EAGER)
	private List<DeliveryAddress> deliverAddress = new ArrayList<>();

	@OneToMany(mappedBy = "user_comment", fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "user_cart", fetch = FetchType.EAGER)
	private List<Cart> carts = new ArrayList<>();

	@OneToMany(mappedBy = "user_order", fetch = FetchType.EAGER)
	private List<Order> orders = new ArrayList<>();
	
	@OneToMany(mappedBy = "user_wishlist", fetch = FetchType.EAGER)
	private List<Wishlist> wishlist = new ArrayList<>();
	
	public User(String username, String password, String fullName, String email, String phoneNumber, 
			Date createAt) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createAt = createAt;
	}
	
	public User() {}

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getGender() {
		return gender;
	}

	public List<DeliveryAddress> getDeliverAddress() {
		return deliverAddress;
	}

	public void setDeliverAddress(List<DeliveryAddress> deliverAddress) {
		this.deliverAddress = deliverAddress;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public List<Wishlist> getWishlist() {
		return wishlist;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", fullName=" + fullName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", avatar="
				+ avatar + ", gender=" + gender + ", isActive=" + isActive + ", createAt=" + createAt + ", role=" + role
				+ ", deliverAddress=" + deliverAddress + ", comments=" + comments + ", carts=" + carts + ", orders="
				+ orders + ", wishlist=" + wishlist + "]";
	}

	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
