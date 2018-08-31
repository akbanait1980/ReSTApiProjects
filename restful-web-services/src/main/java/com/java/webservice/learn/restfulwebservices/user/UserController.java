package com.java.webservice.learn.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Resource<User> getUser(@PathVariable int id) {
		System.out.println("id: " + id);
		User user = service.findOne(id);
		
		//Exception for User Not Found
		if(user == null) {
			throw new userNotFoundException("id-" + id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listAll());
		resource.add(linkTo.withRel("get-all-users"));
		
		return resource;
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = service.saveUser(user);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable int id) {
		
		User user = service.deleteById(id);
		
		if (user == null) {
			throw new userNotFoundException("id-"+ id);
		}
		
	}
}
