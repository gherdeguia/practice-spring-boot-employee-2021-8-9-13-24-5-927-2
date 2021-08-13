package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;

import javax.persistence.*;
import java.util.List;


public class CompanyRequest {

    Integer id;
    String companyName;
    List<Employee> employees;

    public CompanyRequest(Integer id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
    }

    public CompanyRequest(String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    public CompanyRequest(String companyName) {
        this.companyName = companyName;
    }


    public CompanyRequest() {

    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

}
