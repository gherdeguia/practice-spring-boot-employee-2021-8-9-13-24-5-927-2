package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee create(Employee employee) {
        return (employeeRepository.addEmployee(employee));
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> getByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findByPageIndexAndPageSize(pageIndex, pageSize);
    }

    public Employee update(Integer id, Employee employeeToBeUpdated) {
        return employeeRepository.updateEmployee(id, employeeToBeUpdated);
    }


    public boolean delete(Integer id) {
        return employeeRepository.deleteEmployee(id);
    }

}
