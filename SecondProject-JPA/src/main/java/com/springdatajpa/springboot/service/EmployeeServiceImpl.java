package com.springdatajpa.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springdatajpa.springboot.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		Employee e1 = new Employee();
		e1.setName("Swapnil Udapure");
		e1.setAge(23L);
		e1.setDepartment("Cloud Engineer");
		e1.setEmail("swapnil@gmail.com");
		e1.setLocation("Nagpur");
		Employee e2 = new Employee();
		e2.setName("Tanay Nikam");
		e2.setAge(23L);
		e2.setDepartment("Java Engineer");
		e2.setEmail("tanay@gmail.com");
		e2.setLocation("Nashik");
		
		list.add(e1);
		list.add(e2);
		return list;
	}	

}
