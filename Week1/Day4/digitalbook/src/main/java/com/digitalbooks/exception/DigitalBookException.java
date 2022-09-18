package com.digitalbooks.exception;

public class DigitalBookException extends Exception {

    private String message;

    public DigitalBookException(String msg) {
        super(msg);
        this.message = msg;
    }
}

