package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public Optional<Employee> getEmployeeById(Integer id) {
		
		Optional<Employee> employee1 = employeeRepository.findById(id);
		Optional<Employee> employee = null;
		if (employee1!=null) {
			employee = employee1;
		} 
		else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;

	}

	public void deleteEmployeeById(Integer id) {
		
		employeeRepository.deleteById(id);
	}

	
	


}
