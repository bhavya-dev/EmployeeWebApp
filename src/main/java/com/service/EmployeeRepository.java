package com.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
