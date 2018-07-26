package com.java.webservice.learn.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
//@ComponentScan(basePackages="com.java.webservice.learn.restfulwebservices.user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User> listAll(){
		System.out.println("List All");
		return service.listAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		System.out.println("id: " + id);
		User user = service.findOne(id);
		
		//Exception for User Not Found
		if(user == null) {
			throw new userNotFoundException("id-" + id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User savedUser = service.saveUser(user);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
