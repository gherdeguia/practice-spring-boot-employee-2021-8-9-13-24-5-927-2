package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer age;
    public String gender;
    public Integer salary;

    @JoinColumn(insertable = false, updatable = false)
    public Integer companyId;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer age, String gender, Integer salary, Integer companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
        this(id, name, age, gender, salary, null);
    }

    public Employee(String name, Integer age, String gender, Integer salary, Integer companyId) {
        this(null, name, age, gender, salary, companyId);
    }

    public Employee(String name, Integer age, String gender, Integer salary) {
        this(name, age, gender, salary, null);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
