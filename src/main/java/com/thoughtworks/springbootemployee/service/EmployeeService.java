package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final RetiringEmployeeRepository retiringEmployeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, RetiringEmployeeRepository retiringEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return (employeeRepository.save(employee));
    }

    public Employee getById(Integer id) {
        return retiringEmployeeRepository.findById(id);
    }

    public List<Employee> getByGender(String gender) {
        return retiringEmployeeRepository.findByGender(gender);
    }

    public List<Employee> getByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
        return retiringEmployeeRepository.findByPageIndexAndPageSize(pageIndex, pageSize);
    }

    public Employee update(Integer id, Employee employeeToBeUpdated) {
        return retiringEmployeeRepository.updateEmployee(id, employeeToBeUpdated);
    }

    public boolean delete(Integer id) {
        return retiringEmployeeRepository.deleteEmployee(id);
    }

}
