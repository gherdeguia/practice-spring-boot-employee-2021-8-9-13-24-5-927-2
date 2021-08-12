package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String companyName;
    Integer employeesNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    List<Employee> employees;

    public Company(Integer id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company() {

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

//    public void setEmployeesNumber(Integer employeesNumber) {
//        this.employeesNumber = employeesNumber;
//    }

    public List<Employee> getEmployees() {
        return employees;
    }

//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
}
