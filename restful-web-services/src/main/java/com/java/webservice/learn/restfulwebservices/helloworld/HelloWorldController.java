package com.java.webservice.learn.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
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
	
	@GetMapping("/hello-world-internationalization")
	//public String helloWorldInternationalization(@RequestHeader(name="Accept-Language", required=false) Locale locale){
	public String helloWorldInternationalization(){
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}
