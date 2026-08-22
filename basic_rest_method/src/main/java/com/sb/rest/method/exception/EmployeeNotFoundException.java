package com.sb.rest.method.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
