package com.sb.rest.method.advice;

import com.sb.rest.method.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiErrorInfo> handleNotFoundException(EmployeeNotFoundException exception){
        ApiErrorInfo errorInfo = ApiErrorInfo.builder().message(exception.getMessage()).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
