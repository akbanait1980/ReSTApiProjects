package com.java.webservice.learn.restfulwebservices.filter;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retrievingSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrievingListSomeBean(){
		List<SomeBean> SBList = new ArrayList<>();
		SomeBean SB1 = new SomeBean("value11","value22","value33");
		SomeBean SB2 = new SomeBean("value111","value222","value333");
		SBList.add(SB1);
		SBList.add(SB2);
		return SBList;
	}
	
	@GetMapping("/filtering-dynamic13")
	public MappingJacksonValue retrievingDynamicSomeBean(){

		SomeBean someBean = new SomeBean("value1111","value2222","value3333");
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SimpleBeanFilter", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-dynamic23")
	public MappingJacksonValue retrievingDynamicSomeBean2(){
		
		List<SomeBean> SBList = new ArrayList<>();
		SomeBean SB1 = new SomeBean("value11","value22","value33");
		SomeBean SB2 = new SomeBean("value111","value222","value333");
		SBList.add(SB1);
		SBList.add(SB2);
		
//		SomeBean someBean = new SomeBean("value1111","value2222","value3333");
		
		MappingJacksonValue mapping = new MappingJacksonValue(SBList);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SimpleBeanFilter", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
}
