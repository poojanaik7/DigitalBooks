package com.digitalbooks.exception;

public class ResultNotFoundException extends RuntimeException {

    private String message;

    public ResultNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
