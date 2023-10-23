package com.example.demo.Exceptions;

public class PaginationException extends RuntimeException{

    public PaginationException() {
    }
    public PaginationException(String message) {
        super(message);
    }


}
