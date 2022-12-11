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
public class ProductImage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	@Column(nullable = false)
	private String imageURL;
	
	@ManyToOne
	@JoinColumn(name = "produtId")
	private Product product_image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Product getProduct() {
		return product_image;
	}

	public Product getProduct_image() {
		return product_image;
	}

	public void setProduct_image(Product product_image) {
		this.product_image = product_image;
	}

}
