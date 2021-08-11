package com.thoughtworks.springbootemployee.controller;

import model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeesController() {
        employees.add(new Employee(1, "Francis", 24, "male", 99));
        employees.add(new Employee(2, "Eric", 22, "male", 99));
        employees.add(new Employee(3, "Jewel", 22, "female", 99));
        employees.add(new Employee(4, "Francis", 24, "male", 99));
        employees.add(new Employee(5, "Eric", 22, "male", 99));
        employees.add(new Employee(6, "Jewel", 22, "female", 99));
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employees;
    }

    @GetMapping(path = "/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @RequestMapping(params = "gender")
    public List<Employee> getGender(@RequestParam String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employees.stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize).collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee){
        Employee employeesToBeAdded = new Employee(employees.size() + 1,
                employee.getName(), employee.getAge(), employee.getGender(),
                employee.getSalary());

        employees.add(employeesToBeAdded);
    }
}
