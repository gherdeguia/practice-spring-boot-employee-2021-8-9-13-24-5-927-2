package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void should_return_all_companies_when_get_all_companies() {
        //given
        List<Company> companies = companiesDataFactory();
        when(companyRepository.getCompanies()).thenReturn(companies);

        //when
        List<Company> actualCompanies = companyService.getAllCompanies();

        //then
        assertEquals(companies, actualCompanies);

    }

    @Test
    public void should_return_company_when_get_company_given_company_id_1() {
        //given
        Company company = companiesDataFactory().get(0);
        Integer companyId = company.getCompanyId();

        when(companyRepository.findById(companyId)).thenReturn(company);

        //when
        Company actualCompany = companyService.getById(companyId);

        //then
        assertEquals(company, actualCompany);
    }

    @Test
    public void should_return_company_employees_when_get_company_employees_given_company_id_1() {
        //given
        Company company = companiesDataFactory().get(0);
        List<Employee> employees = company.getEmployees();
        Integer companyId = company.getCompanyId();

        when(companyRepository.findEmployeesById(companyId)).thenReturn(employees);

        //when
        List<Employee> actualEmployees = companyService.getEmployeesById(companyId);

        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    public void should_return_first_5_companies_when_get_companies_by_pagination_given_page_index_1_and_page_size_5() {
        //given
        List<Company> companies = new ArrayList<>();
        companies.add(companiesDataFactory().get(0));
        companies.add(companiesDataFactory().get(1));
        companies.add(companiesDataFactory().get(0));
        companies.add(companiesDataFactory().get(1));
        companies.add(companiesDataFactory().get(0));
        companies.add(companiesDataFactory().get(1));
        when(companyRepository.findByPageIndexAndPageSize(1, 5)).thenReturn(companies);

        //when
        List<Company> actualCompanies = companyService.getByPageIndexAndPageSize(1, 5);

        //then
        assertEquals(companies, actualCompanies);
    }

    @Test
    public void should_return_new_company_when_add_company_given_company() {
        //given
        Company company = companiesDataFactory().get(0);
        when(companyRepository.addCompany(company)).thenReturn(company);

        //when
        Company actualCompany = companyService.create(company);

        //then
        assertEquals(company, actualCompany);
    }

    private List<Company> companiesDataFactory() {
        List<Company> companies = new ArrayList<>();
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

        return companies;
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
}