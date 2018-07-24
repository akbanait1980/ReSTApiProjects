package com.java.webservice.learn.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class HelloWorldController {
	
	@RequestMapping("/")
	public String showMainPage() {
		System.out.println("in show page");
		return "Welcome To WebService";
	}
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello-World!!!";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello-World-Bean!!!");
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public String helloWorldPathVariable(@PathVariable String name) {
		return String.format("Hello World, %s", name);
	}

}
