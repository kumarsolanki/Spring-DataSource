package com.solanki.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.solanki.employee.beans.Employee;


public interface EmployeeService {
	
	public Optional<Employee> find(int eid);
	public List<Employee> getAllEMployees();
	public void saveEmployee(Employee employee);

}
