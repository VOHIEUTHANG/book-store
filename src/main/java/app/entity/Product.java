package app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Product {
	@Id
	@Column(nullable = false, updatable = false)
	private String id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int price;
	@Column(columnDefinition = "int default 0")
	private int discountPercent;
	@Column
	private int publicYear;
	@Column(nullable = false)
	private int inventory;
	@Column
	private String description;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@Column(columnDefinition = "bit default 0")
	private Boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "authorId")
	private Author author;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "publisherId")
	private Publisher publisher;

	@OneToMany(mappedBy = "product_image", fetch = FetchType.EAGER)
	private List<ProductImage> images = new ArrayList<>();

	@OneToMany(mappedBy = "product_cart", fetch = FetchType.EAGER)
	private List<Cart> carts = new ArrayList<>();

	@OneToMany(mappedBy = "product_comment", fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "product_orderItem", fetch = FetchType.EAGER)
	private List<OrderItem> orderItems = new ArrayList<>();

	@ManyToMany(mappedBy = "product_wishlist")
	Set<User> usersHaveWishList = new HashSet<>();

	public Product() {
	}

	public Product(String name, int price, Author author, Category category,
			Publisher publisher, int publicYear) {
			this.id = UUID.randomUUID().toString();
			this.name = name;
			this.price = price;
			this.createAt = new Date(System.currentTimeMillis());
			this.author = author;
			this.category = category;
			this.inventory = 100;
			this.publisher = publisher;
			this.isDeleted = false;
			this.publicYear = publicYear;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getPublicYear() {
		return publicYear;
	}

	public void setPublicYear(int publicYear) {
		this.publicYear = publicYear;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Author getAuthor() {
		return author;
	}

	public Category getCategory() {
		return category;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Set<User> getUsersHaveWishList() {
		return usersHaveWishList;
	}

	public void setUsersHaveWishList(Set<User> usersHaveWishList) {
		this.usersHaveWishList = usersHaveWishList;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
