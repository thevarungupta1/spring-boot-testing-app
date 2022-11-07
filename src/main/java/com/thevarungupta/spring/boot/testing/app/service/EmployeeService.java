package com.thevarungupta.spring.boot.testing.app.service;

import com.thevarungupta.spring.boot.testing.app.entity.Employee;
import com.thevarungupta.spring.boot.testing.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Employee updateEmployee);
    void deleteEmployee(Long id);


}
