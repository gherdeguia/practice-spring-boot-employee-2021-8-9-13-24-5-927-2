package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        final Employee employee = new Employee("Spongebob", 14, "male", 99);
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Spongebob"))
                .andExpect(jsonPath("$[0].age").value("14"))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value("99"));
    }

    @Test
    public void should_create_employee_when_call_create_employee_api_without_company_id() throws Exception {
        // given
        String employeeJson = "{\n" +
                "    \"name\": \"Spongebob\",\n" +
                "    \"age\": 24,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 999,\n" +
                "    \"companyId\": null\n" +
                "}";

        // when
        // then
        mockMvc.perform(post("/employees")
                .contentType(APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Spongebob"))
                .andExpect(jsonPath("$.age").value("24"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("999"))
                .andExpect(jsonPath("$.companyId").isEmpty());
    }

    @Test
    public void should_create_employee_when_call_create_employee_api_with_company_id() throws Exception {
        // given
        Company company = companiesDataFactory().get(0);
        Integer companyId = companyRepository.save(company).getId();

        String employeeJson = "{\n" +
                "    \"name\": \"Spongebob\",\n" +
                "    \"age\": 24,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 999,\n" +
                "    \"companyId\": " + companyId + "\n" +
                "}";

        // when
        // then
        mockMvc.perform(post("/employees")
                .contentType(APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Spongebob"))
                .andExpect(jsonPath("$.age").value("24"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("999"))
                .andExpect(jsonPath("$.companyId").value("1"));
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

        companies.add(new Company(1, "Alibaba", employees1));
        companies.add(new Company(2, "Shoppee", employees2));

        return companies;
    }

    private List<Employee> employeesDataFactory() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Francis", 24, "male", 99, 1));
        employees.add(new Employee(2, "Eric", 22, "male", 99, 1));
        employees.add(new Employee(3, "Spongebob", 24, "male", 99, 1));
        employees.add(new Employee(4, "Patrick", 22, "male", 99, 1));
        employees.add(new Employee(5, "Gary", 24, "male", 99, 1));
        employees.add(new Employee(6, "Squidward", 22, "male", 99, 1));

        return employees;
    }
}
