package com.java.webservice.learn.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> listAll(){
		System.out.println("List All");
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		System.out.println("id: " + id);
		Optional<User> user = userRepository.findById(id);
		
		//Exception for User Not Found
		if(!user.isPresent()) {
			throw new userNotFoundException("id-" + id);
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listAll());
		resource.add(linkTo.withRel("get-all-users"));
		
		return resource;
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteById(@PathVariable int id) {
		
		userRepository.deleteById(id);
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getPost(@PathVariable int id) {
		System.out.println("id: " + id);
		Optional<User> user = userRepository.findById(id);
		
		//Exception for User Not Found
		if(!user.isPresent()) {
			throw new userNotFoundException("id-" + id);
		}
		
		List<Post> posts = user.get().getPost();
		
		return posts;
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> addUser(@PathVariable int id, @RequestBody Post post) {
		System.out.println("id: " + id);
		Optional<User> userOptional = userRepository.findById(id);
		
		//Exception for User Not Found
		if(!userOptional.isPresent()) {
			throw new userNotFoundException("id-" + id);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(post.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
