package com.java.webservice.learn.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name Should Have Atleast 2 Chars")
	private String name;
	
	@Past(message="BirthDate Should Be Past Date")
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> post;

	protected User() {
	}	
	
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
}
