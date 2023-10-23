package com.example.demo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiError {
    private String status;
    private String message;
    private Integer responseCode;

    public ApiError(String message, Integer responseCode) {
        this.status = "error";
        this.message = message;
        this.responseCode = responseCode;
    }
}
