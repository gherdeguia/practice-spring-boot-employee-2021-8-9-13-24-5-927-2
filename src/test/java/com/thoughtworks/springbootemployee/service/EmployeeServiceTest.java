package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    public static final String FEMALE = "female";
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

    @Test
    public void should_return_all_female_employees_when_get_employee_by_gender_given_gender_is_female() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Francis", 24, "male", 99));
        employees.add(new Employee(2, "Eric", 22, "male", 99));
        employees.add(new Employee(2, "Sandy", 22, "female", 99));
        given(employeeRepository.findByGender(FEMALE)).willReturn(employees);

        //when
        List<Employee> actualEmployee = employeeService.getByGender(FEMALE);

        //then
        assertEquals(employees, actualEmployee);
    }

    @Test
    public void should_return_first_5_employees_when_get_employees_by_pagination_given_page_index_1_and_page_size_5() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Francis", 24, "male", 99));
        employees.add(new Employee(2, "Eric", 22, "male", 99));
        employees.add(new Employee(3, "Spongebob", 24, "male", 99));
        employees.add(new Employee(4, "Patrick", 22, "male", 99));
        employees.add(new Employee(5, "Gary", 24, "male", 99));
        employees.add(new Employee(6, "Squidward", 22, "male", 99));
        employees.add(new Employee(7, "Pearl", 22, "male", 99));
        employees.remove(5);
        given(employeeRepository.findByPageIndexAndPageSize(1,5)).willReturn(employees);

        //when
        List<Employee> actualEmployee = employeeService.getByPageIndexAndPageSize(1, 5);

        //then
        assertEquals(employees, actualEmployee);
    }

    @Test
    public void should_add_1_new_employee_when_add_employee_given_employee() {
        //given
        Employee employee = new Employee(1, "Francis", 24, "male", 99);
        given(employeeRepository.addEmployee(employee)).willReturn(employee);

        //when
        Employee actualEmployee = employeeService.create(employee);

        //then
        assertEquals(employee, actualEmployee);
    }
}