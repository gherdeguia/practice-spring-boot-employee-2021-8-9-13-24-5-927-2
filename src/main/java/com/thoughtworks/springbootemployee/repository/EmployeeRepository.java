package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.controller.EmployeesController;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Francis", 24, "male", 99));
        employees.add(new Employee(2, "Eric", 22, "male", 99));
        employees.add(new Employee(3, "Spongebob", 24, "male", 99));
        employees.add(new Employee(4, "Patrick", 22, "male", 99));
        employees.add(new Employee(5, "Gary", 24, "male", 99));
        employees.add(new Employee(6, "Squidward", 22, "male", 99));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee findById(Integer id) {
        return getEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findByGender(String gender) {
        return getEmployees()
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }
}
