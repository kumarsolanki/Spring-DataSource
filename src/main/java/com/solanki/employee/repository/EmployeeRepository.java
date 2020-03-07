package com.solanki.employee.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solanki.employee.beans.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
