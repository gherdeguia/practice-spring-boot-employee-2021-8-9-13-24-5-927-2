package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private RetiringCompanyRepository retiringCompanyRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getById(Integer companyId) {
        return companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EmployeeNotFoundException("Company ID not found."));
    }

    public List<Employee> getCompanyEmployeesById(Integer companyId) {
        return employeeRepository.findEmployeesByCompanyId(companyId);
    }

    public List<Company> getByPageIndexAndPageSize(Integer pageIndex, int pageSize) {
        return companyRepository.findAll(PageRequest.of(pageIndex-1, pageSize)).toList();

    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company update(int companyId, Company companyToBeUpdated) {
        return companyRepository.save(updateCompanyInfo(getById(companyId),companyToBeUpdated));
    }

    private Company updateCompanyInfo(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null) {
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return company;
    }
    public void delete(int companyId) {
        companyRepository
                .delete(companyRepository.findById(companyId)
                        .orElseThrow(null));
    }
}
