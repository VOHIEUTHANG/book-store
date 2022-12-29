package app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Wishlist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User user_wishlist;
	
	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product_wishlist;
	
	public Wishlist() {}
	
	public Wishlist(User user_wishlist, Product product_wishlist) {
		this.user_wishlist = user_wishlist;
		this.product_wishlist = product_wishlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser_wishlist() {
		return user_wishlist;
	}

	public void setUser_wishlist(User user_wishlist) {
		this.user_wishlist = user_wishlist;
	}

	public Product getProduct_wishlist() {
		return product_wishlist;
	}

	public void setProduct_wishlist(Product product_wishlist) {
		this.product_wishlist = product_wishlist;
	}
	
}
