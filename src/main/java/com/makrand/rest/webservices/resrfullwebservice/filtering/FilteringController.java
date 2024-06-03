package com.makrand.rest.webservices.resrfullwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean filtering()
	{
		SomeBean someBean = new SomeBean("Value1","Value2","Value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		return someBean;
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList()
	{
		return  Arrays.asList(new SomeBean("Value1","Value2","Value3"),
				new SomeBean("Value4","Value5","Value6"));
	}
}
