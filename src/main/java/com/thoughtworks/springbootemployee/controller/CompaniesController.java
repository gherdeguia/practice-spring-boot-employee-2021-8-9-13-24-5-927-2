package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private final CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompanyById(@PathVariable Integer companyId) {
        return companyService.getById(companyId);
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId) {
        return companyService.getCompanyEmployeesById(companyId);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Company> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyService.getByPageIndexAndPageSize(pageIndex, pageSize);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addCompany(@RequestBody Company company) {
        companyService.create(company);
    }

    @PutMapping(path = "/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company companyToBeUpdated) {
        return companyService.update(id, companyToBeUpdated);
    }

    @DeleteMapping("/{companyId}")
    public boolean deleteEmployee(@PathVariable Integer companyId) {
        return companyService.delete(companyId);
    }
}
