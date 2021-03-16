package com.Employees;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Employee;
import com.service.EmployeeRepository;
import com.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeesApplicationTests {

	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void getAllStudentsTest() {
		Mockito.when(employeeRepository.findAll())
				.thenReturn(Stream
						.of(new Employee(1, true,"kunam", "Ramanamma","ramana@gmail.com"),
								new Employee(2,true, "Alla", "Ramakrishna", "krishna@gmail.com"))
						.collect(Collectors.toList()));
		Assertions.assertEquals(2, employeeService.getAllEmployees().size());
	}
	
	@Test
	void saveEmployeeTest() {
		
		Employee employee= new Employee(1,true, "kunam", "Ramanamma","ramana@gmail.com");

		employeeService.saveEmployee(employee);
		Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
	}
	
	@Test
	void getEmployeeByIdTest() {
		
		Integer id=1;
		Optional<Employee> employee = Optional.of(new Employee(1,true, "kunam", "Ramanamma", "ramana@gmail.com"));
		Mockito.when(employeeRepository.findById(id)).thenReturn(employee);
		Assertions.assertEquals(employee, employeeService.getEmployeeById(id));
		
	}
	
	@Test
	void deleteEmployeeByIdTest() {
		Integer id=1;
        employeeService.deleteEmployeeById(id);
		Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(id);

		
	}

	

	
}
