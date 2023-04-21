package com.in28minutes.spring.learnspringframework.enterprise.example.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.spring.learnspringframework.enterprise.example.data.DataService;

@Component
public class BusinessService {

	@Autowired
	private DataService dataService;

	public BusinessService(DataService dataService) {
		this.dataService = dataService;
	}

	public long calculateSum() {
		List<Integer> data = dataService.getData();
		return data.stream().reduce(Integer::sum).get(); // Functional Programming
	}
}
