package com.java.webservice.learn.restfulwebservices.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retrievingSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
}
