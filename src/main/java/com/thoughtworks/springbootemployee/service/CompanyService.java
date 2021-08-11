package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getCompanies();
    }

    public Company getById(Integer companyId) {
        return companyRepository.findById(companyId);
    }

    public List<Employee> getEmployeesById(Integer companyId) {
        return companyRepository.findEmployeesById(companyId);
    }

    public List<Company> getByPageIndexAndPageSize(Integer pageIndex, int pageSize) {
        return companyRepository.findByPageIndexAndPageSize(pageIndex, pageSize);

    }

    public Company create(Company company) {
        return companyRepository.addCompany(company);
    }
}
