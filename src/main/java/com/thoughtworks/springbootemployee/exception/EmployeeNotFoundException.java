package com.thoughtworks.springbootemployee.exception;

import static java.lang.String.format;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message){
        super(format("Employee ID %s not found.", message));
    }
}
