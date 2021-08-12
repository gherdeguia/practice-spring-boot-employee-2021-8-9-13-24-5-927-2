package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

import com.thoughtworks.springbootemployee.exception.CompanyDoesNotExistException;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        Integer companyId = employee.getCompanyId();
        if(nonNull(companyId)){
            companyRepository.findById(companyId)
                    .orElseThrow(CompanyDoesNotExistException::new);
        }

        return employeeRepository.save(employee);
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(null);
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findEmployeeByGender(gender);
    }

    public List<Employee> getByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex-1, pageSize)).toList();
    }

    public Employee update(Integer id, Employee employeeToBeUpdated) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeToBeUpdated.setId(id);

                    return employeeRepository.save(employeeToBeUpdated);
                })
                .orElseThrow(null);
    }

    public void delete(Integer id) {
        employeeRepository.delete(employeeRepository.findById(id).orElseThrow(null));
    }

}
