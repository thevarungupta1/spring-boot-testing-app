package com.thevarungupta.spring.boot.testing.app.repository;

import com.thevarungupta.spring.boot.testing.app.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Mark")
                .lastName("Watson")
                .email("m@gmail.com")
                .build();
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void givenEmployeeObject_whenSave_thenReturnSaveEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Mark")
                .lastName("Watson")
                .email("m@gmail.com")
                .build();

        // when - action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(saveEmployee).isNotNull();
        assertThat(saveEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Mark")
                .lastName("Watson")
                .email("m@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Smith")
                .email("j@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // when
        List<Employee> employeeList = employeeRepository.findAll();

        // then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test got get employee by id opeartion")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){

        // given
//        Employee employee = Employee.builder()
//                .firstName("Mark")
//                .lastName("Watson")
//                .email("m@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then
        assertThat(employeeDB).isNotNull();
    }

    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        // given
        employeeRepository.save(employee);

        // whn
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        System.out.println(employeeDB);
        // then
        assertThat(employeeDB).isNotNull();
    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given
        employeeRepository.save(employee);

        // when
        Employee saveEmployee = employeeRepository.findById(employee.getId()).get();
        saveEmployee.setEmail("abc@gmail.com");
        saveEmployee.setFirstName("abc");
        Employee updateEmployee = employeeRepository.save(saveEmployee);

        // then
        assertThat(updateEmployee.getEmail()).isEqualTo("abc@gmail.com");
        assertThat(updateEmployee.getFirstName()).isEqualTo("abc");
    }

    @Test
    public void givenEmployeeObject_whenDeleteEmployee_thenRemoveEmployee(){
        // given
        employeeRepository.save(employee);

        // when
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then
        assertThat(employeeOptional).isEmpty();
    }

    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        // given
        employeeRepository.save(employee);
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();

//        String firstName = "abc";
//        String lastName = "xyz";

        // when
        Employee saveEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then
        assertThat(saveEmployee).isNotNull();
    }


}