package com.thevarungupta.spring.boot.testing.app.controller;

import com.thevarungupta.spring.boot.testing.app.entity.Employee;
import com.thevarungupta.spring.boot.testing.app.service.EmployeeService;
import com.thevarungupta.spring.boot.testing.app.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long empId) {
        return employeeService.getEmployeeById(empId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long empId,
                                                   @RequestBody Employee employee) {
        return employeeService.getEmployeeById(empId)
                .map(saveEmployee -> {
                    saveEmployee.setFirstName(employee.getFirstName());
                    saveEmployee.setLastName(employee.getLastName());
                    saveEmployee.setEmail(employee.getEmail());
                    Employee updateEmployee = employeeService.updateEmployee(saveEmployee);
                    return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId) {
        employeeService.deleteEmployee(empId);
        return new ResponseEntity<>("employee delete successfully", HttpStatus.OK);
    }
}
