package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFoundException extends RuntimeException{
    private String message;

    public EmployeeNotFoundException(String message){
//        this.message = message;
        super(message);
    }
}
