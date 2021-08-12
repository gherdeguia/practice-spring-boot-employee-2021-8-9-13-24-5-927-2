package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final RetiringEmployeeRepository retiringEmployeeRepository;

    public EmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return retiringEmployeeRepository.getEmployees();
    }

    public Employee create(Employee employee) {
        return (retiringEmployeeRepository.addEmployee(employee));
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
