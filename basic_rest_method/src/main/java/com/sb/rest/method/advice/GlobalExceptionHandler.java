package com.sb.rest.method.advice;

import com.sb.rest.method.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiResponseInfo> handleNotFoundException(EmployeeNotFoundException exception){
        ApiErrorInfo errorInfo = ApiErrorInfo.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return buildErrorResponseEntity(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseInfo> handleInternalServerError(Exception exception){
        ApiErrorInfo errorInfo = ApiErrorInfo
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return buildErrorResponseEntity(errorInfo);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseInfo> handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> listError = exception.getBindingResult().getAllErrors().stream().map(errors->errors.getDefaultMessage()).toList();
        ApiErrorInfo errorInfo = ApiErrorInfo.builder().subError(listError).message("Input Validation Error").status(HttpStatus.BAD_REQUEST).build();
        return buildErrorResponseEntity(errorInfo);
    }

    private ResponseEntity<ApiResponseInfo> buildErrorResponseEntity(ApiErrorInfo apiErrorInfo){
        return new ResponseEntity<>(new ApiResponseInfo(apiErrorInfo),apiErrorInfo.getStatus());
    }
}
