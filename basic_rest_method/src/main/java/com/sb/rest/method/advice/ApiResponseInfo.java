package com.sb.rest.method.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponseInfo<T> {

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime localDateTime;
    private T data;
    private ApiErrorInfo error;

    public ApiResponseInfo() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiResponseInfo(T data) {
        this();
        this.data = data;
    }

    public ApiResponseInfo(ApiErrorInfo error) {
        this();
        this.error = error;
    }
}
