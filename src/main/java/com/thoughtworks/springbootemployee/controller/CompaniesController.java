package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private final CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable Integer companyId) {
        return companyMapper.toResponse(companyService.getById(companyId));
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
    @ResponseStatus(code = HttpStatus.CREATED)
    public Company addCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.addNewCompanyService(companyMapper.toEntity(companyRequest));
    }


    @PutMapping(path = "/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company companyToBeUpdated) {
        return companyService.update(id, companyToBeUpdated);
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployee(@PathVariable Integer companyId) {
        companyService.delete(companyId);
    }
}
