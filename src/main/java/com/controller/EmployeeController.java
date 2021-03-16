package com.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Employee;
import com.service.EmployeeService;

@Controller
public class EmployeeController {
	
	Logger logger= LoggerFactory.getLogger(EmployeeController.class);

	
	@Autowired
    EmployeeService employeeService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees",employeeService.getAllEmployees());
		return "index";		
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {

		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Integer id, Model model) {
		
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") Integer id) {
		
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	@RequestMapping("/checkMethod")
	public String checkMethod(@ModelAttribute("employee") Employee employee) {
    employeeService.saveEmployee(employee);
    System.out.println(employee);
		return "redirect:/";
	}

}
