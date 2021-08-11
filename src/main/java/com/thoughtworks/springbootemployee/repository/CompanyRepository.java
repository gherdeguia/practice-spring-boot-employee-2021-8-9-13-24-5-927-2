package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        List<Employee> employees1 = new ArrayList<>();
        employees1.add(employeesDataFactory().get(0));
        employees1.add(employeesDataFactory().get(1));
        employees1.add(employeesDataFactory().get(2));


        List<Employee> employees2 = new ArrayList<>();
        employees2.add(employeesDataFactory().get(3));
        employees2.add(employeesDataFactory().get(4));
        employees2.add(employeesDataFactory().get(5));

        companies.add(new Company(1, "Alibaba", 199, employees1));
        companies.add(new Company(2, "Shoppee", 199, employees2));
        companies.add(new Company(3, "Lazada", 199, employees1));
        companies.add(new Company(4, "Zalora", 199, employees2));
        companies.add(new Company(5, "Shein", 199, employees1));
        companies.add(new Company(6, "Amazon", 199, employees2));
    }

    private List<Employee> employeesDataFactory() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Francis", 24, "male", 99));
        employees.add(new Employee(2, "Eric", 22, "male", 99));
        employees.add(new Employee(3, "Spongebob", 24, "male", 99));
        employees.add(new Employee(4, "Patrick", 22, "male", 99));
        employees.add(new Employee(5, "Gary", 24, "male", 99));
        employees.add(new Employee(6, "Squidward", 22, "male", 99));

        return employees;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public Company findById(Integer companyId) {
        return getCompanies()
                .stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findEmployeesById(Integer companyId) {
        return getCompanies()
                .stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .map(Company::getEmployees)
                .orElse(null);
    }

    public List<Company> findByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
        return getCompanies()
                .stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize).collect(Collectors.toList());
    }
}
