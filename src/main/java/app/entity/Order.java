package app.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	
	@Column 
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Column 
	private int totalPrice;
	
	@Column(columnDefinition = "int default 0")
	private int orderStatus;
	/*
	 * 0 : cho xac nhan
	 * 1 : da xac nhan
	 * 2 : that bai
	 * 3 : thanh cong
	 */
	
	@Column(columnDefinition = "int default 0")
	private int deliveryStatus; 
	/*
	 * 0 : cho xac nhan
	 * 1 : dang lay hang
	 * 2 : dang giao hang
	 * 3 : giao hang thanh cong
	 * 4 : giao hang that bai 
	 */
	
	@Column(columnDefinition = "int default 0")
	private int paymentStatus;
	/*
	 * 0 : thanh toan thanh cong
	 * 1 : thanh toan that bai
	 */
	@Column(columnDefinition = "int default 0")
	private int paymentMethod;
	/*
	 * 0 : COD
	 * 1 : Paypal
	 */
	@Column
	private String deliveryAddress;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private User user_order;
	
	@OneToMany(mappedBy="order_orderItem", fetch = FetchType.EAGER)
	private List<OrderItem> orderItems = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public User getUser_order() {
		return user_order;
	}

	public void setUser_order(User user_order) {
		this.user_order = user_order;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
}
