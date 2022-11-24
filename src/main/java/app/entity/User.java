package app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(unique = true)
	String userName;
	String passWord;
	String fullName;
	String email;
	String phoneNumber;
	String avatar;
	Boolean isActive ;
	Date  createAt;
	String address;
	@ManyToOne
	@JoinColumn(nullable=false) 
	Role role;
	

}
