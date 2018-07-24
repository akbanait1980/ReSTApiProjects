package com.java.webservice.learn.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserService {	
	
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1, "one", new Date()));
		users.add(new User(2, "two", new Date()));
		users.add(new User(3, "three", new Date()));
	}

	
	public List<User> listAll(){
		return users;
	}
	
	public User saveUser(User user) {
		if(user.getId()==null) {
			user.setId(users.size()+1);
		}
		System.out.println(user);
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}

	
	
}