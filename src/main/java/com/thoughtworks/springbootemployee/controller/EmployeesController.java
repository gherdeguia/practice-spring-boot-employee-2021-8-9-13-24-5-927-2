package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/employees")
public
class EmployeesController {

    @Autowired
    private  EmployeeService employeeService;

    @Autowired
    private  EmployeeMapper employeeMapper;



    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.getById(id));
    }

    @RequestMapping(params = "gender")
    public List<Employee> getEmployeesByGender(@RequestParam String gender) {
        return employeeService.getByGender(gender);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employeeService.getByPageIndexAndPageSize(pageIndex, pageSize);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.create(employeeMapper.toEntity(employeeRequest));
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.update(id, employeeMapper.toEntity(employeeRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
    }
}
