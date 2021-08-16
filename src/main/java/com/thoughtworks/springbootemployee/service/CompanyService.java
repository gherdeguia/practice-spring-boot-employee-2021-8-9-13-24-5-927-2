package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyDoesNotExistException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompaniesService() {
        return companyRepository.findAll();
    }

    public Company findCompanyByIdService(Integer companyId) {
        return companyRepository
                .findById(companyId)
                .orElseThrow(() -> new CompanyDoesNotExistException("Company ID not found."));
    }

    public List<Employee> getCompanyEmployeesById(Integer companyId) {
        return employeeRepository.findEmployeesByCompanyId(companyId);
    }

    public List<Company> getCompaniesByPageandPageSize(Integer pageIndex, int pageSize) {
        return companyRepository.findAll(PageRequest.of(pageIndex-1, pageSize)).toList();

    }

    public Company addNewCompanyService(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompanyService(int companyId, Company companyToBeUpdated) {
//        return companyRepository.save(updateCompanyInfo(getById(companyId),companyToBeUpdated));
        return companyRepository.findById(companyId)
                .map(company -> {
                    companyToBeUpdated.setId(companyId);
                    return companyRepository.save(companyToBeUpdated);
                })
                .orElseThrow(() -> new CompanyDoesNotExistException(String.valueOf(companyId)));
    }

    private Company updateCompanyInfo(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null) {
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return company;
    }
    public Company deleteCompanyByIdService(int companyId) {
        Company deletedCompany = new Company(
                findCompanyByIdService(companyId).getId(),
                findCompanyByIdService(companyId).getCompanyName()
        );
        companyRepository.delete(
                companyRepository
                        .findById(companyId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Company ID not found."))
        );
        return deletedCompany;
    }
}
