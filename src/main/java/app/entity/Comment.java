package app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	@Column(nullable = false)
	private String content;
	@Column
	private String imageURL;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user_comment;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product_comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user_comment;
	}

	public void setUser(User user) {
		this.user_comment = user;
	}

	public Product getProduct() {
		return product_comment;
	}

	public void setProduct(Product product) {
		this.product_comment = product;
	}

	public User getUser_comment() {
		return user_comment;
	}

	public void setUser_comment(User user_comment) {
		this.user_comment = user_comment;
	}

	public Product getProduct_comment() {
		return product_comment;
	}

	public void setProduct_comment(Product product_comment) {
		this.product_comment = product_comment;
	}
	
}
