package com.thoughtworks.springbootemployee.integration;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_companies_when_call_get_companies_api() throws Exception {
        //given
        List<Company> companies = companiesDataFactory();
        companyRepository.saveAll(companies);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk())
                ;
    }

    @Test
    void should_add_new_company_when_call_create_company_api() throws Exception {
        //given
        String companyToAdd = "{\n" +
                "        \"companyName\": \"Krusty Krab\",\n" +
                "        \"employees\": null\n" +
                "    }";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyToAdd)
                )
                .andExpect(status().isCreated());
        ;
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

        companies.add(new Company(1, "Amestris", employees1));
        companies.add(new Company(2, "Xerxes", employees2));

        return companies;
    }

    private List<Employee> employeesDataFactory() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "GG", 24, "male", 99));
        employees.add(new Employee(2, "Rio", 22, "female", 99));
        employees.add(new Employee(3, "Winry", 24, "female", 99));
        employees.add(new Employee(4, "Alfonse", 22, "male", 99));
        employees.add(new Employee(5, "Edward", 24, "male", 99));
        employees.add(new Employee(6, "Riza Hawkeye", 22, "female", 99));
        employees.add(new Employee(7, "Roy Mustang", 22, "male", 99));

        return employees;
    }
}
