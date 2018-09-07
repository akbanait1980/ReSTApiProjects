package com.java.webservice.learn.restfulwebservices.user;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String comments;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Integer getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", comments=" + comments + "]";
	}
	
	
}
