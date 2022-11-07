package com.thevarungupta.spring.boot.testing.app.service.impl;

import com.thevarungupta.spring.boot.testing.app.entity.Employee;
import com.thevarungupta.spring.boot.testing.app.exception.ResourceNotFoundException;
import com.thevarungupta.spring.boot.testing.app.repository.EmployeeRepository;
import com.thevarungupta.spring.boot.testing.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    @Autowired
//    private EmployeeRepository employeeRepository;
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository repository){
        this.employeeRepository = repository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw  new ResourceNotFoundException("employee already exists with given email: "+ employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee updateEmployee) {
        return employeeRepository.save(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
