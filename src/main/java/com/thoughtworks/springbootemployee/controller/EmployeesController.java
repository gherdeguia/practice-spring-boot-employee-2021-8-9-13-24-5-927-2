package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private  EmployeeService employeeService;

    @Autowired
    private  EmployeeMapper employeeMapper;
    

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployeesService();
    }

    @GetMapping(path = "/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.findByEmployeeIDService(id));
    }

    @RequestMapping(params = "gender")
    public List<EmployeeResponse> getEmployeesByGender_alternate(@RequestParam String gender) {
        return employeeMapper.toResponse(employeeService.getEmployeeByGenderService(gender));
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<EmployeeResponse> getEmployeesByPagination_alternate(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return employeeMapper.toResponse(employeeService.getEmployeesByPageService(page, pageSize));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.addNewEmployeeService(employeeMapper.toEntity(employeeRequest));
    }


    @PutMapping(path = "/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateEmployeeService(id, employeeMapper.toEntity(employeeRequest));
    }

    @DeleteMapping("/{id}")
    public EmployeeResponse deleteEmployee(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.deleteEmployeeByIdService(id));
    }
}
