package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        List<Employee> employees = employeesDataFactory();
        employeeRepository.saveAll(employees);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("GG"))
                .andExpect(jsonPath("$[0].age").value(24))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(99))

                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].name").value("Rio"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].gender").value("female"))
                .andExpect(jsonPath("$[1].salary").value(99))
        ;
    }

    @Test
    public void should_return_employee_when_call_get_employee_api_given_employee_id_1() throws Exception {
        //given
        List<Employee> employees = employeesDataFactory();
        employeeRepository.saveAll(employees);
        Integer employeeId = employeesDataFactory().get(1).getId();
        //when
        //then
        mockMvc.perform(get("/employees/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rio"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(99))
            ;
    }

    @Test
    public void should_return_male_employees_when_call_get_employee_api_given_employee_gender_female() throws Exception {
        //given
        List<Employee> employees = employeesDataFactory();
        employeeRepository.saveAll(employees);
        String gender = "female";
        //when
        //then
        mockMvc.perform(get("/employees?gender={gender}",gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void should_return_employees_when_call_get_employees_api_by_pagination_given_page_index_and_page_size() throws Exception {
        //given
        List<Employee> employees = employeesDataFactory();
        employeeRepository.saveAll(employees);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("page", String.valueOf(2))
                .param("pageSize", String.valueOf(3))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alfonse"))
                .andExpect(jsonPath("$[1].name").value("Edward"))
                .andExpect(jsonPath("$[2].name").value("Riza Hawkeye"))
        ;
    }

    @Test
    public void should_create_employee_when_call_create_employee_api_without_company_id() throws Exception {
        // given
        String employeeJson = "{\n" +
                "    \"name\": \"Extra Characters\",\n" +
                "    \"age\": 24,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 999\n" +
                "}";

        // when
        // then
        mockMvc.perform(post("/employees")
                .contentType(APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Extra Characters"))
                .andExpect(jsonPath("$.age").value("24"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("999"));
    }

    @Test
    public void should_create_employee_when_call_create_employee_api_with_company_id() throws Exception {
        // given
        Integer companyId = companyRepository.save(companiesDataFactory().get(0)).getId();
        String employeeJson = "{\n" +
                "    \"name\": \"Alfonse Elric\",\n" +
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
                .andExpect(jsonPath("$.name").value("Alfonse Elric"))
                .andExpect(jsonPath("$.age").value("24"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("999"))
                .andExpect(jsonPath("$.companyId").value(companyId))
        ;
    }

    @Test
    public void should_update_employee_when_call_update_employee_api_given_employee_id_and_updated_employee_request() throws Exception {
        // given
        Employee employee = employeesDataFactory().get(3);
        Integer returnedEmployeeId = employeeRepository.save(employee).getId();

        String employeeJson = "{\n" +
                "    \"name\": \"Patrick Starro\",\n" +
                "    \"age\": 22,\n" +
                "    \"gender\": \"female\",\n" +
                "    \"salary\": 99\n" +
                "}";

        // when
        // then
        mockMvc.perform(put(format("/employees/%d", returnedEmployeeId))
                .contentType(APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(returnedEmployeeId))
                .andExpect(jsonPath("$.name").value("Patrick Starro"))
                .andExpect(jsonPath("$.age").value("22"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value("99"));
    }

    @Test
    public void should_delete_employee_when_call_delete_api_given_employee_id() throws Exception {
        // given
        List<Employee> employees = employeesDataFactory();
        Integer employeeIdToBeDeleted = employeeRepository.saveAll(employees).get(0).getId();

        // when
        // then
        mockMvc.perform(delete(format("/employees/%d", employeeIdToBeDeleted)))
                .andExpect(status().isOk());
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
