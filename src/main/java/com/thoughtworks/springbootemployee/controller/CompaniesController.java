package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;


    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompaniesService();
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable Integer companyId) {
        return companyMapper.toResponse(companyService.findCompanyByIdService(companyId));
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<EmployeeResponse> getCompanyEmployees(@PathVariable Integer companyId) {
        return employeeMapper.toResponse(companyService.getCompanyEmployeesById(companyId));
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> getEmployeesByPagination_alternate(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyMapper.toResponse(companyService.getCompaniesByPageandPageSize(page, pageSize));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Company addCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.addNewCompanyService(companyMapper.toEntity(companyRequest));
    }

    @PutMapping(path = "/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompanyService(id, companyMapper.toEntity(companyRequest));
    }

    @DeleteMapping("/{companyId}")
    public Company deleteEmployee(@PathVariable Integer companyId) {
        return companyService.deleteCompanyByIdService(companyId);
    }
}
