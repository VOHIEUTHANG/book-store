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
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	@Column
	private int originPrice;
	@Column(columnDefinition = "int default 0")
	private int discountPercent;
	@Column(nullable = false)
	private int quantity;
	@Column
	private int actualPrice;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product_orderItem;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	private Order order_orderItem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(int originPrice) {
		this.originPrice = originPrice;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(int actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Product getProduct_orderItem() {
		return product_orderItem;
	}

	public void setProduct_orderItem(Product product_orderItem) {
		this.product_orderItem = product_orderItem;
	}

	public Order getOrder_orderItem() {
		return order_orderItem;
	}

	public void setOrder_orderItem(Order order_orderItem) {
		this.order_orderItem = order_orderItem;
	}
	

}
