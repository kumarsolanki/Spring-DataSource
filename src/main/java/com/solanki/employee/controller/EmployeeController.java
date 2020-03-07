package com.solanki.employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.solanki.employee.beans.Employee;
import com.solanki.employee.beans.User;
import com.solanki.employee.service.EmployeeServiceImpl;
import com.solanki.user.service.UserServiceImpl;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;
	
	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/employee/add")
	public void saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		User user = new User();
		user.setUserId(101);
		user.setUsername("ashish");
		userServiceImpl.saveUser(user);

	}

	@GetMapping("/employee/getEmployee/{eid}")
	public Employee getEmployeeById(@PathVariable int eid) {
		Optional<Employee> optionalEmployee = employeeService.find(eid);
		if (optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		}
		return null;
	}

	@GetMapping("/employee")
	public String getHealth() {
		return "Application is up";
	}
}
