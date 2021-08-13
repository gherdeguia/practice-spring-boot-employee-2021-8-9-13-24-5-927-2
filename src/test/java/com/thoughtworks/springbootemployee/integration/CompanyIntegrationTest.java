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

//    @AfterEach
//    void tearDown() {
//        employeeRepository.deleteAll();
//    }

    @Test
    void should_return_all_companies_when_call_get_companies_api() throws Exception {
        //given
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

    private List<Employee> employeesDataFactory(int companyID) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Francis", 24, "male", 99, companyID));
        employees.add(new Employee("Eric", 22, "male", 99, companyID));
        employees.add(new Employee("Spongebob", 24, "male", 99, companyID));
        employees.add(new Employee("Patrick", 22, "male", 99, companyID));
        employees.add(new Employee("Gary", 24, "male", 99, companyID));
        employees.add(new Employee("Squidward", 22, "male", 99, companyID));
        employees.add(new Employee( "Sandy", 22, "female", 99, companyID));

        return employees;
    }
}
