package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyDoesNotExistException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployeesService() {
        return employeeRepository.findAll();
    }

    public Employee findByEmployeeIDService(Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found."));
    }

    public List<Employee> getEmployeeByGenderService(String gender) {
        return employeeRepository.findEmployeeByGender(gender);
    }

    public Employee addNewEmployeeService(Employee employee) {
        Integer companyId = employee.getCompanyId();
        if(nonNull(companyId)){
            companyRepository
                    .findById(companyId)
                    .orElseThrow(() -> new CompanyDoesNotExistException("Company ID not found."));
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByPageService(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex-1, pageSize)).getContent();
    }

    public Employee updateEmployeeService(Integer id, Employee employeeToBeUpdated) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeToBeUpdated.setId(id);
                    return employeeRepository.save(employeeToBeUpdated);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id.toString()));
    }

    public Employee deleteEmployeeByIdService(Integer id) {
        Employee deletedEmployee = new Employee(
                findByEmployeeIDService(id).getId(),
                findByEmployeeIDService(id).getName(),
                0,
                findByEmployeeIDService(id).getGender(),
                0,
                findByEmployeeIDService(id).getCompanyId()
        );

        //todo deleteByID
        employeeRepository.deleteById(id);
//        employeeRepository.delete(
//                employeeRepository
//                        .findById(id)
//                        .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found."))
//                );
        return deletedEmployee;
    }

}
