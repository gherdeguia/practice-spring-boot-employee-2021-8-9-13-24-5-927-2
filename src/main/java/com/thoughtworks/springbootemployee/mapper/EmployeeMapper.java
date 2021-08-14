package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return employee;
    }

    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }


    public List<EmployeeResponse> toResponse(List<Employee> employees) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        EmployeeResponse employeeResponse = new EmployeeResponse();

        employees.forEach(employee -> {
            BeanUtils.copyProperties(employee, employeeResponse);
            employeeResponses.add(employeeResponse);
        });
        return employeeResponses;
    }
}
