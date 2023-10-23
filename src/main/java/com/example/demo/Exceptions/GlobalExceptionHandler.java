package com.example.demo.Exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = {PaginationException.class})
    protected ResponseEntity<Object> handlePaginationException(PaginationException paginationException,
                                                               WebRequest webRequest) {
        var body = new ApiError("Page number and size must a positive number", 400);
        logger.info(body.getMessage());
        return handleExceptionInternal(paginationException, body,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }


}
