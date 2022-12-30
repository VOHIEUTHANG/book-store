package app.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	@Column 
	private String authorName;
	@Column 
	private String authorPhoto;
	@Column 
	private String authorStory;
	@Column 
	private String authorTitle;
	@Column 
	private int birthYear;
	@Column 
	private int deadYear;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	private Collection<Product> products;
	
	public Author() {
		
	}
	
	public Author(String authorName, String authorPhoto) {
		this.authorName = authorName;
		this.authorPhoto = authorPhoto;
	}
	
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorPhoto() {
		return authorPhoto;
	}

	public void setAuthorPhoto(String authorPhoto) {
		this.authorPhoto = authorPhoto;
	}

	public String getAuthorStory() {
		return authorStory;
	}

	public void setAuthorStory(String authorStory) {
		this.authorStory = authorStory;
	}

	public String getAuthorTitle() {
		return authorTitle;
	}

	public void setAuthorTitle(String authorTitle) {
		this.authorTitle = authorTitle;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getDeadYear() {
		return deadYear;
	}

	public void setDeadYear(int deadYear) {
		this.deadYear = deadYear;
	}

	public Collection<Product> getProducts() {
		return products;
	}
	
}
