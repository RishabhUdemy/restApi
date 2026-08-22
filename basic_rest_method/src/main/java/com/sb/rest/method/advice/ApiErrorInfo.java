package com.sb.rest.method.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiErrorInfo {

    private String message;
    private HttpStatus status;

}
