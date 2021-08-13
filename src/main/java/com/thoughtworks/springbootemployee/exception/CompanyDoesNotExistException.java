package com.thoughtworks.springbootemployee.exception;

public class CompanyDoesNotExistException extends RuntimeException{
    private String message;
    public CompanyDoesNotExistException(String message){
        super(message);
//        super("Company ID does not exist in our records.");
    }
}
