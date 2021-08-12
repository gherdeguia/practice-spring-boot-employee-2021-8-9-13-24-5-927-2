package com.thoughtworks.springbootemployee.exception;

import java.util.function.Supplier;

public class CompanyDoesNotExistException extends RuntimeException{
    public CompanyDoesNotExistException(){
        super("Company ID does not exist in our records.");
    }
}
