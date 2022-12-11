package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user_cart;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product_cart;
	
	@Column
	private int inventory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser_cart() {
		return user_cart;
	}

	public void setUser_cart(User user_cart) {
		this.user_cart = user_cart;
	}

	public Product getProduct_cart() {
		return product_cart;
	}

	public void setProduct_cart(Product product_cart) {
		this.product_cart = product_cart;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
}
