package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_get_all_employees_given_all_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Francis", 24, "male", 99));
        employees.add(new Employee(2, "Eric", 22, "male", 99));
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(employees, actualEmployees);

    }

    @Test
    public void should_return_employee_when_get_employee_given_employee_id_1() {
        //given
        Employee employee = new Employee(1, "Francis", 24, "male", 99);
        Integer employeeId = employee.getId();

        given(employeeRepository.findById(employeeId)).willReturn(employee);

        //when
        Employee actualEmployee = employeeService.getById(employeeId);

        //then
        assertEquals(employee, actualEmployee);
    }
}